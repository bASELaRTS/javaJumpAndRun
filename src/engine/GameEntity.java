package engine;

public class GameEntity {
	private String m_key;
	
	private Vector3 m_position;
	
	private int m_width;
	private int m_height;
	
	private boolean m_visible;
	private boolean m_remove;
	
	private GameEngine m_game;
	
	public GameEntity(GameEngine engine, String key) {
		this.m_position = new Vector3();
		
    this.setGame(engine);
		this.setKey(key);
		this.getPosition().setCoordinates(0, 0, 0);
		this.setSize(0, 0);
		this.setVisible(true);
	}
	
	public void update() {}
	public void paint() {}
	
	public void setRemove(boolean b) {this.m_remove=b;}
	public boolean getRemove() {return this.m_remove;}
	
	public void setVisible(boolean b) {this.m_visible=b;}
	public boolean getVisible() {return this.m_visible;}
	
	public void setKey(String s) {this.m_key=s;}
	public String getKey() {return this.m_key;}
	
	public Vector3 getPosition() {return this.m_position;}
	
	protected void setWidth(int w) {this.m_width=w;}
	protected void setHeight(int h) {this.m_height=h;}
	protected void setSize(int w, int h) {
		this.setWidth(w);
		this.setHeight(h);
	}
	public int getWidth() {return this.m_width;}
	public int getHeight() {return this.m_height;}
	
	public void setGame(GameEngine g) {this.m_game=g;}
	public GameEngine getGame() {return this.m_game;}
}
