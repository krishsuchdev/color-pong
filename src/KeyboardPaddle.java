import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.util.*;
import java.awt.MouseInfo;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.*;
import java.awt.Canvas;

public class KeyboardPaddle extends JPanel implements Runnable, KeyListener {}
	public Paddle it1;
	public Paddle it2;
	public Pumpkin p1;
	private boolean[] keys;
	
	private boolean anything = false;
	
	int paddleSize;
	
	int b = 0;
	
	int width, height;
	
	Color one;
	Color two;
	String oone;
	String ttwo;
	
	public KeyboardPaddle(int width, int height) {
		this.width = width;
		this.height = height;
		
		ArrayList<String> cn = new ArrayList<String>();
		ArrayList<Color> cc = new ArrayList<Color>();
		
		int r;
		
		int runs = 0;
		
		do {
			for (int k = 0; k <= 1; k++){
				r = (int) (Math.random() * 9);
				if (r == 0) {
					cc.add(Color.RED);
					cn.add("Red");
				}
				else if (r == 1) {
					cc.add(Color.BLUE);
					cn.add("Blue");
				} else if (r == 2) {
					cc.add(Color.GREEN);
					cn.add("Green");
				} else if (r == 3) {
					cc.add(Color.YELLOW);
					cn.add("Yellow");
				} else if (r == 4) {
					cc.add(Color.ORANGE);
					cn.add("Orange");
				} else if (r == 5) {
					cc.add(Color.CYAN);
					cn.add("Cyan");
				} else if (r == 6) {
					cc.add(Color.WHITE);
					cn.add("White");
				} else if (r == 7) {
					cc.add(Color.MAGENTA);
					cn.add("Magenta");
				} else if (r == 8) {
					cc.add(Color.GRAY);
					cn.add("Gray");
				}
		}
		
		if (runs >= 0) {
			cc.add(Color.RED);
			cn.add("Red");
			cc.add(Color.BLUE);
			cn.add("Blue");
		}
		one = cc.get(0);
		two = cc.get(1);
		oone = cn.get(0);
		ttwo = cn.get(1);
		runs++;
		} while (oone.equals(ttwo));
		
		setBackground(Color.BLACK);
		
		paddleSize = 150;
		
		it1 = new Paddle( width - 100, 200, 30, paddleSize, 100);
		it2 = new Paddle( 100, 200, 30, paddleSize, 100);
		p1 = new Pumpkin(50,50,50,50,it1);
		
		keys = new boolean[4];
		
		setFocusable(true);
		
		addKeyListener(this);
		
		new Thread(this).start();		
	}

	public void paint( Graphics window ) {
		window.setColor(Color.BLACK);
		window.fillRect( 0,0, width, height);
		
		if( keys[0] == true ) {
			it1.moveDown();
			anything = true;
			keys[0] = false;
		}
		if( keys[1] == true ) {
			it1.moveUp();
			anything = true;
			keys[1] = false;			
		}
		if( keys[2] == true ) {
			it2.moveDown();
			anything = true;
			keys[2] = false;
		}
		if( keys[3] == true ) {
			it2.moveUp();
			anything = true;
			keys[3] = false;			
		}
		
		Font nFont100 = new Font("Arial", Font.BOLD, 20);
			window.setFont( nFont100 );
			window.setColor(Color.WHITE);
			window.drawString("Bounces: " + b, width / 2 - 50, 30);
		
		for (int yy = 0; yy < 1; yy++) {
		it1.paint(window, 1, two);	
		it2.paint(window, 2, one);
		p1.paint(window);
		p1.keepInBounds();
		p1.changePos();
		p1.keepInBounds();
		if (p1.checkGameStatus() == false) {
			window.setColor(Color.BLACK);
			window.fillRect( 0,0, 1000, 1000);
			
			Color winner = p1.getColor();
			
			Font nFont = new Font("Arial", Font.BOLD, 30);
			window.setFont( nFont );
			window.setColor(Color.WHITE);
			window.drawString("Game Over!", width / 2 - 100, 400);
			
			window.setFont( nFont );
			window.setColor(winner);
			if (one == winner) {
				window.drawString(oone + " Wins!", width / 2 - 100, 450);
			} else if (two == winner) {
				window.drawString(ttwo + " Wins!", width / 2 - 100, 450);
			} else {
				window.setColor(Color.GRAY);
				window.drawString("Nobody Wins!", width / 2 - 100, 450);
			}
		} else {
			if (!anything) {
				window.setColor(Color.WHITE);
				Font nFont3 = new Font("Arial", Font.BOLD, 40);
				window.setFont( nFont3 );
				window.drawString("Color Pong", width / 2 - 100, 400);
				
				window.setColor(one);
				Font nFont4 = new Font("Arial", Font.BOLD, 25);
				window.setFont( nFont4 );
				window.drawString("Use W key to move up and S key to move down", width / 2 - 300, 500);
				
				window.setColor(two);
				Font nFont5 = new Font("Arial", Font.BOLD, 25);
				window.setFont( nFont5 );
				window.drawString("Use UP ARROW key to move up and DOWN ARROW key to move down", width / 2 - 400, 550);
			}
		}
		}
		if (Math.abs(it1.getX() - p1.getX()) < p1.speed && p1.getY() >= it1.getY() - 5 && p1.getY() <= it1.getY() + 5 + paddleSize) {
			p1.bounceLeft(two);
			b++;
		}
		if (Math.abs(it2.getX() - p1.getX()) < p1.speed && p1.getY() >= it2.getY() - 5 && p1.getY() <= it2.getY() + 5 + paddleSize) {
			p1.bounceRight(one);
			b++;
		}
	}

    public void keyPressed(KeyEvent e) {
    	if( e.getKeyCode() == KeyEvent.VK_DOWN )
			keys[0] = true;
		if( e.getKeyCode() == KeyEvent.VK_UP )
			keys[1] = true;	
		if( e.getKeyCode() == KeyEvent.VK_S)
			keys[2] = true;
		if( e.getKeyCode() == KeyEvent.VK_W)
			keys[3] = true;	
		repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_DOWN)
			keys[0] = true;
		if(e.getKeyCode() == KeyEvent.VK_UP)
			keys[1] = true;	
		if(e.getKeyCode() == KeyEvent.VK_S)
			keys[2] = true;
		if(e.getKeyCode() == KeyEvent.VK_W)
			keys[3] = true;	
		repaint();
    }
	
	public void run() {

		try {
			while( true ) {	
			   Thread.sleep(3);
			   repaint();
			}
		}
		catch( Exception e ) {
			
		}

	}
}