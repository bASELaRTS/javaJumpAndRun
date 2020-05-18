package engine;

public class EntityManager extends LinkedList {
  public EntityManager() {
    super();
  }
  
  public void add(GameEntity entity) {
    this.add(new LinkedListObject(entity));
  }
  
  public void update() {
    LinkedListObject o,n;
    GameEntity e;
    
    o = this.getFirst();
    while(o!=null) {
      n = o.getNext();
      e = (GameEntity)o.getObject();
      if (e.getRemove()) {
        e = null;
        this.remove(o);
      } else {
        e.update();
      }
      o = n;
    }
  }
  
  public void paint() {
    LinkedListObject o,n;
    GameEntity e;
    
    o = this.getFirst();
    while(o!=null) {
      n = o.getNext();
      e = (GameEntity)o.getObject();
      if ((!e.getRemove())&&(e.getVisible())) {        
        e.paint();
      }
      o = n;
    }    
  }
  
  public GameEntity find(String key) {
    LinkedListObject o;
    GameEntity e;
    
    o = this.getFirst();
    while(o!=null) {
      e = (GameEntity)o.getObject();
      if (e.getKey().equals(key)) {
        return e;
      }
      o = o.getNext();
    }
    return null;
  }
}
