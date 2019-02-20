import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.MouseInfo;
import java.util.concurrent.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Pumpkin extends Canvas {
	boolean up = false;
	boolean right = true;
	
	private int x, y, w, h;
	int c = 220;
	int red = (int)(Math.random()*256);
	int green = (int)(Math.random()*256);
	int blue = (int)(Math.random()*256);
	Color newColor = new Color(red, green, blue);
	Color glow = new Color(c,c,0);
	int speed = (int)(1);
	Color cc = Color.WHITE;
	
	public Pumpkin( int ex, int wy, int wd, int ht, Paddle it1) {
		x = ex;
		y = wy;
		w = wd;
		h = ht;		
	}

	public void paint( Graphics window ) {
		
		Color newColor = new Color(red, green, blue);
		
		window.setColor(cc);
		window.fillOval(x,y,w,h);
		
		/*//window.setColor(Color.RED); //The Box
		//window.drawRect(x,y,w,h);
		
		window.setColor(glow);
		window.fillRect(x+w/3,y+(h/3*2),w/2,h/12);
		
		window.setColor(glow);
		window.fillRect(x+w/4,y+h/4,w/8,h/8);
		
		window.setColor(glow);
		window.fillRect(x+w/4*3,y+h/4,w/8,h/8);
		
		window.setColor(Color.GREEN);
		window.fillRect(x+w/2,y,w/5,h/10);*/
		
	}
	
	public void changePos() {
		int s = speed;
		if (right) {
	  		x = x + s;
		} else {
			x = x - s;
		}
		if (up) {
	  		y = y - s;
		} else {
			y = y + s;
		}
	  	if (c < 300) {
	  		c++;
	  	}
	}
	
	public void keepInBounds() {
		if (y >= 700 - h) {
			up = true;
			y = y - 1;
		}
		if (y <= h) {
			up = false;
			y = y + 1;
		}
	}
	
	public boolean checkGameStatus() {
		if (x >= 1200 - w) {
			return false;
		}
		if (x <= 0 + w) {
			return false;
		}
		return true;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return cc;
	}
	
	public void bounceLeft(Color one) {
		cc = one;
		right = false;
		x = x - 1;
	}
	
	public void bounceRight(Color two) {
		cc = two;
		right = true;
		x = x + 1;
		speed++;
	}
	
}