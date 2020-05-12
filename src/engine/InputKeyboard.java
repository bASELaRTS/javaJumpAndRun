package engine;

import java.util.Vector;

import com.sun.glass.events.KeyEvent;

public class InputKeyboard {
	private Vector<Key> m_keys;
	
	public InputKeyboard() {
	  this.m_keys = new Vector<Key>();
	  
	  this.add(new Key(KeyEvent.VK_ESCAPE));
    this.add(new Key(KeyEvent.VK_SPACE));
    this.add(new Key(KeyEvent.VK_UP));
    this.add(new Key(KeyEvent.VK_DOWN));
    this.add(new Key(KeyEvent.VK_LEFT));
    this.add(new Key(KeyEvent.VK_RIGHT));
	  
		this.setKeys(false);
	}

	public void add(Key key) {
	  this.m_keys.add(key);
	}
	
	private void setKeys(boolean state) {
		int i;
		Key key;
		for(i=0;i<this.m_keys.size();i++) {
		  key = this.m_keys.elementAt(i);
		  key.setState(state);
		}
	}
	
	public Key find(int code) {
	  Key key;
	  for(int i=0;i<this.m_keys.size();i++) {
	    key = this.m_keys.elementAt(i);
	    if (key.getCode()==code) {
	      return key;
	    }
	  }
	  return null;
	}
	
	public void setState(int code, boolean state) {
	  Key key = this.find(code);
	  if (key!=null) {
	    key.setState(state);
	  }
	}
	
	public boolean getState(int code) {
	  Key key = this.find(code);
	  if (key!=null) {
	    return key.getState();
	  }
	  return false;
	}
	
	public class Key {
	  private int m_code;
	  private boolean m_state;
	  
	  public Key(int code) {
	    this.setCode(code);
	    this.setState(false);
	  }
	  
	  public void setCode(int i) {this.m_code=i;}
	  public int getCode() {return this.m_code;}
	  public void setState(boolean b) {this.m_state=b;}
	  public boolean getState() {return this.m_state;}
	}
}
