package com.esisa.os.exam;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.esisa.os.exam.help.Observer;

public class PanelObserver extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private CardTable cardTable;
	private int size=50,margin=20;

	public PanelObserver(CardTable cardTable) {
		this.cardTable=cardTable;
		this.cardTable.setObserver(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		printGrid(g);
		printData(g);
		setBackground(Color.WHITE);
	}
	
	private void printData(Graphics g) {
		Card[][] m=cardTable.getCards();
		g.setColor(Color.BLACK);
		for (int i = 0; i <8; i++) {
			for (int j = 0; j < 8; j++) {
				if(m[i][j]!=null) {
					g.drawString(m[i][j].getIndex(), margin+size*i+10, margin+size*j+30);
				}
			}
		}
		g.drawString("Le vainqueur : "+cardTable.getVainqueur(), margin, margin+size*8+15);
	}

	private void printGrid(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(margin, margin, size*8, size*8);
		for (int i = 1; i <8; i++) {
			for (int j = 1; j < 8; j++) {
				g.drawLine(margin+size*i, margin, margin+size*i, margin+size*8);
				g.drawLine(margin, margin+size*j,margin+size*8 , margin+size*j);
			}
		}
	}

	@Override
	public void update() {
		repaint();
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(margin*2+size*8,margin*2+size*8);
	}

}
