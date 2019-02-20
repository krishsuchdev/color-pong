import java.util.*;
import javax.swing.*;

public class GraphicsRunner extends JFrame {
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 700;

	public GraphicsRunner() {
		super("Color Pong");

		setSize(WIDTH,HEIGHT);
		
		getContentPane().add(new KeyboardPaddle(WIDTH,HEIGHT) );

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		GraphicsRunner run = new GraphicsRunner();
	}
}