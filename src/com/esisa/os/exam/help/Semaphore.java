package com.esisa.os.exam.help;

import com.esisa.os.exam.help.Logger;
import com.esisa.os.exam.help.ThreadList;

//cree par Dijkstra en 1965
//C'est une structure de données base sur la notion 
//de verrou pour assurer des accés exclusifs a une resource partagée.
//Le verrou est une simple variable de type int :
//int v=1;=>1 ressource disponible
//	  v=2;=>2 ressources disponible
//Le verrou permet aussi de compatbiliser les acces
//ainsi que les tentatives d'accés
//	  v=0;=>plus de ressource disponible
//	  v=-1;=>un process bloqué
//	  v=-2;=>deux processus bloqués
//	  ....

public class Semaphore {
	private int value;	//Le verrou
	private ThreadList waitingThreadList;
	private Logger logger=null;
	private String name;
	
	public Semaphore() {
		this("Semaphore");
		//init(1);//Semaphore d'exclusion mutielle
	}
	public Semaphore(int n) {
		this("Semaphore",n);
		//init(n);//Semaphore multiple
	}
	public Semaphore(String name) {
		this.name=name;
		init(1);//Semaphore d'exclusion mutielle
	}
	public Semaphore(String name,int n) {
		this.name=name;
		init(n);//Semaphore multiple
	}
	public ThreadList getWaitingThreadList() {
		return waitingThreadList;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
// 3 primitives de Dijkstra:
	synchronized public void init(int n) {
		value=n;
		waitingThreadList=new ThreadList(name);
	}
	synchronized public void waitDijkstra() {//verouiller
		value--;
		if(value<0) {
			suspend();//bloquer();
		}
	}
	synchronized public void signalDijkstra() {//deverouiller
		value++;
		if(value<=0) {
			resume();//réveiller();
		}
	}
//------------------------------------------
	private void suspend() {
		waitingThreadList.add();
		log("PAssage a l'etat bloqué");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitingThreadList.remove();
		log("PAssage a l'etat réveillé");
	}
	private void resume() {
		notify();//quand on reveille le thread sa reveille apres wait directement donc on fait la supression apres wait
	}
	
	private void log(String message) {
		if(logger!=null) {
			logger.log(message, waitingThreadList);
		}
	}
}
