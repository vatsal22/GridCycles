import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridCycles extends JPanel {
 private int screen;
 private int bombWare;
 private int glitch;
 private boolean music = true;
 private boolean items = true;
 private int rounds = 2;
 private boolean comP = false;

 // the ridiculously large number of buffered images
 private static BufferedImage p1End;
 private static BufferedImage p2End;
 private static BufferedImage tieEnd;
 private static BufferedImage helpOverlay;
 private static BufferedImage helpGameMenu;
 private static BufferedImage helpMainMenu;
 private static BufferedImage mainMenu_Play;
 private static BufferedImage mainMenu_Settings;
 private static BufferedImage mainMenu_Credits;
 private static BufferedImage mainMenu_Quit;
 private static BufferedImage mainMenu_Paused;
 private static BufferedImage settings_Music;
 private static BufferedImage settings_Music_Off;
 private static BufferedImage settings_Music_On;
 private static BufferedImage settings_Items;
 private static BufferedImage settings_Items_Off;
 private static BufferedImage settings_Items_On;
 private static BufferedImage settings_Rounds;
 private static BufferedImage settings_Rounds_1;
 private static BufferedImage settings_Rounds_3;
 private static BufferedImage settings_Rounds_9;
 private static BufferedImage settings_Rounds_17;
 private static BufferedImage settings_Players;
 private static BufferedImage settings_Players_2;
 private static BufferedImage settings_Players_1;
 private static BufferedImage settings_Back;
 private static BufferedImage credits;
 private static BufferedImage p1Shield0;
 private static BufferedImage p1Shield1;
 private static BufferedImage p1Shield2;
 private static BufferedImage p1Shield3;
 private static BufferedImage p2Shield0;
 private static BufferedImage p2Shield1;
 private static BufferedImage p2Shield2;
 private static BufferedImage p2Shield3;
 private static BufferedImage p1Ultra0;
 private static BufferedImage p1Ultra1;
 private static BufferedImage p2Ultra0;
 private static BufferedImage p2Ultra1;
 private static BufferedImage p1Disk0;
 private static BufferedImage p1Disk1;
 private static BufferedImage p1Disk2;
 private static BufferedImage p1Disk3;
 private static BufferedImage p1Disk4;
 private static BufferedImage p2Disk0;
 private static BufferedImage p2Disk1;
 private static BufferedImage p2Disk2;
 private static BufferedImage p2Disk3;
 private static BufferedImage p2Disk4;
 private static BufferedImage p1Wins0;
 private static BufferedImage p1Wins1;
 private static BufferedImage p1Wins2;
 private static BufferedImage p1Wins3;
 private static BufferedImage p1Wins4;
 private static BufferedImage p1Wins5;
 private static BufferedImage p1Wins6;
 private static BufferedImage p1Wins7;
 private static BufferedImage p1Wins8;
 private static BufferedImage p1Wins9;
 private static BufferedImage p2Wins0;
 private static BufferedImage p2Wins1;
 private static BufferedImage p2Wins2;
 private static BufferedImage p2Wins3;
 private static BufferedImage p2Wins4;
 private static BufferedImage p2Wins5;
 private static BufferedImage p2Wins6;
 private static BufferedImage p2Wins7;
 private static BufferedImage p2Wins8;
 private static BufferedImage p2Wins9;
 private static BufferedImage p1WinsTornament;
 private static BufferedImage p2WinsTornament;
 private Grid grid = new Grid();
 private PickUps powerUps = new PickUps(this, grid);
 private Player p1 = new Player(1, grid, powerUps, this, comP);
 private Player p2 = new Player(2, grid, powerUps, this, comP);

 private MusicPlayer mp = new MusicPlayer();

 public GridCycles() {
  screen = 10;
  this.glitch = 1;
  this.bombWare = 1;
  initImages();
  initKeyListener();
  mp.startBackgroundMusic();
 }

 // paint method pretty simple you get it
 @Override
 public void paint(Graphics g) {
  Graphics2D g2d = (Graphics2D) g;
  super.paint(g2d);
  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  if (screen == 10 || screen == 11 || screen == 12 || screen == 13)
   mainMenu(g2d);
  else if (screen == 2)
   game(g2d);
  else if (screen == 110 || screen == 111 || screen == 112 || screen == 113 || screen == 114)
   settings(g2d);
  else if (screen == 121)
   credits(g2d);
  else if (screen == 21)
   p1Winner(g2d);
  else if (screen == 22)
   p2Winner(g2d);
  else if (screen == 23)
   tieWinner(g2d);
  else if (screen == 20)
   g2d.drawImage(mainMenu_Paused, 0, 0, null);
  else if (screen == 25)
   g2d.drawImage(helpGameMenu, 0, 0, null);
  else if (screen == 26)
   g2d.drawImage(helpMainMenu, 0, 0, null);
  else if (screen == 211)
   g2d.drawImage(p1WinsTornament, 0, 0, null);
  else if (screen == 221)
   g2d.drawImage(p2WinsTornament, 0, 0, null);
 }

 public static void main(String[] args) throws InterruptedException {
  JFrame frame = new JFrame("Grid Cycles [V 1.0.1] - © CRANVA 2017");
  frame.setSize(1026, 640);
  GridCycles gc = new GridCycles();
  frame.add(gc);
  frame.setResizable(false);
  frame.setVisible(true);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  while (true) {
   gc.repaint();
   Thread.sleep(40);
  }
 }

 private void initKeyListener() {
  addKeyListener(new KeyListener() {
   @Override
   public void keyTyped(KeyEvent e) {

   }

   @Override
   public void keyReleased(KeyEvent e) {

   }

   @Override
   public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_W)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_A)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_S)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_D)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_Q)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_E)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W && screen != 2) {
     p2.keyPressed(e, screen);
     menuSwap(e);
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
     p2.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
     p2.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S && screen != 2) {
     p2.keyPressed(e, screen);
     menuSwap(e);
    }
    if (e.getKeyCode() == KeyEvent.VK_COMMA)
     p2.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_PERIOD)
     p2.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_SHIFT && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
     p1.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_M)
     p2.keyPressed(e, screen);
    if (e.getKeyCode() == KeyEvent.VK_SLASH && bombWare == 1 && screen==2)
     bombWare();
    if (e.getKeyCode() == KeyEvent.VK_CONTROL && glitch == 1
      && e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT && screen==2)
     glitch();
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
     resetAll();
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
     menuSwap(e);
    if (e.getKeyCode() == KeyEvent.VK_ENTER)
     menuSwap(e);
    if (e.getKeyCode() == KeyEvent.VK_H)
     menuSwap(e);
   }
  });
  setFocusable(true);
 }

 // this is the game yay yay yay yay yay yay
 private void game(Graphics g2d) {
  if (!powerUps.getIsInitialized() && items) {
   powerUps.initPickUps();
  }

  if (!items) {
   p1.noDisk();
   p2.noDisk();
  }

  p1.move();
  p2.move();

  boolean p1Alive = p1.testAlive();
  boolean p2Alive = p2.testAlive();

  if (p1Alive)
   p1.draw();
  if (p2Alive)
   p2.draw();

  if (p1Alive && p1.getIsSpeedBoosted()) {
   p1.move();
   p1Alive = p1.testAlive();
   if (p1.getIsAlive())
    p1.draw();
  }
  if (p2Alive && p2.getIsSpeedBoosted()) {
   p2.move();
   p2Alive = p2.testAlive();
   if (p2.getIsAlive())
    p2.draw();
  }

  grid.printGrid(g2d);
  updateHud(g2d);
  if (!p1Alive && !p2Alive) {
   mp.playDeath();
   screen = 23;
  } else if (p1Alive && !p2Alive) {
   mp.playDeath();
   p1.won();
   if (p1.getWins() >= rounds) {
    screen = 211;
   } else
    screen = 21;
  } else if (!p1Alive && p2Alive) {
   mp.playDeath();
   p2.won();
   if (p2.getWins() >= rounds) {
    screen = 221;
   } else
    screen = 22;
  }
 }

 // this one makes a pew sound isn't it great
 public void playPew() {
  mp.playPew();
 }

 public void playShieldHum() {
  mp.playShieldHum();
 }

 public void playWarpSpeed() {
  mp.playWarpSpeed();
 }

 // p1 won screen
 private void p1Winner(Graphics g2d) {
  grid.printGrid(g2d);
  g2d.drawImage(p1End, 0, 0, null);
  updateHud(g2d);
 }

 // p2 won screen
 private void p2Winner(Graphics g2d) {
  grid.printGrid(g2d);
  g2d.drawImage(p2End, 0, 0, null);
  updateHud(g2d);
 }

 // tie screen
 private void tieWinner(Graphics g2d) {
  grid.printGrid(g2d);
  g2d.drawImage(tieEnd, 0, 0, null);
  updateHud(g2d);
 }

 // between rounds
 private void reset() {
  powerUps.setInitStatus(false);
  grid.resetGrid();
  p1.resetPlayer();
  p2.resetPlayer();
  screen = 2;
 }

 // clears all walls
 private void bombWare() {
  grid.resetGrid();
  bombWare--;
  p1.initSpeedBuffTimer();
  p2.initSpeedBuffTimer();
 }

 // glitch item generates 100 random blocks and starts shield
 private void glitch() {
  int randy = 1;
  p1.activateShield();
  p2.activateShield();
  for (int i = 0; i < 100; i++) {
   for (int j = 0; j < 100; j++) {
    randy = (int) (Math.random() * 100 + 1);
    if (randy < 100)
     grid.drawOnGrid(1, i, j);
    else
     grid.drawOnGrid((int) (Math.random() * 2 + 2), i, j);
   }
  }
  glitch--;
 }

 // resets all things to original
 private void resetAll() {
  screen = 10;
  this.glitch = 1;
  this.bombWare = 1;
  grid.resetGrid();
  p1.resetPlayer();
  p2.resetPlayer();
  p1.resetWins();
  p2.resetWins();
 }

 // menu controls
 private void menuSwap(KeyEvent e) {
  // up is pressed menu stuff
  if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
   if (screen == 11)
    screen = 10;
   else if (screen == 12)
    screen = 11;
   else if (screen == 13)
    screen = 12;
   else if (screen == 10)
    screen = 13;
   else if (screen == 110)
    screen = 114;
   else if (screen == 111)
    screen = 110;
   else if (screen == 112)
    screen = 111;
   else if (screen == 113)
    screen = 112;
   else if (screen == 114)
    screen = 113;
  }
  // down is pressed menu stuff
  if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
   if (screen == 10)
    screen = 11;
   else if (screen == 11)
    screen = 12;
   else if (screen == 12)
    screen = 13;
   else if (screen == 13)
    screen = 10;
   else if (screen == 110)
    screen = 111;
   else if (screen == 111)
    screen = 112;
   else if (screen == 112)
    screen = 113;
   else if (screen == 113)
    screen = 114;
   else if (screen == 114)
    screen = 110;
  }
  // h is pressed
  if (e.getKeyCode() == KeyEvent.VK_H) {
   if (screen == 2)
    screen = 25;
   else if (screen == 25)
    screen = 2;
   else if ((screen == 10) || (screen == 11) || (screen == 12) || (screen == 13) || (screen == 110)
     || (screen == 111) || (screen == 112) || (screen == 113) || (screen == 114))
    screen = 26;
   else if (screen == 26)
    screen = 10;
  }
  // space is pressed menu stuff
  // __________________________________________________________________________________________
  if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
   if (screen == 2)
    screen = 20;
   else if (screen == 20)
    screen = 2;
   else if (screen == 10)
    reset();
   else if (screen == 11)
    screen = 110;
   else if (screen == 12)
    screen = 121;
   else if (screen == 13)
    System.exit(1);
   else if (screen == 23 || screen == 22 || screen == 21)
    reset();
   else if (screen == 110) {
    if (music) {
     music = false;
     mp.stopBackgroundMusic();
    } else {
     music = true;
     mp.startBackgroundMusic();
    }
   } else if (screen == 111) {
    if (items)
     items = false;
    else
     items = true;
   } else if (screen == 112) {
    if (rounds == 1)
     rounds = 2;
    else if (rounds == 2)
     rounds = 5;
    else if (rounds == 5)
     rounds = 9;
    else if (rounds == 9)
     rounds = 1;
   } else if (screen == 113) {
    if (comP) {
     comP = false;
     p2.comPFlip();
    } else {
     comP = true;
     p2.comPFlip();
    }
   } else if (screen == 114)
    screen = 10;
   else if (screen == 211 || screen == 221)
    resetAll();
   else if (screen == 121)
    screen = 10;
  }
 }

 // main menu stuffs
 private void mainMenu(Graphics g2d) {
  if (screen == 10)
   g2d.drawImage(mainMenu_Play, 0, 0, null);
  else if (screen == 11)
   g2d.drawImage(mainMenu_Settings, 0, 0, null);
  else if (screen == 12)
   g2d.drawImage(mainMenu_Credits, 0, 0, null);
  else if (screen == 13)
   g2d.drawImage(mainMenu_Quit, 0, 0, null);
 }

 // settings menu
 private void settings(Graphics g2d) {
  if (screen == 110)
   g2d.drawImage(settings_Music, 0, 0, null);
  else if (screen == 111)
   g2d.drawImage(settings_Items, 0, 0, null);
  else if (screen == 112)
   g2d.drawImage(settings_Rounds, 0, 0, null);
  else if (screen == 113)
   g2d.drawImage(settings_Players, 0, 0, null);
  else if (screen == 114)
   g2d.drawImage(settings_Back, 0, 0, null);
  if (music)
   g2d.drawImage(settings_Music_On, 0, 0, null);
  else
   g2d.drawImage(settings_Music_Off, 0, 0, null);
  if (items)
   g2d.drawImage(settings_Items_On, 0, 0, null);
  else
   g2d.drawImage(settings_Items_Off, 0, 0, null);
  if (rounds == 2)
   g2d.drawImage(settings_Rounds_3, 0, 0, null);
  else if (rounds == 5)
   g2d.drawImage(settings_Rounds_9, 0, 0, null);
  else if (rounds == 9)
   g2d.drawImage(settings_Rounds_17, 0, 0, null);
  else if (rounds == 1)
   g2d.drawImage(settings_Rounds_1, 0, 0, null);
  if (!comP)
   g2d.drawImage(settings_Players_2, 0, 0, null);
  else
   g2d.drawImage(settings_Players_1, 0, 0, null);
 }

 // credits screen
 private void credits(Graphics g2d) {
  g2d.drawImage(credits, 0, 0, null);
 }

 // hud generation and updating
 private void updateHud(Graphics g2d) {
  int m1 = p1.getDiscs();
  int m2 = p2.getDiscs();
  int n1 = p1.getShields();
  int n2 = p2.getShields();
  int w1 = p1.getWins();
  int w2 = p2.getWins();
  if (n1 == 0)
   g2d.drawImage(p1Shield0, 0, 0, null);
  else if (n1 == 1)
   g2d.drawImage(p1Shield1, 0, 0, null);
  else if (n1 == 2)
   g2d.drawImage(p1Shield2, 0, 0, null);
  else if (n1 == 3)
   g2d.drawImage(p1Shield3, 0, 0, null);
  if (n2 == 0)
   g2d.drawImage(p2Shield0, 0, 0, null);
  else if (n2 == 1)
   g2d.drawImage(p2Shield1, 0, 0, null);
  else if (n2 == 2)
   g2d.drawImage(p2Shield2, 0, 0, null);
  else if (n2 == 3)
   g2d.drawImage(p2Shield3, 0, 0, null);
  if (bombWare == 1 && items)
   g2d.drawImage(p2Ultra1, 0, 0, null);
  else
   g2d.drawImage(p2Ultra0, 0, 0, null);
  if (glitch == 1 && items)
   g2d.drawImage(p1Ultra1, 0, 0, null);
  else
   g2d.drawImage(p1Ultra0, 0, 0, null);
  if (m1 == 0 || !items)
   g2d.drawImage(p1Disk0, 0, 0, null);
  else if (m1 == 3)
   g2d.drawImage(p1Disk3, 0, 0, null);
  else if (m1 == 2)
   g2d.drawImage(p1Disk2, 0, 0, null);
  else if (m1 == 1)
   g2d.drawImage(p1Disk1, 0, 0, null);
  else if (m1 == 4)
   g2d.drawImage(p1Disk4, 0, 0, null);
  if (m2 == 0 || !items)
   g2d.drawImage(p2Disk0, 0, 0, null);
  else if (m2 == 3)
   g2d.drawImage(p2Disk3, 0, 0, null);
  else if (m2 == 2)
   g2d.drawImage(p2Disk2, 0, 0, null);
  else if (m2 == 1)
   g2d.drawImage(p2Disk1, 0, 0, null);
  else if (m2 == 4)
   g2d.drawImage(p2Disk4, 0, 0, null);
  if (w1 == 0)
   g2d.drawImage(p1Wins0, 0, 0, null);
  else if (w1 == 1)
   g2d.drawImage(p1Wins1, 0, 0, null);
  else if (w1 == 2)
   g2d.drawImage(p1Wins2, 0, 0, null);
  else if (w1 == 3)
   g2d.drawImage(p1Wins3, 0, 0, null);
  else if (w1 == 4)
   g2d.drawImage(p1Wins4, 0, 0, null);
  else if (w1 == 5)
   g2d.drawImage(p1Wins5, 0, 0, null);
  else if (w1 == 6)
   g2d.drawImage(p1Wins6, 0, 0, null);
  else if (w1 == 7)
   g2d.drawImage(p1Wins7, 0, 0, null);
  else if (w1 == 8)
   g2d.drawImage(p1Wins8, 0, 0, null);
  else if (w1 == 9)
   g2d.drawImage(p1Wins9, 0, 0, null);
  if (w2 == 0)
   g2d.drawImage(p2Wins0, 0, 0, null);
  else if (w2 == 1)
   g2d.drawImage(p2Wins1, 0, 0, null);
  else if (w2 == 2)
   g2d.drawImage(p2Wins2, 0, 0, null);
  else if (w2 == 3)
   g2d.drawImage(p2Wins3, 0, 0, null);
  else if (w2 == 4)
   g2d.drawImage(p2Wins4, 0, 0, null);
  else if (w2 == 5)
   g2d.drawImage(p2Wins5, 0, 0, null);
  else if (w2 == 6)
   g2d.drawImage(p2Wins6, 0, 0, null);
  else if (w2 == 7)
   g2d.drawImage(p2Wins7, 0, 0, null);
  else if (w2 == 8)
   g2d.drawImage(p2Wins8, 0, 0, null);
  else if (w2 == 9)
   g2d.drawImage(p2Wins9, 0, 0, null);
 }

 // image initiation
 private static void initImages() {
  try {
   helpOverlay = ImageIO.read(new File("HelpScreen_MenuOverlay.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Back = null;
  }
  try {
   helpGameMenu = ImageIO.read(new File("HelpScreen_Help.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Back = null;
  }
  try {
   helpMainMenu = ImageIO.read(new File("HelpScreen_Menu.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Back = null;
  }
  try {
   settings_Back = ImageIO.read(new File("Settings_Back.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Back = null;
  }
  try {
   settings_Rounds = ImageIO.read(new File("Settings_Rounds.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Rounds = null;
  }
  try {
   settings_Rounds_1 = ImageIO.read(new File("Settings_Rounds_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Rounds_1 = null;
  }
  try {
   settings_Rounds_3 = ImageIO.read(new File("Settings_Rounds_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Rounds_3 = null;
  }
  try {
   settings_Rounds_9 = ImageIO.read(new File("Settings_Rounds_9.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Rounds_9 = null;
  }
  try {
   settings_Rounds_17 = ImageIO.read(new File("Settings_Rounds_17.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Rounds_17 = null;
  }
  try {
   settings_Music = ImageIO.read(new File("Settings_Music.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Music = null;
  }
  try {
   settings_Music_Off = ImageIO.read(new File("Settings_Music_Off.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Music_Off = null;
  }
  try {
   settings_Music_On = ImageIO.read(new File("Settings_Music_On.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Music_On = null;
  }
  try {
   settings_Items = ImageIO.read(new File("Settings_Items.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Items = null;
  }
  try {
   settings_Items_Off = ImageIO.read(new File("Settings_Items_Off.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Items_Off = null;
  }
  try {
   settings_Items_On = ImageIO.read(new File("Settings_Items_On.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Items_On = null;
  }
  try {
   settings_Players = ImageIO.read(new File("Settings_Mode.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Players = null;
  }
  try {
   settings_Players_1 = ImageIO.read(new File("Settings_Mode_1Player.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Players_1 = null;
  }
  try {
   settings_Players_2 = ImageIO.read(new File("Settings_Mode_2Player.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   settings_Players_2 = null;
  }
  try {
   p1WinsTornament = ImageIO.read(new File("P1_WinsTorrnament.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1WinsTornament = null;
  }
  try {
   p2WinsTornament = ImageIO.read(new File("P2_WinsTorrnament.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2WinsTornament = null;
  }
  try {
   mainMenu_Paused = ImageIO.read(new File("MainMenu_Paused.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   mainMenu_Paused = null;
  }
  try {
   p1End = ImageIO.read(new File("P1_End.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1End = null;
  }
  try {
   p2End = ImageIO.read(new File("P2_End.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2End = null;
  }
  try {
   tieEnd = ImageIO.read(new File("Tie_End.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   tieEnd = null;
  }
  try {
   mainMenu_Play = ImageIO.read(new File("MainMenu_Play.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   mainMenu_Play = null;
  }
  try {
   credits = ImageIO.read(new File("Credits.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   credits = null;
  }
  try {
   mainMenu_Settings = ImageIO.read(new File("MainMenu_Settings.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   mainMenu_Settings = null;
  }
  try {
   mainMenu_Credits = ImageIO.read(new File("MainMenu_Credits.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   mainMenu_Credits = null;
  }

  try {
   mainMenu_Quit = ImageIO.read(new File("MainMenu_Quit.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   mainMenu_Quit = null;
  }
  try {
   p1Shield0 = ImageIO.read(new File("P1_Shield_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Shield0 = null;
  }
  try {
   p1Shield1 = ImageIO.read(new File("P1_Shield_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Shield1 = null;
  }
  try {
   p1Shield2 = ImageIO.read(new File("P1_Shield_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Shield2 = null;
  }
  try {
   p1Shield3 = ImageIO.read(new File("P1_Shield_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Shield3 = null;
  }
  try {
   p2Shield0 = ImageIO.read(new File("P2_Shield_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Shield0 = null;
  }
  try {
   p2Shield1 = ImageIO.read(new File("P2_Shield_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Shield1 = null;
  }
  try {
   p2Shield2 = ImageIO.read(new File("P2_Shield_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Shield2 = null;
  }
  try {
   p2Shield3 = ImageIO.read(new File("P2_Shield_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Shield3 = null;
  }
  try {
   p2Ultra0 = ImageIO.read(new File("P2_Untra_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Ultra0 = null;
  }
  try {
   p2Ultra1 = ImageIO.read(new File("P2_Untra_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Ultra1 = null;
  }
  try {
   p1Ultra0 = ImageIO.read(new File("P1_Untra_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Ultra0 = null;
  }
  try {
   p1Ultra1 = ImageIO.read(new File("P1_Untra_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Ultra1 = null;
  }
  try {
   p1Disk0 = ImageIO.read(new File("P1_DataDisk_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Disk0 = null;
  }
  try {
   p1Disk1 = ImageIO.read(new File("P1_DataDisk_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Disk1 = null;
  }
  try {
   p1Disk2 = ImageIO.read(new File("P1_DataDisk_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Disk2 = null;
  }
  try {
   p1Disk3 = ImageIO.read(new File("P1_DataDisk_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Disk3 = null;
  }
  try {
   p1Disk4 = ImageIO.read(new File("P1_DataDisk_4.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Disk4 = null;
  }
  try {
   p2Disk0 = ImageIO.read(new File("P2_DataDisk_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Disk0 = null;
  }
  try {
   p2Disk1 = ImageIO.read(new File("P2_DataDisk_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Disk1 = null;
  }
  try {
   p2Disk2 = ImageIO.read(new File("P2_DataDisk_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Disk2 = null;
  }
  try {
   p2Disk3 = ImageIO.read(new File("P2_DataDisk_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Disk3 = null;
  }
  try {
   p2Disk4 = ImageIO.read(new File("P2_DataDisk_4.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Disk4 = null;
  }
  try {
   p1Wins0 = ImageIO.read(new File("P2_Wins_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins0 = null;
  }
  try {
   p1Wins1 = ImageIO.read(new File("P2_Wins_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins1 = null;
  }
  try {
   p1Wins2 = ImageIO.read(new File("P2_Wins_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins2 = null;
  }
  try {
   p1Wins3 = ImageIO.read(new File("P2_Wins_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins3 = null;
  }
  try {
   p1Wins4 = ImageIO.read(new File("P2_Wins_4.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins4 = null;
  }
  try {
   p1Wins5 = ImageIO.read(new File("P2_Wins_5.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins5 = null;
  }
  try {
   p1Wins6 = ImageIO.read(new File("P2_Wins_6.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins6 = null;
  }
  try {
   p1Wins7 = ImageIO.read(new File("P2_Wins_7.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins7 = null;
  }
  try {
   p1Wins8 = ImageIO.read(new File("P2_Wins_8.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins8 = null;
  }
  try {
   p1Wins9 = ImageIO.read(new File("P2_Wins_9.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p1Wins9 = null;
  }
  try {
   p2Wins0 = ImageIO.read(new File("P1_Wins_0.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins0 = null;
  }
  try {
   p2Wins1 = ImageIO.read(new File("P1_Wins_1.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins1 = null;
  }
  try {
   p2Wins2 = ImageIO.read(new File("P1_Wins_2.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins2 = null;
  }
  try {
   p2Wins3 = ImageIO.read(new File("P1_Wins_3.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins3 = null;
  }
  try {
   p2Wins4 = ImageIO.read(new File("P1_Wins_4.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins4 = null;
  }
  try {
   p2Wins5 = ImageIO.read(new File("P1_Wins_5.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins5 = null;
  }
  try {
   p2Wins6 = ImageIO.read(new File("P1_Wins_6.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins6 = null;
  }
  try {
   p2Wins7 = ImageIO.read(new File("P1_Wins_7.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins7 = null;
  }
  try {
   p2Wins8 = ImageIO.read(new File("P1_Wins_8.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins8 = null;
  }
  try {
   p2Wins9 = ImageIO.read(new File("P1_Wins_9.png"));
  } catch (IOException e) {
   System.out.println("Error: Image not found");
   p2Wins9 = null;
  }
 }
}