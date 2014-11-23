package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;



import javax.faces.context.FacesContext;
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
@SessionScoped
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
    private List<InfoCorreo> bandejaSalida;
    private String userID;
    private int cantidadNoLeidos = 0;
    private InfoCorreo nuevoCorreo;
    private List<SelectItem> usuarios;
    private String usuarioTO;
    private String correoEnviado;
    
    private boolean error = false;

    @PostConstruct
    public void initDatos() {
    	
    	try {
    		userID = (String) sessionBean.getNick();

            List<InfoCorreo> correosEntrada = new ArrayList<InfoCorreo>();
            List<InfoCorreo> correosSalida = new ArrayList<InfoCorreo>();
            nuevoCorreo = new InfoCorreo();
            
            
            correosEntrada = correoEJB.obtenerCorreos(sessionBean.getInfoUsuario().getId());
            setBandejaEntrada(correosEntrada);
            cantidadNoLeidos = contarNoLeidos(bandejaEntrada);
            
            
            correosSalida = correoEJB.obtenerCorreosEnviados(sessionBean.getInfoUsuario().getId());
            setBandejaSalida(correosSalida);

            usuarios = cargarUsuarios(usuarioEJB.getUsuarios());
           
		} catch (Exception e) {
			
			error = false;
		}
        
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

    public List<InfoCorreo> getBandejaSalida() {
        return bandejaSalida;
    }

    public void setBandejaSalida(List<InfoCorreo> bandejaSalida) {
        this.bandejaSalida = bandejaSalida;
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

    public void marcarLeido() {
       
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = (Map<String, String>) fc.getExternalContext().getRequestParameterMap();;
        String idCorreo = params.get("idCorreo");
        Long correoLong = Long.parseLong(idCorreo);
        
        Iterator<InfoCorreo> it = getBandejaEntrada().iterator();
        while (it.hasNext()) {
            InfoCorreo c = it.next();
            if (c.getId().equals(correoLong)) {
                if (c.isLeido()) {
                    c.setLeido(false);
                } else {
                    c.setLeido(true);
                }
                correoEJB.updateCorreo(c);
                break;
            }
        }
        log.info("Marcando como leido " + idCorreo + " !!");
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
        
        nuevoCorreo = new InfoCorreo();
    }
    
    public void refrescarBandeja() {
        setBandejaEntrada(correoEJB.obtenerCorreos(sessionBean.getInfoUsuario().getId()));
        
        cantidadNoLeidos = contarNoLeidos(bandejaEntrada);
        
        
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
