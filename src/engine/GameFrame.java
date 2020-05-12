package engine;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements WindowListener {
  private static final long serialVersionUID = 1L;

  private GamePanel m_panel;
  
  public GameFrame(GameEngine engine) {
    this.create(engine, 1.0);
  }
  
  public GameFrame(GameEngine engine, double zoom) {
    this.create(engine, zoom);
  }
  
  private void create(GameEngine engine, double zoom) {
    this.m_panel = new GamePanel(this,engine,zoom);
    
    this.addWindowListener(this);
    this.setTitle(engine.getKey());
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(this.m_panel,BorderLayout.CENTER);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);    
  }
  
  public void windowActivated(WindowEvent arg0) {}
  public void windowClosed(WindowEvent arg0) {}
  public void windowClosing(WindowEvent arg0) {
    if (this.m_panel!=null) {
      if (this.m_panel.getGameEngine()!=null) {
        this.m_panel.getGameEngine().close();
      }
    }
  }
  public void windowDeactivated(WindowEvent arg0) {}
  public void windowDeiconified(WindowEvent arg0) {}
  public void windowIconified(WindowEvent arg0) {}
  public void windowOpened(WindowEvent arg0) {}

  public GamePanel getPanel() {return this.m_panel;}
  
  public static void main(String[] args) {
    new GameFrame(new GameEngine("GameEngine",320,240));
  }
}
