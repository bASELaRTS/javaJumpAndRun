package gameShooter;

import java.awt.event.KeyEvent;

import engine.GameEngine;
import engine.GameFrame;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.InputKeyboardKey;

public class Shooter extends GameEngine {
  private SceneManager m_scenes;
  
  private boolean m_debug;
  
  public Shooter() {
    super("Shooter",120,160);

    this.m_scenes = new SceneManager();
    this.m_scenes.add(new SceneIntro(this));
    this.m_scenes.add(new SceneOutro(this));
    this.m_scenes.add(new SceneMainMenu(this));
    this.m_scenes.add(new SceneGame(this));
    
    this.m_debug = false;
    this.getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_F1));
  }
    
  public void update() {
    InputKeyboard keyboard = this.getInput().getKeyboard();    

    if (keyboard.getState(KeyEvent.VK_F1)) {
      keyboard.setState(KeyEvent.VK_F1, false);      
      this.m_debug=!this.m_debug;
    }
    
    this.getScenes().update();
  }
  
  public void paint() {
    GameGraphics g = this.getGraphics();
    g.clear();
    
    this.getScenes().paint();
        
    if (this.m_debug) {
      int c = GameGraphics.getColor(255, 255, 216, 0);
      g.drawString("FPS : " + this.getTimer().getFPS(), 2, 12, c);
    }
  }
  
  public SceneManager getScenes() {return this.m_scenes;}
  
  public static void main(String[] args) {
    new GameFrame(new Shooter(),2);
  }
}
