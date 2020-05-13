package gameShooter;

import engine.GameEngine;

public class SceneExit extends Scene {
  public SceneExit(GameEngine engine) {
    super(engine,"sceneexit");
  }
  
  public void update() {
    super.update();
    this.getGameEngine().close();
  }
}
