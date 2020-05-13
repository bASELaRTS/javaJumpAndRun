package engine;

public class GameInput {
  private InputKeyboard m_keyboard;
  private InputMouse m_mouse;
  
  public GameInput() {
    this.m_keyboard = new InputKeyboard();
    this.m_mouse = new InputMouse();
  }
  
  public InputKeyboard getKeyboard() {return this.m_keyboard;}
  public InputMouse getMouse() {return this.m_mouse;}
}
