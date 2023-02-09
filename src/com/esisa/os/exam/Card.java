package com.esisa.os.exam;

public class Card {
	private String index;
	private String type;
	private int valeur;

	public Card() {
	}

	public Card(String index, String type) {
		super();
		this.index = index;
		this.type = type;
		if(index=="As") valeur=1;
		else if(index=="Valet" || index=="Dame" || index=="Roi") valeur=10;
		else if(index=="Joker") valeur=25;
		else valeur=Integer.parseInt(index);
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

}
