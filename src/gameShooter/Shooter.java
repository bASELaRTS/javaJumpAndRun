package gameShooter;

import engine.GameEngine;
import engine.GameFrame;
import engine.GameGraphics;

public class Shooter extends GameEngine {  
  private SceneManager m_scenes;
  
  public Shooter() {
    super("Shooter",120,160);
    this.getInput().getKeyboard().setAutoAddKeyCodes(true);

    this.m_scenes = new SceneManager();
    this.m_scenes.add(new SceneImage(this,GameGraphics.getImage("data/bas.png"),2000,"sceneintro","scenemainmenu"));
    this.m_scenes.add(new SceneImage(this,GameGraphics.getImage("data/bas.png"),2000,"sceneoutro","sceneexit"));
    this.m_scenes.add(new SceneExit(this));
    this.m_scenes.add(new SceneMainMenu(this));
    this.m_scenes.add(new SceneGame(this));
  }
    
  public void update() {
    this.getScenes().update();
  }
  
  public void paint() {
    GameGraphics g = this.getGraphics();
    g.clear();    
    this.getScenes().paint();
  }
  
  public SceneManager getScenes() {return this.m_scenes;}
  
  public static void main(String[] args) {
    new GameFrame(new Shooter(),2);
  }
}
