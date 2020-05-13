package gameShooter;

import engine.GameEngine;
import engine.GameGraphics;

public class SceneIntro extends Scene {
  private long m_timestamp;
  
  public SceneIntro(GameEngine engine) {
    super(engine,"sceneintro");
  }
  
  public void activate() {
    this.m_timestamp = System.currentTimeMillis();
  }

  public void update() {
    super.update();
    
    long dtm = System.currentTimeMillis();
    if ((dtm-this.m_timestamp)>=2000) {
      Scene scene = ((Shooter)this.getGameEngine()).getScenes().getScene("scenemainmenu");
      ((Shooter)this.getGameEngine()).getScenes().activate(scene);
    }
  }
  
  public void paint() {
    super.paint();
    
    GameGraphics graphics = this.getGameEngine().getGraphics();
    graphics.drawString("intro", 2, 12, GameGraphics.getColor(255, 255, 0, 0));
  }
}
