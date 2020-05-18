package gameShooter;

import engine.GameEngine;
import engine.GameGraphics;

public class HUDLives extends ShooterEntity {
  private int m_lives;
  
  public HUDLives(GameEngine engine, Scene scene) {
    super(engine,scene,"hudlives");
    this.getPosition().y = 4;
  }
  
  public void update() {
    Shooter engine = (Shooter)this.getGame();
    SceneGame scene = (SceneGame)engine.getScenes().getScene("scenegame");
    Player player = (Player)scene.getEntities().find("player");
    this.m_lives = player.getLives();
  }

  public void paint() {
    GameGraphics g = this.getGame().getGraphics();
    int w = 8;
    int h = 8;
    int x = this.getGame().getWidth()-4-w;
    int y = (int)this.getPosition().y;
    int i,c;
    c = GameGraphics.getColor(255, 64, 255, 0);
    for(i=0;i<this.m_lives;i++) {
      g.fillRect(x, y, w, h, c);
      x-=(w+2);
    }
  }
}
