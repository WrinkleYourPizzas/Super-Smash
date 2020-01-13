import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChooseMapMenu implements KeyListener {	//KeyListener is like ActionListener but for keyboard
	private int rectX=300,rectY=150,rectWidth=300,rectHeight=200;
	private int currentSelection = 0;
	private int nextSelection = 1;
	private int prevSelection = 5;
	private int nextNextSelection=2;
	private int prevPrevSelection=4;
	private boolean onSettings = false;
	private final static int height = 600;	//Window dimensions
	private final static int width = 900;
	private Font font = null;
	private final Image redCircle = Toolkit.getDefaultToolkit().createImage("redCircle.png").getScaledInstance(56, 56, java.awt.Image.SCALE_SMOOTH);

	private final Image settingsIcon = Toolkit.getDefaultToolkit().createImage("settingsIcon.png").getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	private final Image starImage = Toolkit.getDefaultToolkit().createImage("superSmashDifficultyStars.png").getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
	private boolean ended=false;
	
	//CENTER OF SCREEN IS 450
	public JFrame frame;	
	private JPanel panel = new canvas();	
	private static Map[] allMapArray = new Map[] {//Toolkit.getDefaultToolkit().createImage("FINALDESTINATION.png").getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH),
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP0.png").getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(140, 320 ,610, 5, false, false)}, 1, "FInAl DEstInAtIOn"),		
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP1.png").getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(388, 200 ,120, 5, false, false),new Platform(250, 290 ,120, 5, false, false),new Platform(530, 290 ,120, 5, false, false),new Platform(190, 370 ,525, 5, false, false)}, 1, "SUnrIsE"),		
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP2.png").getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(400, 92 ,101, 5, false, false),new Platform(280, 170 ,102, 5, false, false),new Platform(519, 170 ,102, 5, false, false),new Platform(400, 245 ,101, 5, false, false),new Platform(158, 245 ,107, 5, false, false),new Platform(637, 245 ,105, 5, false, false),new Platform(90, 315 ,710, 5, false, false)}, 2, "BIg BAttlEfIEld"),	
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP3.png").getScaledInstance(width, height-20, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(535, 170 ,135, 5, false, false),new Platform(60, 420 ,770, 5, false, false)}, 2, "ArEnA FErOx"),		
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP4.png").getScaledInstance(width, height-20, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(85, 220 ,160, 5, false, false),new Platform(30, 450 ,120, 5, false, false),new Platform(632, 255 ,268, 5, false, false),new Platform(405, 375 ,495, 5, false, false)}, 5, "SUzAkU CAstlE"),		
			new Map(Toolkit.getDefaultToolkit().createImage("SMASHMAP5.png").getScaledInstance(width, height-20, java.awt.Image.SCALE_SMOOTH), new Platform[] {new Platform(65, 245 ,196, 5, false, false),new Platform(640, 245 ,196, 5, false, false),new Platform(194, 350 ,170, 5, false, false),new Platform(530, 345 ,172, 5, false, false),new Platform(324, 445 ,250, 5, false, false)}, 3, "NOrfAIr"),		

	};
	public ChooseMapMenu() {

		frame = new JFrame("Choose Your Map");	//Frame stuff
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.addKeyListener(this);

		String fName = "superFont.ttf";
		File fontFile = new File(fName);		

		try {
			Font tempfont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			font = tempfont.deriveFont((float)(40));

		} catch (FontFormatException e) {
		} catch (IOException e) {
		}

		panel.setLayout(new BorderLayout());	

		frame.add(panel);

		frame.setLocationRelativeTo(null);	//Make the frame visible
		frame.setVisible(true);	
		Thread drawSquares = new Thread(new Runnable() {	//The main loop
			public void run() {	

				while (!ended) {	
					frame.repaint();	//Refresh frame and panel
					panel.repaint();
					try {Thread.sleep(17);} catch (Exception ex) {}	//10 millisecond delay between each refresh
				}
				new Physics();
				frame.dispose();

			}
		});	
		drawSquares.start();	//Start the main loop

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode()+" "+KeyEvent.VK_ENTER);
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(!onSettings) {
				if(currentSelection < 5) {
					currentSelection++;
				}
				else currentSelection = 0;
				if(currentSelection < 5) {
					nextSelection=currentSelection+1;
				}else nextSelection=0;
				if(currentSelection > 0)prevSelection = currentSelection-1;
				else prevSelection = 5;
				if(nextSelection+1<=5) {
					nextNextSelection = nextSelection+1;
				} else nextNextSelection =0;
				if(prevSelection-1>=0) {
					prevPrevSelection = prevSelection-1;
				} else prevPrevSelection =5;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP) {
			onSettings = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			onSettings = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(!onSettings) {
				if(currentSelection > 0) 
					currentSelection--;
				else currentSelection = 5;
				if(currentSelection < 5) {
					nextSelection=currentSelection+1;
				}else nextSelection=0;
				if(currentSelection > 0)prevSelection = currentSelection-1;
				else prevSelection = 5;
				if(nextSelection+1<=5) {
					nextNextSelection = nextSelection+1;
				} else nextNextSelection =0;
				if(prevSelection-1>=0) {
					prevPrevSelection = prevSelection-1;
				} else prevPrevSelection =5;
			}
			
		}
		else if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(!onSettings) {
				Physics.currentMap=allMapArray[currentSelection];
				ended=true;
			}
			else {
				new mapSettings();
				frame.dispose();
			}
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void clearAll() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	public static void main(String[] args) {	//Call the graphics constructor
		new ChooseMapMenu();
	}

	public class canvas extends JPanel {	//Make a new JPanel that you can draw objects onto (Can't draw stuff anywhere you want onto normal JPanels)
		public void paintComponent(Graphics g) {
			super.paintComponent(g);	//Call paintComponent from the overlord JPanel
			FontMetrics metrics = g.getFontMetrics(font);
			g.setColor(new Color(34,33,34));
			g.fillRect(0, 0, width, height);
			g.setFont(font);
			g.setColor(new Color(255,255,255));
			g.drawString("ChoOse Your Map", 250, rectY-40);
			BufferedImage img = new BufferedImage((int)(rectWidth/1.5), (int)(rectHeight/1.5),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = img.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int rule = AlphaComposite.SRC_OVER;
			Composite comp = AlphaComposite.getInstance(rule , (float) 0.6 );
			g2.setComposite(comp);
			//				 physics2.allMapArray[nextSelection].draw(g2, rectX+250,rectY+35,(int)(rectWidth/1.5),(int)(rectHeight/1.5));

			g2.drawImage(allMapArray[nextSelection].getBack(), 0, 0,(int)(rectWidth/1.5), (int)(rectHeight/1.5),null);
			g.drawImage(img, rectX+250, rectY+35, null);

			BufferedImage img1 = new BufferedImage((int)(rectWidth/1.5), (int)(rectHeight/1.5),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g3 = img1.createGraphics();
			g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int rule2 = AlphaComposite.SRC_OVER;
			Composite comp2 = AlphaComposite.getInstance(rule2 , (float) 0.6 );
			g3.setComposite(comp2);

			g3.drawImage(allMapArray[prevSelection].getBack(), 0, 0,(int)(rectWidth/1.5), (int)(rectHeight/1.5),null);
			g.drawImage(img1, rectX-150, rectY+35, null);


			BufferedImage img2 = new BufferedImage((int)(rectWidth/1.8)-65, (int)(rectHeight/1.8),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g4 = img2.createGraphics();
			g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int rule3 = AlphaComposite.SRC_OVER;
			Composite comp3 = AlphaComposite.getInstance(rule3 , (float) 0.3 );
			g4.setComposite(comp3);

			g4.drawImage(allMapArray[prevPrevSelection].getBack(), 0, 0,(int)(rectWidth/1.8), (int)(rectHeight/1.8),null);
			g.drawImage(img2, rectX-250, rectY+45, null);


			BufferedImage img3 = new BufferedImage((int)(rectWidth/1.8)-65, (int)(rectHeight/1.8),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g5 = img3.createGraphics();
			g5.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int rule4 = AlphaComposite.SRC_OVER;
			Composite comp4 = AlphaComposite.getInstance(rule4 , (float) 0.3 );
			g5.setComposite(comp4);

			g5.drawImage(allMapArray[nextNextSelection].getBack(), 0-65, 0,(int)(rectWidth/1.8), (int)(rectHeight/1.8),null);
			g.drawImage(img3, rectX+450, rectY+45, null);
			g.setColor(new Color(0,0,0));

			g.drawRect(rectX-150, rectY+35, (int)(rectWidth/1.5), (int)(rectHeight/1.5));
			g.drawRect(rectX-150+1, rectY+35+1, (int)(rectWidth/1.5)-2, (int)(rectHeight/1.5)-2);
			g.drawRect(rectX-150-1, rectY+35-1, (int)(rectWidth/1.5)+2, (int)(rectHeight/1.5)+2);
			g.drawRect(rectX+250, rectY+35, (int)(rectWidth/1.5), (int)(rectHeight/1.5));
			g.drawRect(rectX+250+1, rectY+35+1, (int)(rectWidth/1.5)-2, (int)(rectHeight/1.5)-2);
			g.drawRect(rectX+250-1, rectY+35-1, (int)(rectWidth/1.5)+2, (int)(rectHeight/1.5)+2);

			allMapArray[currentSelection].draw(g, rectX,rectY,rectWidth,rectHeight);
			if(!onSettings) {
				g.setColor(new Color(255,0,0));
			}
			g.drawRect(rectX, rectY, rectWidth, rectHeight);
			g.drawRect(rectX+1, rectY+1, rectWidth-2, rectHeight-2);
			g.drawRect(rectX-1, rectY-1, rectWidth+2, rectHeight+2);

			g.setColor(new Color(255,255,255));
			g.drawString(allMapArray[currentSelection].getTitle(), (int)((width - metrics.stringWidth(allMapArray[currentSelection].getTitle())) / 2), rectY+rectHeight+70);
			BufferedImage difficultyStars = new BufferedImage(50*allMapArray[currentSelection].getDifficulty(), 50,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g6 = difficultyStars.createGraphics();
			g6.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			int rule5 = AlphaComposite.SRC_OVER;
			int x =0, y=0;
			Composite comp5 = AlphaComposite.getInstance(rule5 , (float) 1 );
			g6.setComposite(comp5);
			for(int i = 0; i < allMapArray[currentSelection].getDifficulty(); i++) {
				g6.drawImage(starImage, x, y,null);
				x+=50;
			}
			g.drawImage(difficultyStars, (int)((900-difficultyStars.getWidth())/2), rectY+rectHeight+100, null);
			g.drawImage(settingsIcon, 850, 0,null);
			if(onSettings) {
				g.drawImage(redCircle, 850-3, 0-3, null);
			}
		}
	}
}