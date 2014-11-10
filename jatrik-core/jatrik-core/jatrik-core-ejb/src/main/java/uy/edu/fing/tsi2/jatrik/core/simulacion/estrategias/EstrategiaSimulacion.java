package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.jatrik.core.domain.Jugador;
import uy.edu.fing.tsi2.jatrik.core.domain.JugadorEnFormacion;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;
import uy.edu.fing.tsi2.jatrik.core.enumerados.EnumPuestoFormacion;

public abstract class EstrategiaSimulacion {
	
	public abstract void manejarEvento(Partido partido);
	
	private int peso;

	private Random randomGenerator = new Random();
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public int getNextInt(int max){
		return randomGenerator.nextInt(max);
	}
	
	public boolean getNextBoolean(){
		return randomGenerator.nextBoolean();
	}
	
	protected Jugador getJugadorEnPosicion(Partido partido, Set<JugadorEnFormacion> jugadores, EnumPuestoFormacion... puestos){
		Set<Jugador> jugadoresFueraDeCancha = partido.getJugadoresExpulsados();
		jugadoresFueraDeCancha.addAll(partido.getJugadoresLesionados());
		jugadores.removeAll(jugadoresFueraDeCancha);
		List<Jugador> jugadoresSeleccionados = new ArrayList<>();
		for(JugadorEnFormacion jugador : jugadores){
			if(!partido.getJugadoresExpulsados().contains(jugador.getJugador())){
				boolean agregar = false;
				for(EnumPuestoFormacion puesto : puestos){
					if(jugador.getPuestoFormacion().equals(puesto)){
						agregar = true;
						break;
					}
				}
				if(agregar){
					jugadoresSeleccionados.add(jugador.getJugador());
				}
			}
		}
		if(jugadoresSeleccionados.isEmpty()){
			return null;
		}else{
			Random random = new Random(jugadores.size());
			return jugadoresSeleccionados.get(random.nextInt(jugadoresSeleccionados.size()));
		}
	}

}
