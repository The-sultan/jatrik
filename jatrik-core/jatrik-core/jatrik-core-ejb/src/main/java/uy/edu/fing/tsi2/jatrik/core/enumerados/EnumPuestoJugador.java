package uy.edu.fing.tsi2.jatrik.core.enumerados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnumPuestoJugador implements Comparable<EnumPuestoJugador> {
	ARQUERO(1, "Arquero"),
	DEFENSA(2, "Defensa"),
	MEDIOCAMPISTA(3, "Mediocampista"),
	DELANTERO(4, "Delantero");
	
	
	private int codigo;
	private String posicion;

	private EnumPuestoJugador(int codigo, String posicion) {
		this.codigo = codigo;
		this.posicion = posicion;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getPosicion() {
		return this.posicion;
	}
	
	public static List<EnumPuestoJugador> getAllValues() {
		EnumPuestoJugador[] resultArray = values();
		List<EnumPuestoJugador> result = new ArrayList<EnumPuestoJugador>();
		
		for (EnumPuestoJugador estadosVehiculo : resultArray) {
			result.add(estadosVehiculo);
		}
		Collections.sort(result);
		
		return result;
	}

	public static EnumPuestoJugador getById(int codigo) {
		EnumPuestoJugador[] enumList = values();
		
		for (EnumPuestoJugador currentEnum : enumList) {
			if (codigo == currentEnum.getCodigo()) {
				return currentEnum;
			}
		}
		
		return null;
	}	
	
}
