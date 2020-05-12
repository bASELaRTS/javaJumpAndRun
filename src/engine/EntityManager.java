package engine;

public class EntityManager extends LinkedList {
  public EntityManager() {
    super();
  }
  
  public void add(GameEntity entity) {
    this.add(new LinkedListObject(entity));
  }
}
