package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.component.UIParameter;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.front.ejb.interfaces.CorreoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@SuppressWarnings("serial")
@Model
@ViewScoped
public class CorreoController implements Serializable {

    private Logger log = Logger.getLogger(this.getClass());

    @Inject
    SessionBeanJatrik sessionBean;

    @EJB
    private EquipoEJBLocal equipoEJB;

    @EJB
    private UsuarioEJBLocal usuarioEJB;

    @EJB
    private CorreoEJBLocal correoEJB;

    private List<InfoCorreo> bandejaEntrada;
    private String userID;
    private int cantidadNoLeidos = 0;
    private InfoCorreo nuevoCorreo;
    private List<SelectItem> usuarios;
    //private HtmlSelectOneMenu usuarioTO;	
    private String usuarioTO;
    private String correoEnviado;

    @PostConstruct
    public void initDatos() {
        userID = (String) sessionBean.getNick();

        List<InfoCorreo> correos = new ArrayList<InfoCorreo>();
        correos = correoEJB.obtenerCorreos(sessionBean.getInfoUsuario().getId());

        setBandejaEntrada(correos);
        cantidadNoLeidos = contarNoLeidos(bandejaEntrada);

        usuarios = cargarUsuarios(usuarioEJB.getUsuarios());
        nuevoCorreo = new InfoCorreo();
    }

    private int contarNoLeidos(List<InfoCorreo> bandejaEntrada2) {
        Iterator<InfoCorreo> it = bandejaEntrada2.iterator();
        int noLeidos = 0;
        while (it.hasNext()) {
            if (!it.next().isLeido()) {
                noLeidos++;
            }
        }
        return noLeidos;
    }

    public List<InfoCorreo> getBandejaEntrada() {
        return bandejaEntrada;
    }

    public void setBandejaEntrada(List<InfoCorreo> bandejaEntrada) {
        this.bandejaEntrada = bandejaEntrada;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCantidadNoLeidos() {
        return cantidadNoLeidos;
    }

    public void setCantidadNoLeidos(int cantidadNoLeidos) {
        this.cantidadNoLeidos = cantidadNoLeidos;
    }

    public InfoCorreo getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(InfoCorreo nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public List<SelectItem> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<SelectItem> usuarios) {
        this.usuarios = usuarios;
    }

    /*	public HtmlSelectOneMenu getUsuarioTO() {
     return usuarioTO;
     }
     public void setUsuarioTO(HtmlSelectOneMenu usuarioTO) {
     this.usuarioTO = usuarioTO;
     }
     */
    public String getUsuarioTO() {
        return usuarioTO;
    }

    public void setUsuarioTO(String usuarioTO) {
        this.usuarioTO = usuarioTO;
    }

    public String getCorreoEnviado() {
        return correoEnviado;
    }

    public void setCorreoEnviado(String correoEnviado) {
        this.correoEnviado = correoEnviado;
    }

    private List<SelectItem> cargarUsuarios(List<InfoUsuario> listaInfoUsuarios) {
        List<SelectItem> lista = new ArrayList<SelectItem>();
        Iterator<InfoUsuario> it = listaInfoUsuarios.iterator();
        SelectItem item = null;
        while (it.hasNext()) {
            InfoUsuario u = it.next();
            item = new SelectItem(u.getNick(), u.getNick());
            lista.add(item);
        }
        return lista;
    }

    public void marcarLeido(ActionEvent e) {
        String correoString = ((UIParameter) e.getComponent().findComponent("idCorreo")).getValue().toString();
        long correoLong = Long.parseLong(correoString);

        Iterator<InfoCorreo> it = getBandejaEntrada().iterator();
        while (it.hasNext()) {
            InfoCorreo c = it.next();
            if (c.getId().longValue() == correoLong) {
                if (c.isLeido()) {
                    c.setLeido(false);
                } else {
                    c.setLeido(true);
                }
                correoEJB.updateCorreo(c);
            }
        }
        log.info("Marcando como leido " + correoString + " !!");
    }

    public void send() {

        log.info("Enviando correo");
        log.info("El usuario al que mando es :" + usuarioTO);

	userID = (String) sessionBean.getNick();
        InfoUsuario usuario = sessionBean.getInfoUsuario();

        for (InfoUsuario info : usuarioEJB.getUsuarios()) {
            if (info.getNick().equals(usuarioTO)) {
                log.info("Encontre Usuario TO");
                nuevoCorreo.setTo(info.getId());
                nuevoCorreo.setLeido(false);
                nuevoCorreo.setFecha(new Date());
                nuevoCorreo.setFrom(usuario.getId());
                correoEJB.addCorreo(nuevoCorreo);
                break;
            }
        }
    }
}
