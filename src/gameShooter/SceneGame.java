package gameShooter;

import java.awt.event.KeyEvent;

import engine.GameEngine;
import engine.InputKeyboard;

public class SceneGame extends Scene {
  
  public SceneGame(GameEngine engine) {
    super(engine,"scenegame");
        
    this.getEntities().add(new Background(this.getGameEngine(),this));
    this.getEntities().add(new Player(this.getGameEngine(),this));

    this.getEntities().add(new HUDHealthBar(this.getGameEngine(),this));
    this.getEntities().add(new HUDLives(this.getGameEngine(),this));
  }  
  
  public void update() {
    super.update();
    
    Shooter game = (Shooter)this.getGameEngine();
    InputKeyboard keyboard = game.getInput().getKeyboard();
    if (keyboard.getState(KeyEvent.VK_ESCAPE)) {
      keyboard.setKeys(false);
      Scene s = game.getScenes().getScene("scenemainmenu");
      game.getScenes().activate(s);
    }
  }
}
