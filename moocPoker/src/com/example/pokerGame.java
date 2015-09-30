package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class pokerGame {

	public List<Poker> pokersToSelect= new ArrayList<Poker>();
	public Map<Integer, Player> players = new HashMap<Integer, Player>();
	public Scanner console = new Scanner(System.in);
	
	public void createPoker(){
		System.out.println("Create the pokers...");
		String[] colors = new String[]{"Heart","Diamond","Club", "Spade"};
		
		for(int i=0; i<13; i++){
			for(String color : colors){
				pokersToSelect.add(new Poker(i+1, color));
			}
		}
		System.out.println("The pokers are created successfully !");
	}
	
	public void sortPokerToSelect(){
		Collections.sort(pokersToSelect);	
	}
	
	public void show(){
		for(Poker p : pokersToSelect){
			System.out.print(p.color+":"+p.name+"; ");
		}
		System.out.println("");
	}
	
	public void mixPokerToSelect(){
		System.out.println("Starting to mix the pokers ...");
		Random random = new Random();
		
		for(int i=0; i<(pokersToSelect.size()); i++){
			
			int j = random.nextInt(pokersToSelect.size());
			
			Poker temp = pokersToSelect.get(i);
			
			pokersToSelect.set(i, pokersToSelect.get(j));
			pokersToSelect.set(j, temp);
		}
		
		
		
		System.out.println("The size of the pokersToSelect:  " + pokersToSelect.size());
	}
	
	public void createPlayer(){
		System.out.println("Start to create 2 players ...");

		for(int i=0; i<2; i++){
			System.out.println("Please enter the "+(i+1)+" th player's ID and name:");
			System.out.println("Enter the ID:");
			int id;
			
			while(true){
				try{
					Scanner input = new Scanner(System.in);
					id = input.nextInt();
					break;
				}catch(Exception e){
					System.out.println("Input must be an integer! Please enter the ID again: ");
				}
			}
			
			System.out.println("Enter the name:");
			
			String name = console.next();
			
			players.put(id, new Player(id, name));
		}
	}
	
	public void welcomePlayer(){
		Set<Integer> keySet = players.keySet();
		for(Integer key : keySet){
			System.out.println("----- Welcom player: "+ players.get(key).name);
		}
	}
	
	public void releasePokers(){
		System.out.println("Start to release the pokers:");
		int m=0;
		Set<Integer> keySet = players.keySet();
		
		for(int i=0; i<2; i++){
			
			for(Integer key : keySet){
				System.out.println("Player: " + players.get(key).name + " receive card: " + pokersToSelect.get(m).color + " " + pokersToSelect.get(m).name);
				players.get(key).pokers.add(pokersToSelect.get(m));
				++m;
			}
		}
		
		System.out.println("Releasing cards are finished! ");
		for(Integer key : keySet){
			System.out.println(players.get(key).name + "'s number of cards is "+players.get(key).pokers.size());
		}	
	}
	
	public void showPlayerPokers(){
		System.out.println("------------------------");
		System.out.println("Their cards are shown: ");
		
		Set<Integer> keySet = players.keySet();
	
		for(Integer key : keySet){
			System.out.println("The Player " + players.get(key).name+"'s cards are:");
			for(Poker poker : players.get(key).pokers){
				System.out.print(poker.color+" "+poker.name+",  ");
			}
			System.out.println("");
		}
	}
	
	public void startGame(){
		System.out.println(" ---------   Game Start   ---------");
		
		Set<Integer> keySet = players.keySet();
		for(Integer key : keySet){
			Poker pokerMax = new Poker();
			for(Poker poker : players.get(key).pokers){
				int re = poker.compareTo(pokerMax);
				if(re > 0){
					pokerMax = new Poker(poker.number, poker.color);
				}
			}
			players.get(key).pokerMax = new Poker(pokerMax.number, pokerMax.color);
		}
		
		List<Poker> pokerMaxList = new ArrayList<Poker>();
		for(Integer key : keySet){
			System.out.println("Player " + players.get(key).name + "'s maximum card is "+ players.get(key).pokerMax.color+" : " + players.get(key).pokerMax.name);
			pokerMaxList.add(players.get(key).pokerMax);
		}
		
		Poker pokerMax2 = new Poker();
		for(Poker poker : pokerMaxList){
			int re = poker.compareTo(pokerMax2);
			if(re > 0){
				pokerMax2 = new Poker(poker.number, poker.color);
			}
		}
		
		for(Integer key : keySet){
			if(players.get(key).pokers.contains(pokerMax2)){
				System.out.println("The winner is " + players.get(key).name + "!!!");
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		pokerGame pg = new pokerGame();
		pg.createPoker();
		System.out.println("-------- Before Sort -------");
		pg.show();
		
		System.out.println("-------- After Sort -------");
		pg.sortPokerToSelect();
		pg.show();
		
		pg.mixPokerToSelect();
		pg.show();
		System.out.println("Mixing the pokers is finished!");
		
		pg.createPlayer();
		
		pg.welcomePlayer();
		
		pg.releasePokers();

		pg.startGame();
		
		pg.showPlayerPokers();
		
	}

}
