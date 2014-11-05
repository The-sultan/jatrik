package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.component.UIParameter;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import uy.edu.fing.tsi2.front.ejb.interfaces.CorreoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.front.ejb.interfaces.UsuarioEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoCorreo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoUsuario;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;

@SuppressWarnings("serial")
@Model
@RequestScoped
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
	private HtmlSelectOneMenu usuarioTO;	
	private String correoEnviado;
	
	@PostConstruct
	public void initDatos(){
		userID = (String) sessionBean.getNick();
		
		
	/*	InfoCorreo info = new InfoCorreo();
		info.setFrom(2L);
		info.setAsunto("hola");
		info.setFecha(new Date());
		info.setFrom(1L);
		info.setId(1L);
		info.setLeido(false);
		info.setMensaje("HOLA COMO VA, ESTAS BIEN?");
		info.setTo(2L);
		
		InfoCorreo nuevoCorreo = new InfoCorreo();
		nuevoCorreo.setFrom(3L);
		nuevoCorreo.setAsunto("hola2");
		nuevoCorreo.setFecha(new Date());
		nuevoCorreo.setFrom(1L);
		nuevoCorreo.setId(2L);
		nuevoCorreo.setLeido(false);
		nuevoCorreo.setMensaje("HOLA COMO VA, ESTAS BIEN 2?");
		nuevoCorreo.setTo(2L);
		*/
		List<InfoCorreo> correos = new ArrayList<InfoCorreo>();
		correos = correoEJB.obtenerCorreos(sessionBean.getInfoUsuario().getId());
		
		//correos.add(info);
		
		setBandejaEntrada(correos);
		cantidadNoLeidos = contarNoLeidos(bandejaEntrada);
		
		//List<InfoUsuario> users = new ArrayList<InfoUsuario>();
		usuarios = cargarUsuarios(usuarioEJB.getUsuarios());
		nuevoCorreo = new InfoCorreo();
	}
	
	
	public CorreoController() {
		
//		//HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		//HttpSession session = request.getSession();
//
//		userID = (String) sessionBean.getNick();
//		
//		InfoUsuario usuario = sessionBean.getInfoUsuario();
//
//		List<InfoCorreo> correos =correoEJB.obtenerCorreos(usuario);
//
//		Collections.sort(correos, new ComparadorCorreos());
//		setBandejaEntrada(correos);
//
//		cantidadNoLeidos = contarNoLeidos(bandejaEntrada);
//
//		log.info("Correos en la bandeja de entrada : " + bandejaEntrada.size());
//		
//		usuarios = cargarUsuarios(usuarioEJB.getUsuarios());
//		
//		nuevoCorreo =  new InfoCorreo();
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

	public HtmlSelectOneMenu getUsuarioTO() {
		return usuarioTO;
	}

	public void setUsuarioTO(HtmlSelectOneMenu usuarioTO) {
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
		while(it.hasNext()){
			InfoUsuario u = it.next();
			item = new SelectItem(u.getId(),u.getNick());
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
				if (c.isLeido()){
					c.setLeido(false);	
				}else {
					c.setLeido(true);
				}
				correoEJB.updateCorreo(c);
			}
		}
		log.info("Marcando como leido " + correoString + " !!");
	}
	public void send(ActionEvent e) {
		
		log.info("Enviado correo");
		log.info(usuarioTO.getValue());
		
		//HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		//HttpSession session = request.getSession();

		//userID = (String) session.getAttribute("userId");
		userID = (String) sessionBean.getNick();
		InfoUsuario usuario = sessionBean.getInfoUsuario();
		
		
		for (InfoUsuario info : usuarioEJB.getUsuarios()) {
			if (info.getNick().equals(usuarioTO.getValue())){
				log.info("Encontre Usuario TO");
				nuevoCorreo.setTo(info.getId());
				nuevoCorreo.setLeido(false);
				break;				
			}
		} 
		//Usuario para = usuarioEJB.manager.getUsuariosManager().findUsuarioByName((String) usuarioTO.getValue());
	
		
		
		
		nuevoCorreo.setFecha(new Date());
		nuevoCorreo.setFrom(usuario.getId());
		
		
		//nuevoCorreo.setTo(para.);
		//nuevoCorreo.setFromId(usuario.getId());
		//nuevoCorreo.setToId(para.getId());
		
		correoEJB.addCorreo(nuevoCorreo);
		
		
		correoEnviado = "El mensaje fue correctamente enviado";
	}
	
	
	private class ComparadorCorreos implements Comparator<InfoCorreo> {

		public int compare(InfoCorreo o1, InfoCorreo o2) {
			return (-1)  *(o1.getFecha()).compareTo(o2.getFecha());

		}

	}
}