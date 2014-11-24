package uy.edu.fing.tsi2.jatrik.common.payloads;

import java.util.List;
import java.util.Map;

public class InfoLiga {
	private String nombre;

	// Un map entre el numero de etapa (fecha) y la lista de partidos de dicha
	// etapa
	private Map<Integer, List<InfoPartido>> partidos;

	private List<EquipoPosisionLiga> posisiones;

}
