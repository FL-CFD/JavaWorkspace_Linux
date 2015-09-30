package com.example;

public class Poker implements Comparable<Poker>{
	public int number;
	public String name;
	public String color;
	
	public int colorNum;
	public int numberNum;
	
	public Poker(){}
	
	public Poker(int number, String color){
		this.number = number;
		this.color = color;
		
		if(number>=2 && number<=10){
			this.name = String.valueOf(number);
		}else if(number == 1){
			this.name = "A";
		}else if(number == 11){
			this.name = "J";
		}else if(number == 12){
			this.name = "Q";
		}else if(number == 13){
			this.name = "K";
		}
		
		if      (color.equals("Spade")){
			this.colorNum = 3;
		}else if(color.equals("Heart")){
			this.colorNum = 2;
		}else if(color.equals("Diamond")){
			this.colorNum = 1;
		}else if(color.equals("Club")){
			this.colorNum = 0;
		}
		
		if(number == 1){
			this.numberNum = 13;
		}else if(number>=2 && number<=13){
			this.numberNum = number-1;
		}
	}



	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Poker))
			return false;
		Poker other = (Poker) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public int compareTo(Poker p){
		
		int index1;
		Integer thisnumberNum = new Integer(this.numberNum);
		Integer    pnumberNum = new Integer(   p.numberNum);
		
		Integer thiscolorNum = new Integer(this.colorNum);
		Integer    pcolorNum = new Integer(   p.colorNum);
		
		index1 = thisnumberNum.compareTo(pnumberNum);
		if(index1 != 0){
			return index1;
		}else{
			return thiscolorNum.compareTo(pcolorNum);
		}
		
	}
}
