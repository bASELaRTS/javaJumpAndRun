package gameShooter;

import java.awt.image.BufferedImage;

import engine.GameEngine;
import engine.GameGraphics;
import engine.Sprite;

public class SceneImage extends Scene {
  private int m_delay;
  private long m_timestamp;
  private String m_nextSceneName;
  private Sprite m_logo;
  private int m_logoX;
  private int m_logoY;
  
  public SceneImage(GameEngine engine, BufferedImage image, int delay, String sceneName, String nextSceneName) {
    super(engine,sceneName);
    
    this.m_delay = delay;
    this.m_nextSceneName = nextSceneName;
    this.m_logo = new Sprite();
    this.m_logo.add(image);    
  }
  
  public void activate() {
    this.m_timestamp = System.currentTimeMillis();
    this.m_logoX = (int)((this.getGameEngine().getWidth()-this.m_logo.getImage().getWidth())*0.5);
    this.m_logoY = (int)((this.getGameEngine().getHeight()-this.m_logo.getImage().getHeight())*0.5);
  }

  public void update() {
    super.update();
    
    long dtm = System.currentTimeMillis();
    if ((dtm-this.m_timestamp)>=this.m_delay) {
      Scene scene = ((Shooter)this.getGameEngine()).getScenes().getScene(this.m_nextSceneName);
      ((Shooter)this.getGameEngine()).getScenes().activate(scene);
    }
  }
  
  public void paint() {
    super.paint();
    
    GameGraphics graphics = this.getGameEngine().getGraphics();
    graphics.drawImage(this.m_logo.getImage(),this.m_logoX,this.m_logoY);
  }
}
