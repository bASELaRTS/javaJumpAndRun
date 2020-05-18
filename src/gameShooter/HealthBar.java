package gameShooter;

import engine.GameEngine;
import engine.GameGraphics;

public class HealthBar extends ShooterEntity {
  private double m_percentage;
  
  public HealthBar(GameEngine engine, Scene scene) {
    super(engine,scene,"healthbar");
    this.getPosition().setCoordinates(4, 4, 0);
    this.setWidth(6);
    this.setHeight(this.getGame().getHeight()-10);
    this.m_percentage = 0;    
  }
  
  public void update() {
    SceneGame scene = (SceneGame) this.getScene();
    Player player = (Player)scene.getEntities().find("player");
    this.m_percentage = player.getHealth()/100.0;
  }
  
  public void paint() {
    int i,c;
    int x,y;
    int w,h;
    GameGraphics g = this.getGame().getGraphics();
    
    x = (int)this.getPosition().x;
    y = (int)this.getPosition().y;
    w = this.getWidth();
    h = this.getHeight();
    i = (int)(h*this.m_percentage);
    
    c = GameGraphics.getColor(128, 255, 216, 0);
    g.fillRect(x,y + h-i, w, i, c);
    
    c = GameGraphics.getColor(255, 255, 255, 255);
    g.drawRect(x,y,w,h,c);
  }
}
