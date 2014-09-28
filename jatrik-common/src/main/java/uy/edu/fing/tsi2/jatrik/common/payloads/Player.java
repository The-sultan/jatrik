package uy.edu.fing.tsi2.jatrik.common.payloads;

/**
 * @author Farid
 */
public class Player {
	
	private String name;
	
	private String position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Player() {
	}

	public Player(String name, String position) {
		this.name = name;
		this.position = position;
	}
	
	
}
