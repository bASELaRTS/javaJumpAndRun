package gameShooter;

import java.awt.event.KeyEvent;

import engine.EntityManager;
import engine.GameEngine;
import engine.GameFrame;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.InputKeyboardKey;

public class Shooter extends GameEngine {
  private EntityManager m_entities;
  
  private boolean m_debug;
  
  public Shooter() {
    super("Shooter",120,160);
    
    this.m_entities = new EntityManager();
    this.m_entities.add(new Background(this));
    this.m_entities.add(new Player(this));
    
    this.m_debug = false;
    this.getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_F1));
  }
    
  public void update() {
    InputKeyboard keyboard = this.getInput().getKeyboard();    
    if (keyboard.getState(KeyEvent.VK_ESCAPE)) {
      this.close();
    }
    
    if (keyboard.getState(KeyEvent.VK_F1)) {
      keyboard.setState(KeyEvent.VK_F1, false);      
      this.m_debug=!this.m_debug;
    }
    
    this.m_entities.update();
  }
  
  public void paint() {
    GameGraphics g = this.getGraphics();
    g.clear();
        
    this.m_entities.paint();
    
    if (this.m_debug) {
      int c = GameGraphics.getColor(255, 255, 216, 0);
      g.drawString("FPS : " + this.getTimer().getFPS(), 2, 12, c);
      g.drawString("Entities : " + this.getEntities().count(), 2, 22, c);
    }
  }
  
  public EntityManager getEntities() {return this.m_entities;}
  
  public static void main(String[] args) {
    new GameFrame(new Shooter(),2);
  }
}
