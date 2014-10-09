package uy.edu.fing.tsi2.jatrik.core.enumerados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnumPuestoFormacion implements Comparable<EnumPuestoFormacion> {
	ARQUERO(1, "Arquero"),
	DEFENSA(2, "Defensa"),
	MEDIOCAMPISTA(3, "Mediocampista"),
	DELANTERO(4, "Delantero"),
	SUPLENTE(5, "Suplente"),
	RESERVA(6, "Reserva");

	
	
	private int codigo;
	private String posicion;

	private EnumPuestoFormacion(int codigo, String posicion) {
		this.codigo = codigo;
		this.posicion = posicion;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getPosicion() {
		return this.posicion;
	}
	
	public static List<EnumPuestoFormacion> getAllValues() {
		EnumPuestoFormacion[] resultArray = values();
		List<EnumPuestoFormacion> result = new ArrayList<EnumPuestoFormacion>();
		
		for (EnumPuestoFormacion estadosVehiculo : resultArray) {
			result.add(estadosVehiculo);
		}
		Collections.sort(result);
		
		return result;
	}

	public static EnumPuestoFormacion getById(int codigo) {
		EnumPuestoFormacion[] enumList = values();
		
		for (EnumPuestoFormacion currentEnum : enumList) {
			if (codigo == currentEnum.getCodigo()) {
				return currentEnum;
			}
		}
		
		return null;
	}	
	
}
