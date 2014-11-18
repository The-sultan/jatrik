package uy.edu.fing.tsi2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import uy.edu.fing.tsi2.front.ejb.interfaces.EquipoEJBLocal;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoEquipo;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoFormacion;
import uy.edu.fing.tsi2.jatrik.common.payloads.InfoJugador;
import uy.edu.fing.tsi2.model.SessionBeanJatrik;
import uy.edu.fing.tsi2.model.Equipo.Equipo;
import uy.edu.fing.tsi2.navigation.AjaxNavigator;

@SuppressWarnings("serial")
@Model
@ViewScoped
public class EquipoController implements Serializable {

	@Inject
	SessionBeanJatrik sessionBean;

	@Inject
	AjaxNavigator nav;

	@EJB
	EquipoEJBLocal equipoEJB;

	@Named
	@Produces
	Equipo equipoDatos;

	private List<SelectItem> formaciones;
	private String idFormacion;

	private InfoEquipo equipoTemp;

	private InfoFormacion formacion;

	private boolean error = false;

	@PostConstruct
	public void initDatos() {

		try {

			// cargar posibles formaciones
			SelectItemGroup g1 = new SelectItemGroup("Defensivas");
			g1.setSelectItems(new SelectItem[] {
					new SelectItem("5-3-2", "5-3-2"),
					new SelectItem("5-4-1", "5-4-1") });

			SelectItemGroup g2 = new SelectItemGroup("Medias");
			g2.setSelectItems(new SelectItem[] { new SelectItem("4-3-3",
					"4-3-3") });

			formaciones = new ArrayList<SelectItem>();
			formaciones.add(g1);
			formaciones.add(g2);

			// cargar el equipo

			// TODO:Obtener el id del loginBean
			setEquipoTemp(equipoEJB.getEquipo(sessionBean.getInfoUsuario()
					.getInfoEquipo().getId()));
			equipoDatos = new Equipo();
			formacion = equipoEJB.getFormacionEstandar(sessionBean
					.getInfoUsuario().getInfoEquipo().getId());
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

			equipoDatos.setTitulares(titulares);
			equipoDatos.setSuplentes(formacion.getSuplentes());
			equipoDatos.setReserva(formacion.getReservas());

			idFormacion = formacion.getDescriptor();
			
			error = false;

		} catch (Exception e) {
			error = true;
		}

	}

