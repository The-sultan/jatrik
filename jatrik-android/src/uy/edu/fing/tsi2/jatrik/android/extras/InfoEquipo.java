package uy.edu.fing.tsi2.jatrik.android.extras;

import java.util.List;

public class InfoEquipo {

	private Long id;
	
	private String Nombre;
	
	private Double fondos;
	
	private InfoEstadio estadio;
	
	//La formacion por defecto esta implicita en la cantidad de defensas, 
	//mediocampistas y delanteros titulares
	//En caso de que defensas.count=4, mediocampistas.count=3, delanteros.count=3
	//Entonces la formacion es 4-3-3
	private InfoJugador golero;
	private List<InfoJugador> defensas;
	private List<InfoJugador> mediocampistas;
	private List<InfoJugador> delanteros;
	private List<InfoJugador> suplentes;
	private List<InfoJugador> reservas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public Double getFondos() {
		return fondos;
	}

	public void setFondos(Double fondos) {
		this.fondos = fondos;
	}

	public InfoEstadio getEstadio() {
		return estadio;
	}

	public void setEstadio(InfoEstadio estadio) {
		this.estadio = estadio;
	}

	public InfoJugador getGolero() {
		return golero;
	}

	public void setGolero(InfoJugador golero) {
		this.golero = golero;
	}

	public List<InfoJugador> getDefensas() {
		return defensas;
	}

	public void setDefensas(List<InfoJugador> defensas) {
		this.defensas = defensas;
	}

	public List<InfoJugador> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(List<InfoJugador> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public List<InfoJugador> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(List<InfoJugador> delanteros) {
		this.delanteros = delanteros;
	}

	public List<InfoJugador> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(List<InfoJugador> suplentes) {
		this.suplentes = suplentes;
	}

	public List<InfoJugador> getReservas() {
		return reservas;
	}

	public void setReservas(List<InfoJugador> reservas) {
		this.reservas = reservas;
	}
	
	
	
	
	
}
