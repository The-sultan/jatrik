package uy.edu.fing.tsi2.jatrik.core.enumerados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnumPuesto implements Comparable<EnumPuesto> {
	ARQUERO(1, "Arquero"),
	DEFENSA(2, "Defensa"),
	VOLANTE(3, "Volante"),
	DELANTERO(4, "Delantero"),
	RESERVA(5,"Reserva"),
	FUERA(6,"Fuera");
	
	
	private int codigo;
	private String posicion;

	private EnumPuesto(int codigo, String posicion) {
		this.codigo = codigo;
		this.posicion = posicion;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getPosicion() {
		return this.posicion;
	}
	
	public static List<EnumPuesto> getAllValues() {
		EnumPuesto[] resultArray = values();
		List<EnumPuesto> result = new ArrayList<EnumPuesto>();
		
		for (EnumPuesto estadosVehiculo : resultArray) {
			result.add(estadosVehiculo);
		}
		Collections.sort(result);
		
		return result;
	}

	public static EnumPuesto getById(int codigo) {
		EnumPuesto[] enumList = values();
		
		for (EnumPuesto currentEnum : enumList) {
			if (codigo == currentEnum.getCodigo()) {
				return currentEnum;
			}
		}
		
		return null;
	}	
	
}
