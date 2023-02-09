package com.esisa.os.exam;

import com.esisa.os.exam.help.Semaphore;

public class Player extends Thread{
	private CardTable cardTable;
	private int value;
	private Semaphore full;
	private Semaphore empty;

	public Player(String name,CardTable cardTable,Semaphore full,Semaphore empty) {
		super(name);
		this.cardTable=cardTable;
		this.full=full;
		this.empty=empty;
	}

	@Override
	public void run() {
		do {
			int i=(int)(Math.random()*8);
			int j=(int)(Math.random()*8);
			full.waitDijkstra();
			Card c=cardTable.pickUp(i, j);
			empty.signalDijkstra();
			if(c!=null) {
				value+=c.getValeur();
			}
			if(value>=100) cardTable.setVainqueur(Thread.currentThread().getName());
		} while (value<=100);
	}
	synchronized public int getValue() {
		return value;
	}
}
