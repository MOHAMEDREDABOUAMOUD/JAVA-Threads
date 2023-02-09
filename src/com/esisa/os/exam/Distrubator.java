package com.esisa.os.exam;

import java.util.List;
import java.util.Vector;

import com.esisa.os.exam.help.Semaphore;

public class Distrubator extends Thread{
	private CardTable cardTable;
	private List<Card> cards;
	private Semaphore full;
	private Semaphore empty;

	public Distrubator(String name,CardTable cardTable,Semaphore full,Semaphore empty) {
		super(name);
		this.cardTable=cardTable;
		cards=new Vector<Card>();
		cards.add(new Card("As","Pique"));
		cards.add(new Card("As","Coeur"));
		cards.add(new Card("As","Carreau"));
		cards.add(new Card("As","Trefle"));
		cards.add(new Card("Roi","Pique"));
		cards.add(new Card("Roi","Coeur"));
		cards.add(new Card("Roi","Carreau"));
		cards.add(new Card("Roi","Trefle"));
		cards.add(new Card("Dame","Pique"));
		cards.add(new Card("Dame","Coeur"));
		cards.add(new Card("Dame","Carreau"));
		cards.add(new Card("Dame","Trefle"));
		cards.add(new Card("Valet","Pique"));
		cards.add(new Card("Valet","Coeur"));
		cards.add(new Card("Valet","Carreau"));
		cards.add(new Card("Valet","Trefle"));
		for (int i = 2; i <= 10; i++) {
			cards.add(new Card(i+"","Pique"));
			cards.add(new Card(i+"","Coeur"));
			cards.add(new Card(i+"","Carreau"));
			cards.add(new Card(i+"","Trefle"));
		}
		cards.add(new Card("Joker",null));
		cards.add(new Card("Joker",null));
		this.full=full;
		this.empty=empty;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 54; i++) {
			empty.waitDijkstra();
			if(!cardTable.put(cards.get(i))) {
				empty.signalDijkstra();
				i--;
			}
			full.signalDijkstra();
		}
	}

}
