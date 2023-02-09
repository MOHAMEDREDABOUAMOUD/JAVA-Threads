package com.esisa.os.exam;

import com.esisa.os.exam.help.Observer;
import com.esisa.os.exam.help.Semaphore;
import com.esisa.os.exam.help.Util;

public class ConsoleObserver implements Observer{
	private CardTable cardTable;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Semaphore full;
	private Semaphore empty;
	private Distrubator distrubator;

	public ConsoleObserver(CardTable cardTable) {
		this.cardTable=cardTable;
		this.cardTable.setObserver(this);
		full=new Semaphore(0);
		empty=new Semaphore(8*8);
		distrubator =new Distrubator("Distrubuter", cardTable,full, empty);
		player1=new Player("Player 1",cardTable, full, empty);
		player2=new Player("Player 2",cardTable, full, empty);
		player3=new Player("PLayer 3",cardTable, full, empty);
		player4=new Player("Player 4",cardTable, full, empty);
		
		distrubator.start();
		Util.waitFor(distrubator);
		player1.start();
		player2.start();
		player3.start();
		player4.start();
	}
	
	public static void main(String[] args) {
		CardTable table=new CardTable();
		new ConsoleObserver(table);
	}

	@Override
	public void update() {
		System.out.println(cardTable.toString());
		System.out.println("*********************************************************");
	}

}
