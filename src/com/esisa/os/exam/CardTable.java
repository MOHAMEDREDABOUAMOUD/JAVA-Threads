package com.esisa.os.exam;

import com.esisa.os.exam.help.Observer;
import com.esisa.os.exam.help.Util;

public class CardTable {
	private Card[][] cards;
	private int CardCounter=0;
	private Observer observer;
	private String vainqueur="";

	public CardTable() {
		cards=new Card[8][8];
	}
	
	synchronized public boolean put(Card card) {
		int i=(int)(Math.random()*8);
		int j=(int)(Math.random()*8);
		if(cards[i][j]==null) {
			cards[i][j]=card;
			update();
			CardCounter++;
			Util.pause(1000);
			return true;
		}
		else return false;
	}
	
	synchronized public Card pickUp(int i,int j) {
		if(cards[i][j]!=null) {
			Card c=cards[i][j];
			cards[i][j]=null;
			update();
			CardCounter--;
			Util.pause(3000);
			return c;
		}
		else {
			Util.pause(5000);
			return null;
		}
	}
	
	synchronized public int getCardCounter() {
		return CardCounter;
	}
	public Card[][] getCards() {
		return cards;
	}
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	public void update() {
		if(observer!=null) observer.update();
	}
	synchronized public void setVainqueur(String vainqueur) {
		if(this.vainqueur=="")this.vainqueur = vainqueur;
	}
	public String getVainqueur() {
		return vainqueur;
	}
	
	@Override
	public String toString() {
		String s="Card table : \n";
		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards[i].length; j++) {
				if(cards[i][j]!=null) s+=cards[i][j].getIndex()+" | ";
				else s+="   | ";
			}
			s+="\n";
		}
		if(vainqueur!="") s+=vainqueur+" WIN";
		s+="\n";
		return s;
	}

}
