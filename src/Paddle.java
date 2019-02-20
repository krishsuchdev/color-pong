import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Paddle extends Canvas {
	public int padX;
	public int padY;
	
	private int x, y, w, h, xSpeed;
	
	public Paddle( int ex, int wy, int wd, int ht, int xs) {
		x = ex;
		y = wy;
		w = wd;
		h = ht;		
		xSpeed = xs;
	}

	public void paint( Graphics window, int i, Color cc ) {
		if (i == 1) {
			window.setColor(cc);
		} else {
			window.setColor(cc);
		}
		window.fillRect( x, y, w, h);
		
		padX = x;
		padY = y;
	}
	
	public void moveUp() {
		for (int c = 0; c < xSpeed; c++) {
	  		y = y - 1;
		}
		
		padX = x;
		padY = y;
	}
	
	public void moveDown() {
	  	for (int c = 0; c < xSpeed; c++) {
	  		y = y + 1;
		}
		
		padX = x;
		padY = y;
	}
	
	public int getX() {
		return padX;
	}
	public int getY() {
		return padY;
	}

}