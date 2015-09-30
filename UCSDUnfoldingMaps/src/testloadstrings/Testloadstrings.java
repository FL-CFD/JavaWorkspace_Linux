package testloadstrings;

import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;


public class Testloadstrings extends PApplet{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Testloadstrings a = new Testloadstrings();
		
		a.print();

	}
	
	public void print(){
		String lines[] = loadStrings ("https://processing.org/overview/index.html");
		System.out.println("there are " + lines.length + " lines");
		for (int i = 0 ; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
	}

}