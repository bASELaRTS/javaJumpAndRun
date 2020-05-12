package engine;

public class GameInput {
  private InputKeyboard m_keyboard;
  
  public GameInput() {
    this.m_keyboard = new InputKeyboard();
  }
  
  public InputKeyboard getKeyboard() {return this.m_keyboard;}
}
