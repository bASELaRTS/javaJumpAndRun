package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
  private static final long serialVersionUID = 1L;

  private GameEngine m_engine;
  private double m_zoom;
  
  private boolean m_threadRunning;
  private Thread m_thread;
  
  public GamePanel(GameEngine engine) {
    this.create(engine, 1.0);
  }
  
  public GamePanel(GameEngine engine, double zoom) {
    this.create(engine, zoom);
  }
  
  private void create(GameEngine engine, double zoom) {
    this.m_engine = engine;
    this.m_zoom = zoom;
    
    int w = (int)(this.m_engine.getWidth()*this.m_zoom);
    int h = (int)(this.m_engine.getHeight()*this.m_zoom);
    
    this.setPreferredSize(new Dimension(w,h));
    
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
          this.m_engine.loop();
          this.repaint();      
          Thread.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }      
    }
  }

  public GameEngine getGameEngine() {return this.m_engine;}
}
