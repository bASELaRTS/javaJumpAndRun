package gameShooter;

import engine.GameEngine;
import engine.GameGraphics;

public class SceneOutro extends Scene {
  private long m_timestamp;
  
  public SceneOutro(GameEngine engine) {
    super(engine,"sceneoutro");
  }
  
  public void activate() {
    this.m_timestamp = System.currentTimeMillis();
  }

  public void update() {
    super.update();
    
    long dtm = System.currentTimeMillis();
    if ((dtm-this.m_timestamp)>=2000) {
      this.getGameEngine().close();
    }
  }
  
  public void paint() {
    super.paint();
    
    GameGraphics graphics = this.getGameEngine().getGraphics();
    graphics.drawString("outro", 2, 12, GameGraphics.getColor(255, 255, 0, 0));
  }
}
