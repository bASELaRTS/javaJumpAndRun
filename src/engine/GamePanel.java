package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {
  private static final long serialVersionUID = 1L;

  private GameFrame m_frame;
  private GameEngine m_engine;
  private double m_zoom;
  
  private boolean m_threadRunning;
  private Thread m_thread;
  
  public GamePanel(GameFrame frame, GameEngine engine) {    
    this.create(frame, engine, 1.0);
  }
  
  public GamePanel(GameFrame frame, GameEngine engine, double zoom) {
    this.create(frame, engine, zoom);
  }
  
  private void create(GameFrame frame, GameEngine engine, double zoom) {
    this.m_frame = frame;
    this.m_engine = engine;
    this.m_zoom = zoom;
    
    int w = (int)(this.m_engine.getWidth()*this.m_zoom);
    int h = (int)(this.m_engine.getHeight()*this.m_zoom);
    
    this.setPreferredSize(new Dimension(w,h));
    this.setFocusable(true);
    this.addKeyListener(this);    
    
    this.m_thread = new Thread(this);
    this.m_thread.start();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    
    int w,h;
    BufferedImage image;
    if (this.getGameEngine()!=null) {
      w = (int)(this.getGameEngine().getWidth()*this.m_zoom);
      h = (int)(this.getGameEngine().getHeight()*this.m_zoom);
      image = this.getGameEngine().getGraphics().getImage();      
      if (image!=null) {
        g.drawImage(image, 0, 0, w, h, null);
      }
    }
  }
    
  public void run() {
    this.m_threadRunning=true;
    while(this.m_threadRunning) {
      if (this.m_engine!=null) {
        try {
          if (!this.m_engine.isClosed()) {
            this.m_engine.loop();
            this.repaint();      
          } else {
            this.m_threadRunning = false;
          }
          
          Thread.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }      
    }
    
    this.m_frame.dispatchEvent(new WindowEvent(this.m_frame, WindowEvent.WINDOW_CLOSING));
  }

  public void keyPressed(KeyEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getKeyboard().setState(arg0.getKeyCode(), true);
    }
  }
  public void keyReleased(KeyEvent arg0) {
    if (this.m_engine!=null) {
      this.m_engine.getInput().getKeyboard().setState(arg0.getKeyCode(), false);
    }    
  }
  public void keyTyped(KeyEvent arg0) {}

  public GameEngine getGameEngine() {return this.m_engine;}
}
