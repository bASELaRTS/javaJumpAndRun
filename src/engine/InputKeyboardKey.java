package engine;

public class InputKeyboardKey {
  private int m_code;
  private boolean m_state;
  
  public InputKeyboardKey(int code) {
    this.setCode(code);
    this.setState(false);
  }
  
  public void setCode(int i) {this.m_code=i;}
  public int getCode() {return this.m_code;}
  public void setState(boolean b) {this.m_state=b;}
  public boolean getState() {return this.m_state;}
}