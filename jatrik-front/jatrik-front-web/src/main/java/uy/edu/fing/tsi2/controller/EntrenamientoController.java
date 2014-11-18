package uy.edu.fing.tsi2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEntrenamientoJugador;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.model.Equipo.Equipo;
import uy.edu.fing.tsi2.model.Equipo.Jugador;

@Model
@ViewScoped
public class EntrenamientoController {

	@Inject
	SessionBeanJatrik sessionBean;

	@EJB
	EquipoEJBLocal equipoEJB;

	@Named
	@Produces
	Equipo equipoDatosEntrenamiento;

	@Named
	@Produces
	List<Jugador> entrenarDefensa;

	private boolean error = false;

	@PostConstruct
	public void initDatos() {

		try {

			// cargar el equipo

			// TODO:Obtener el id del loginBean
			equipoDatosEntrenamiento = new Equipo();
			InfoFormacion formacion = equipoEJB
					.getFormacionEstandar(sessionBean.getInfoUsuario()
							.getInfoEquipo().getId());
			List<InfoJugador> titulares = new ArrayList<>();
			titulares.add(formacion.getGolero());
			for (InfoJugador jugador : formacion.getDefensas()) {
				titulares.add(jugador);
			}
			for (InfoJugador jugador : formacion.getMediocampistas()) {
				titulares.add(jugador);
			}
			for (InfoJugador jugador : formacion.getDelanteros()) {
				titulares.add(jugador);
			}
			for (InfoJugador jugador : formacion.getSuplentes()) {
				titulares.add(jugador);
			}
			for (InfoJugador jugador : formacion.getReservas()) {
				titulares.add(jugador);
			}

			equipoDatosEntrenamiento.setTitulares(titulares);

			error = false;
		} catch (Exception e) {
			error = true;
		}

	}

	public void agregarAEntrenamiento() {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		String ids = params.get("ids");
		String id2 = params.get("tipoEntrenamiento");
	}

	public EntrenamientoController() {

	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
