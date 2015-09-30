package testweek2;

import processing.core.*;

public class Test1 extends PApplet{

	private String URL = "https://upload.wikimedia.org/wikipedia/commons/b/b4/JPEG_example_JPG_RIP_100.jpg";
	private PImage backgroundImg;
		
	public void setup() {

        size(200,200);
        
        backgroundImg = loadImage(URL,"jpg");
        
        System.out.println("The program ends.");
        
	}
	
	public void draw(){
		
		backgroundImg.resize(width, 0);
		
		image(backgroundImg, 0, 0);
		
		// set the color changing to different time
		
		int color[];
		
		color = setcolor(second());
		
		fill(color[0],color[1],color[2]);
		
		ellipse(width/4, height/5, width/5, height/5);
		
	}
	
	public int[] setcolor(float seconds){
		
		int[] rgb = new int[3];
		
		float diff = Math.abs(seconds-30);
		
		float ratio = diff/30;
		
		rgb[0] = (int) (ratio*255);
		rgb[1] = (int) (ratio*255);
		rgb[2] = 0;
		
		return rgb;
		
	}

}
