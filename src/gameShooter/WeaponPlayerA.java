package gameShooter;

import engine.GameEntity;
import engine.Vector3;

public class WeaponPlayerA extends Weapon {
  
  public WeaponPlayerA(ShooterEntity entity) {
    super(entity);
    this.setFireDelay(150);
  }
  
  public boolean fire() {
    boolean b = super.fire();
    Vector3 v3 = new Vector3();
    ShooterEntity entity = this.getGameEntity();
    if (b) {
      // add bullet;
      GameEntity bullit;
      v3.setCoordinates(0, -(120/1000.0), 0);
      bullit = new Bullit01(entity.getGame(),entity.getScene(),v3);
      bullit.getPosition().x = entity.getPosition().x+5.5;
      bullit.getPosition().y = entity.getPosition().y-13;
      entity.getScene().getEntities().add(bullit);
    }
    return b;
  }
}