	public void onJugadorCambio() {
		try {
			int i = 0;
			int j = i;
			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> params = context.getExternalContext()
					.getRequestParameterMap();
			int id1 = Integer.parseInt(params.get("id1"));
			int id2 = Integer.parseInt(params.get("id2"));

			int index1 = 0;
			boolean esGolero1 = false;
			List<InfoJugador> lista1 = new ArrayList<InfoJugador>();

			int index2 = 0;
			boolean esGolero2 = false;
			List<InfoJugador> lista2 = new ArrayList<InfoJugador>();

			if (formacion.getGolero().getId() == id1) {
				esGolero1 = true;
			} else {
				for (int k = 0; k < formacion.getDefensas().size(); k++) {
					if (formacion.getDefensas().get(k).getId() == id1) {
						lista1 = formacion.getDefensas();
						index1 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getMediocampistas().size(); k++) {
					if (formacion.getMediocampistas().get(k).getId() == id1) {
						lista1 = formacion.getMediocampistas();
						index1 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getDelanteros().size(); k++) {
					if (formacion.getDelanteros().get(k).getId() == id1) {
						lista1 = formacion.getDelanteros();
						index1 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getSuplentes().size(); k++) {
					if (formacion.getSuplentes().get(k).getId() == id1) {
						lista1 = formacion.getSuplentes();
						index1 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getReservas().size(); k++) {
					if (formacion.getReservas().get(k).getId() == id1) {
						lista1 = formacion.getReservas();
						index1 = k;
						break;
					}
				}
			}

			if (formacion.getGolero().getId() == id2) {
				esGolero2 = true;
			} else {
				for (int k = 0; k < formacion.getDefensas().size(); k++) {
					if (formacion.getDefensas().get(k).getId() == id2) {
						lista2 = formacion.getDefensas();
						index2 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getMediocampistas().size(); k++) {
					if (formacion.getMediocampistas().get(k).getId() == id2) {
						lista2 = formacion.getMediocampistas();
						index2 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getDelanteros().size(); k++) {
					if (formacion.getDelanteros().get(k).getId() == id2) {
						lista2 = formacion.getDelanteros();
						index2 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getSuplentes().size(); k++) {
					if (formacion.getSuplentes().get(k).getId() == id2) {
						lista2 = formacion.getSuplentes();
						index2 = k;
						break;
					}
				}
				for (int k = 0; k < formacion.getReservas().size(); k++) {
					if (formacion.getReservas().get(k).getId() == id2) {
						lista2 = formacion.getReservas();
						index2 = k;
						break;
					}
				}
			}

			// Si el jugador 1 es el golero:
			InfoJugador swap;
			if (esGolero1) {
				swap = lista2.get(index2);
				lista2.set(index2, formacion.getGolero());
				formacion.setGolero(swap);
			} else if (esGolero2) {
				swap = lista1.get(index1);
				lista1.set(index1, formacion.getGolero());
				formacion.setGolero(swap);
			} else {
				swap = lista1.get(index1);
				lista1.set(index1, lista2.get(index2));
				lista2.set(index2, swap);
			}

			List<InfoJugador> titulares = new ArrayList<>();
			titulares.add(formacion.getGolero());
			titulares.addAll(formacion.getDefensas());
			titulares.addAll(formacion.getMediocampistas());
			titulares.addAll(formacion.getDelanteros());
			equipoDatos.setTitulares(titulares);
			equipoDatos.setSuplentes(formacion.getSuplentes());
			equipoDatos.setReserva(formacion.getReservas());

			guardarEquipo(idFormacion);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getIdFormacion() {
		return idFormacion;
	}

	public void setIdFormacion(String idFormacion) {
		this.idFormacion = idFormacion;
	}

	public void onFormacionChange() {
		System.out.println(idFormacion);
		System.out.flush();
		cambiarFormacion(idFormacion);
		guardarEquipo(idFormacion);
	}

	private void cambiarFormacion(String descForm) {
		String[] lineas = descForm.split("-");

		int cantDef = Integer.parseInt(lineas[0]);
		int cantMed = Integer.parseInt(lineas[1]);
		int cantDel = Integer.parseInt(lineas[2]);

		formacion.setDefensas(new ArrayList<InfoJugador>(equipoDatos
				.getTitulares().subList(1, cantDef + 1)));
		formacion.setMediocampistas(new ArrayList<InfoJugador>(equipoDatos
				.getTitulares().subList(cantDef + 1, cantDef + cantMed + 1)));
		formacion.setDelanteros(new ArrayList<InfoJugador>(equipoDatos
				.getTitulares().subList(cantDef + cantMed + 1,
						cantDef + cantMed + cantDel + 1)));

	}

	private void guardarEquipo(String descForm) {
		InfoFormacion toSave = new InfoFormacion();
		toSave.setDefensas(formacion.getDefensas());
		toSave.setMediocampistas(formacion.getMediocampistas());
		toSave.setDelanteros(formacion.getDelanteros());
		toSave.setGolero(formacion.getGolero());
		toSave.setSuplentes(formacion.getSuplentes());
		toSave.setReservas(formacion.getReservas());
		toSave.setDescriptor(descForm);

		equipoEJB.saveFormacionEstandar(sessionBean.getInfoUsuario()
				.getInfoEquipo().getId(), toSave);
	}

	public List<SelectItem> getFormaciones() {
		return formaciones;
	}

	public void setFormaciones(List<SelectItem> formaciones) {
		this.formaciones = formaciones;
	}

	public InfoFormacion getFormacion() {
		return formacion;
	}

	public void setFormacion(InfoFormacion formacion) {
		this.formacion = formacion;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public InfoEquipo getEquipoTemp() {
		return equipoTemp;
	}

	public void setEquipoTemp(InfoEquipo equipoTemp) {
		this.equipoTemp = equipoTemp;
	}

}
