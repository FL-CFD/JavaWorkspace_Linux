package com.example;

import java.util.HashSet;
import java.util.Set;

public class Player {
	public int id;
	public String name;
	public Set<Poker> pokers;
	public Poker pokerMax;
	
	public Player(){}
	
	public Player(int id, String name){
		this.id = id;
		this.name = name;
		this.pokers = new HashSet<Poker>();
		this.pokerMax = new Poker();
	}

	
}
