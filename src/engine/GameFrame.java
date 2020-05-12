package engine;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
  private static final long serialVersionUID = 1L;

  private GamePanel m_panel;
  
  public GameFrame(GameEngine engine) {
    this.create(engine, 1.0);
  }
  
  public GameFrame(GameEngine engine, double zoom) {
    this.create(engine, zoom);
  }
  
  private void create(GameEngine engine, double zoom) {
    this.m_panel = new GamePanel(engine,zoom);
    
    this.setTitle(engine.getKey());
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(this.m_panel,BorderLayout.CENTER);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);    
  }
  
  public GamePanel getPanel() {return this.m_panel;}
  
  public static void main(String[] args) {
    new GameFrame(new GameEngine("GameEngine",320,240));
  }
}
