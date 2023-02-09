package com.esisa.os.exam.help;

import java.util.LinkedList;

public class ThreadList{
	
	private LinkedList<Thread> list;
	private Observer observer=null;
	private String name;
	
	public ThreadList(String name) {
		this.name=name;
		list=new LinkedList<Thread>();
	}
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
	public void add() {
		list.add(Thread.currentThread());
		update();
	}
	public void add(Thread thread) {
		list.add(thread);
		update();
	}
	public void remove() {
		list.remove(Thread.currentThread());
		update();
	}
	void update() {
		if(observer!=null) observer.update();
	}
	public LinkedList<Thread> getList() {
		return list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
