package uy.edu.fing.tsi2.jatrik.core.simulacion.estrategias;

import java.util.Random;
import javax.ejb.Stateless;
import uy.edu.fing.tsi2.jatrik.core.domain.Partido;

@Stateless
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

}
