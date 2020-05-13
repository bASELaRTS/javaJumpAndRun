package gameShooter;

import com.sun.glass.events.KeyEvent;

import engine.GameEngine;
import engine.GameGraphics;
import engine.InputKeyboard;
import engine.InputKeyboardKey;

public class SceneMainMenu extends Scene {
  public SceneMainMenu(GameEngine engine) {
    super(engine,"scenemainmenu");
    
    this.getEntities().add(new Background(this.getGameEngine(),this));
    
    this.getGameEngine().getInput().getKeyboard().add(new InputKeyboardKey(KeyEvent.VK_ENTER));
  }
  
  public void update() {
    super.update();
    Scene scene;
    Shooter engine = (Shooter)this.getGameEngine();
    InputKeyboard keyboard = this.getGameEngine().getInput().getKeyboard();
    if (keyboard.getState(KeyEvent.VK_ENTER)) {
      keyboard.setKeys(false);
      scene = engine.getScenes().getScene("scenegame");
      engine.getScenes().activate(scene);
    } else if (keyboard.getState(KeyEvent.VK_ESCAPE)) {
      keyboard.setKeys(false);
      scene = engine.getScenes().getScene("sceneoutro");
      engine.getScenes().activate(scene);
    }    
  }
  
  public void paint() {
    super.paint();
    
    GameGraphics graphics = this.getGameEngine().getGraphics();
    graphics.drawString("MainMenu", 2, 12, GameGraphics.getColor(255, 255, 0, 0));
  }
}
