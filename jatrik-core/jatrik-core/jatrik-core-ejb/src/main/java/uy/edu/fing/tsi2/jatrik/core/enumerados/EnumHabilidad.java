package uy.edu.fing.tsi2.jatrik.core.enumerados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum EnumHabilidad implements Comparable<EnumHabilidad> {
	VELOCIDAD(1, "Velocidad"),
	TECNICA(2, "Tecnica"), 
	ATAQUE(3, "Ataque"), 
	DEFENSA(4, "Defensa"), 
	PORTERIA(5, "Porteria");

	private int codigo;
	private String habilidad;

	private EnumHabilidad(int codigo, String habilidad) {
		this.codigo = codigo;
		this.habilidad = habilidad;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getHabilidad() {
		return this.habilidad;
	}

	public static List<EnumHabilidad> getAllValues() {
		EnumHabilidad[] resultArray = values();
		List<EnumHabilidad> result = new ArrayList<EnumHabilidad>();

		for (EnumHabilidad h : resultArray) {
			result.add(h);
		}
		Collections.sort(result);

		return result;
	}

	public static EnumHabilidad getById(int codigo) {
		EnumHabilidad[] enumList = values();

		for (EnumHabilidad currentEnum : enumList) {
			if (codigo == currentEnum.getCodigo()) {
				return currentEnum;
			}
		}

		return null;
	}

}
