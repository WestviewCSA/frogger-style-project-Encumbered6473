import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	//for any debugging code we add public static boolean debugging = true
	public static boolean debugging = true; 
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	


		
		
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
	
	
	Charmander charmander = new Charmander();
	Charmander charmander2 = new Charmander(150,150);
	SquirtleScroller squirtle = new SquirtleScroller(150, 50);
	Background background = new Background();
	Water water = new Water();
	

	
	//a row of CharmanderScroller objects!
	Water[] row1 = new Water[1];
	CharmanderScroller[] row2 = new CharmanderScroller[5];
	CharmanderScroller2[] row3 = new CharmanderScroller2[5];
	SquirtleScroller[] row4 = new SquirtleScroller[8];
	ArrayList<CharmanderScroller> row1List = new ArrayList <CharmanderScroller>();
	ArrayList<LifeImage> lives = new ArrayList <LifeImage>();
	
	//frame width/height
	static int width = 600;
	static int height = 800;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		

		//paint the other objects on the screen
		water.paint(g);
		charmander2.paint(g);
		squirtle.paint(g);
		background.paint(g);
		charmander.paint(g);
		
		
		boolean riding = false;
		 
		/*Collision Detction */
		//for each Ghost object in row 1 array
		
		for (SquirtleScroller obj : row4 ) {
			//invoke the collided method fo ryour
			//class - pass the main character
			//as your arugment
			if (obj.collided(charmander)) {
				System.out.println("hit");
				
				charmander.resetPosition();
				
				
				
			}
		}
		
		for (Water obj : row1) {
			//invoke the collided method fo ryour
			//class - pass the main character
			//as your arugment
			if (obj.collided(charmander)) {
				System.out.println("hit");
				
				charmander.resetPosition();
			}
		}
		
		for (CharmanderScroller obj : row2) {
			//invoke the collided method fo ryour
			//class - pass the main character
			//as your arugment
			if (obj.collided(charmander)) {
				
				System.out.println("safe");
				
			}
		}
	
		
		for(CharmanderScroller obj: row2) {
			if(obj.collided(charmander)) {
				charmander.setVx(charmander.getVx() + obj.getVx());
				riding = true;
				break;
		
			}
		}
		
		
		for (CharmanderScroller2 obj : row3) {
			//invoke the collided method fo ryour
			//class - pass the main character
			//as your arugment
			if (obj.collided(charmander)) {
				System.out.println("safe");
				charmander.setVx(charmander.getVx() + obj.getVx());
				riding = true;
				break;
		
			}
		}
		
		//main character stops moving if not on rideable object
		if(!riding && charmander.getY() > 120) {
			riding = false; 
			charmander.setVx(0);
			 
	
		}
		
		
		for(CharmanderScroller obj : row1List) { // for every Charmander object in row1List
			obj.paint(g);
		}
		
		//drawing the life counter images
		for(LifeImage obj: lives) {
			//draw the LifeImage objects
			obj.paint(g);
			
			
		}
		
		
		// win and lose variables
			int livesCount = 6; 
			boolean gameWon = false; 
			
			 
			
			if (charmander.getY() <= 800) {
		         gameWon = true; 
		     }

	
	
		
	
		
			
	
	
		
			
		
		
		// have the row1 objects paint on the screen!
		
				// for each obj in row3
				for(Water obj : row1) {
					obj.paint(g);
}
				// for each obj in row1 
				for(CharmanderScroller obj : row2) {
					obj.paint(g);
	}	
				
				// for each obj in row2
				for(CharmanderScroller2 obj : row3) {
					obj.paint(g);
				}
		
				// for each obj in row3
				for(SquirtleScroller obj : row4) {
					obj.paint(g);
	}
				charmander.paint(g);
	
		
		}
		
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.green);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
//		backgroundMusic.play();

		/* 
		 * Setup any 1D array here! - create the objects that go in them ;0
		 */
		
		
		
		//traverse the array 
		for(int i = 0; i < row1.length; i++) {
			row1[i] = new Water();
		}
		for(int i = 0; i < row2.length; i++) {
			row2[i] = new CharmanderScroller( i*400 , 260);
		}
		
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new CharmanderScroller2( i*550 , 125);
		}
		
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new SquirtleScroller( i*250 , 500);
		}
		
		for(int i = 0; i < 10; i++) { 
			//run the body 10x 
			this.row1List.add(new CharmanderScroller(i*400, 260));
		}
		
		//start with 6 attempts
		for(int i = 0; i < 6; i++) {
			
			this.lives.add(new LifeImage(i*40, 10));
			
			
		}
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==87) {
			//move character up
			charmander.move(0);
		}else if (arg0.getKeyCode()==83) {
			//move character down
			charmander.move(1);
		
		}else if (arg0.getKeyCode()==65) {
			//move character down
			charmander.move(2);
			
		}else if (arg0.getKeyCode()==68) {
			//move character down
			charmander.move(3);
		
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
