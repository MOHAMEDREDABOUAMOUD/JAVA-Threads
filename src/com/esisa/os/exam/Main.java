package com.esisa.os.exam;

import javax.swing.JFrame;

import com.esisa.os.exam.help.Semaphore;
import com.esisa.os.exam.help.Util;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	private PanelObserver panelObserver;
	
	public Main() {
		CardTable cardTable=new CardTable();
		Semaphore full=new Semaphore(0);
		Semaphore empty=new Semaphore(8*8);
		Distrubator distrubator =new Distrubator("Distrubuter", cardTable,full, empty);
		Player player1=new Player("Player 1",cardTable, full, empty);
		Player player2=new Player("Player 2",cardTable, full, empty);
		Player player3=new Player("PLayer 3",cardTable, full, empty);
		Player player4=new Player("Player 4",cardTable, full, empty);
		panelObserver=new PanelObserver(cardTable);
		
		config();
		
		distrubator.start();
		Util.waitFor(distrubator);
		player1.start();
		player2.start();
		player3.start();
		player4.start();
	}
	
	private void config() {
		setContentPane(panelObserver);
		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
