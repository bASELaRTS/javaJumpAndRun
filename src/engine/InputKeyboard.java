package engine;

import java.awt.event.KeyEvent;
import java.util.Vector;

public class InputKeyboard {
	private Vector<InputKeyboardKey> m_keys;
	
	public InputKeyboard() {
	  this.m_keys = new Vector<InputKeyboardKey>();
	  
	  this.add(new InputKeyboardKey(KeyEvent.VK_ESCAPE));
    this.add(new InputKeyboardKey(KeyEvent.VK_SPACE));
    this.add(new InputKeyboardKey(KeyEvent.VK_UP));
    this.add(new InputKeyboardKey(KeyEvent.VK_DOWN));
    this.add(new InputKeyboardKey(KeyEvent.VK_LEFT));
    this.add(new InputKeyboardKey(KeyEvent.VK_RIGHT));
	  
		this.setKeys(false);
	}

	public void add(InputKeyboardKey key) {
	  InputKeyboardKey temp = this.find(key.getCode());
	  if (temp==null) {
	    this.m_keys.add(key);
	  }
	}
	
	private void setKeys(boolean state) {
		int i;
		InputKeyboardKey key;
		for(i=0;i<this.m_keys.size();i++) {
		  key = this.m_keys.elementAt(i);
		  key.setState(state);
		}
	}
	
	public InputKeyboardKey find(int code) {
	  InputKeyboardKey key;
	  for(int i=0;i<this.m_keys.size();i++) {
	    key = this.m_keys.elementAt(i);
	    if (key.getCode()==code) {
	      return key;
	    }
	  }
	  return null;
	}
	
	public void setState(int code, boolean state) {
	  InputKeyboardKey key = this.find(code);
	  if (key!=null) {
	    key.setState(state);
	  }
	}
	
	public boolean getState(int code) {
	  InputKeyboardKey key = this.find(code);
	  if (key!=null) {
	    return key.getState();
	  }
	  return false;
	}
}
