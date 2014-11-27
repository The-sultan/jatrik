package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.model.Equipo.Equipo;

@SuppressWarnings("serial")
@ManagedBean
@Model
@SessionScoped
public class EntrenamientoController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;

	@EJB
	EquipoEJBLocal equipoEJB;

	Equipo equipoDatosEntrenamiento;

	List<InfoJugador> jugadoresEntrenar;

	List<InfoJugador> entrenarPorteria;

	List<InfoJugador> entrenarDefensa;

	List<InfoJugador> entrenarTecnica;

	List<InfoJugador> entrenarVelocidad;

	List<InfoJugador> entrenarAtaque;

	private boolean error = false;

	private boolean equipoEntreno = false;

	@PostConstruct
	public void initDatos() {

		try {

			// cargar el equipo

			equipoDatosEntrenamiento = new Equipo();
			InfoFormacion formacion = equipoEJB
					.getFormacionEstandar(sessionBean.getInfoUsuario()
							.getInfoEquipo().getId());
			jugadoresEntrenar = new ArrayList<>();
			jugadoresEntrenar.add(formacion.getGolero());
			for (InfoJugador jugador : formacion.getDefensas()) {
				jugadoresEntrenar.add(jugador);
			}
			for (InfoJugador jugador : formacion.getMediocampistas()) {
				jugadoresEntrenar.add(jugador);
			}
			for (InfoJugador jugador : formacion.getDelanteros()) {
				jugadoresEntrenar.add(jugador);
			}
			for (InfoJugador jugador : formacion.getSuplentes()) {
				jugadoresEntrenar.add(jugador);
			}
			for (InfoJugador jugador : formacion.getReservas()) {
				jugadoresEntrenar.add(jugador);
			}

			equipoDatosEntrenamiento.setTitulares(jugadoresEntrenar);

			entrenarAtaque = new ArrayList<InfoJugador>();
			entrenarDefensa = new ArrayList<InfoJugador>();
			entrenarPorteria = new ArrayList<InfoJugador>();
			entrenarTecnica = new ArrayList<InfoJugador>();
			entrenarVelocidad = new ArrayList<InfoJugador>();

			error = false;
			
			equipoEntreno = !equipoEJB.puedeEntrenarEquipo(sessionBean.getInfoUsuario()
							.getInfoEquipo().getId());
		} catch (Exception e) {
			error = true;
		}

	}

	public void agregarAEntrenamiento() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		String ids = params.get("ids");
		String tipoEntrenamiento = params.get("tipoEntrenamiento");

		List<String> listaIds = Arrays.asList(ids.split("#"));

		for (String id : listaIds) {
			InfoJugador jug = null;
			for (int i = 0; i < jugadoresEntrenar.size(); i++) {
				InfoJugador infoJug = jugadoresEntrenar.get(i);
				if (infoJug.getId() == Long.parseLong(id)) {
					jugadoresEntrenar.remove(i);
					switch (tipoEntrenamiento) {
					case "entrenarPorteria":
						entrenarPorteria.add(infoJug);
						break;
					case "entrenarDefensa":
						entrenarDefensa.add(infoJug);
						break;
					case "entrenarTecnica":
						entrenarTecnica.add(infoJug);
						break;
					case "entrenarVelocidad":
						entrenarVelocidad.add(infoJug);
						break;
					case "entrenarAtque":
						entrenarAtaque.add(infoJug);
						break;
					default:
						break;
					}
					break;
				}
			}

		}

	}

	public void comenzarEntrenamiento() {
		List<InfoEntrenamientoJugador> entrenamiento = new ArrayList<InfoEntrenamientoJugador>();

		InfoEntrenamientoJugador entr;

		for (InfoJugador jug : entrenarVelocidad) {
			entr = new InfoEntrenamientoJugador();
			entr.setIdjugador(jug.getId());
			entr.setModo(1);
			entrenamiento.add(entr);
		}
		for (InfoJugador jug : entrenarTecnica) {
			entr = new InfoEntrenamientoJugador();
			entr.setIdjugador(jug.getId());
			entr.setModo(2);
			entrenamiento.add(entr);
		}
		for (InfoJugador jug : entrenarAtaque) {
			entr = new InfoEntrenamientoJugador();
			entr.setIdjugador(jug.getId());
			entr.setModo(3);
			entrenamiento.add(entr);
		}
		for (InfoJugador jug : entrenarDefensa) {
			entr = new InfoEntrenamientoJugador();
			entr.setIdjugador(jug.getId());
			entr.setModo(4);
			entrenamiento.add(entr);
		}
		for (InfoJugador jug : entrenarPorteria) {
			entr = new InfoEntrenamientoJugador();
			entr.setIdjugador(jug.getId());
			entr.setModo(5);
			entrenamiento.add(entr);
		}

		equipoEJB.entrenarEquipo(sessionBean.getInfoUsuario().getInfoEquipo()
				.getId(), entrenamiento);
		
		equipoEntreno = true;
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Entrenamiento realizado",  "El equipo acaba de realizar el entrenamiento diario") );
	}
	
	public void dummy(){
		
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<InfoJugador> getJugadoresEntrenar() {
		return jugadoresEntrenar;
	}

	public void setJugadoresEntrenar(List<InfoJugador> jugadoresEntrenar) {
		this.jugadoresEntrenar = jugadoresEntrenar;
	}

	public List<InfoJugador> getEntrenarPorteria() {
		return entrenarPorteria;
	}

	public void setEntrenarPorteria(List<InfoJugador> entrenarPorteria) {
		this.entrenarPorteria = entrenarPorteria;
	}

	public List<InfoJugador> getEntrenarDefensa() {
		return entrenarDefensa;
	}

	public void setEntrenarDefensa(List<InfoJugador> entrenarDefensa) {
		this.entrenarDefensa = entrenarDefensa;
	}

	public List<InfoJugador> getEntrenarTecnica() {
		return entrenarTecnica;
	}

	public void setEntrenarTecnica(List<InfoJugador> entrenarTecnica) {
		this.entrenarTecnica = entrenarTecnica;
	}

	public List<InfoJugador> getEntrenarVelocidad() {
		return entrenarVelocidad;
	}

	public void setEntrenarVelocidad(List<InfoJugador> entrenarVelocidad) {
		this.entrenarVelocidad = entrenarVelocidad;
	}

	public List<InfoJugador> getEntrenarAtaque() {
		return entrenarAtaque;
	}

	public void setEntrenarAtaque(List<InfoJugador> entrenarAtaque) {
		this.entrenarAtaque = entrenarAtaque;
	}

	public boolean isEquipoEntreno() {
		return equipoEntreno;
	}

	public void setEquipoEntreno(boolean equipoEntreno) {
		this.equipoEntreno = equipoEntreno;
	}

}
