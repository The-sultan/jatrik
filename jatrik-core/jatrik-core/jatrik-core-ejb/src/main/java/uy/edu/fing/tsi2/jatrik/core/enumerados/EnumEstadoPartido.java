package uy.edu.fing.tsi2.jatrik.core.enumerados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnumEstadoPartido implements Comparable<EnumEstadoPartido> {

	PENDIENTE(1, "Pendiente"), 
	EN_CURSO(2, "En curso"), 
	FINALIZADO(3,"Finalizado");

	private int codigo;
	private String estado;

	private EnumEstadoPartido(int codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getEstado() {
		return this.estado;
	}

	public static List<EnumEstadoPartido> getAllValues() {
		EnumEstadoPartido[] resultArray = values();
		List<EnumEstadoPartido> result = new ArrayList<EnumEstadoPartido>();

		for (EnumEstadoPartido estadosVehiculo : resultArray) {
			result.add(estadosVehiculo);
		}
		Collections.sort(result);

		return result;
	}

	public static EnumEstadoPartido getById(int codigo) {
		EnumEstadoPartido[] enumList = values();

		for (EnumEstadoPartido currentEnum : enumList) {
			if (codigo == currentEnum.getCodigo()) {
				return currentEnum;
			}
		}

		return null;
	}

}
