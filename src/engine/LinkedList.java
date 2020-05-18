package engine;

public class LinkedList {
	private LinkedListObject m_first;
	private LinkedListObject m_last;
	
	public LinkedList() {
		this.setFirst(null);
		this.setLast(null);
	}
	
	public void add(LinkedListObject llo) {
		if (this.getFirst()==null) {
			this.setFirst(llo);
			this.setLast(llo);
			
			this.getFirst().setNext(null);
			this.getFirst().setPrevious(null);
			
			this.getLast().setNext(null);
			this.getLast().setPrevious(null);
		} else {
			llo.setPrevious(this.getLast());
			llo.setNext(null);			
			this.getLast().setNext(llo);
			this.setLast(llo);
		}
	}
	
	public void remove(LinkedListObject llo) {
		LinkedListObject next;
		LinkedListObject prev;
		
		prev = llo.getPrevious();
		next = llo.getNext();
		
		if (prev==null) {
			this.setFirst(next);
		} else {
			prev.setNext(next);
		}
		
		if (next==null) {
			this.setLast(prev);
		} else {
			next.setPrevious(prev);
		}
		
		llo.setObject(null);
		llo = null;
	}
	
	public void clear() {
		LinkedListObject llo,next;
		llo = this.getFirst();
		while(llo!=null) {
			next = llo.getNext();
			this.remove(llo);
			llo = next;
		}
	}
	
	public int count() {
		int i;
		LinkedListObject llo;
		
		i = 0;
		llo = this.getFirst();
		while (llo!=null) {
			i++;
			llo = llo.getNext();
		}
		
		return i;
	}
	
	public void swap(LinkedListObject a, LinkedListObject b) {
	  LinkedListObject ap,an,bp,bn;
	  // temp
	  ap = a.getPrevious();
	  an = a.getNext();
	  bp = b.getPrevious();
	  bn = b.getNext();
    // relink
    if (ap!=null) {
      ap.setNext(b);
    } else {
      this.setFirst(b);
      b.setPrevious(null);
    }
    if (bn!=null) {
      bn.setPrevious(a);
    } else {
      this.setLast(a);
      a.setNext(null);
    }
	  // swap
	  a.setNext(bn);
	  a.setPrevious(bp);
	  
	  b.setNext(an);
	  b.setPrevious(ap);
	}

	private void setFirst(LinkedListObject llo) {this.m_first=llo;}
	public LinkedListObject getFirst() {return this.m_first;}
	
	private void setLast(LinkedListObject llo) {this.m_last=llo;}
	private LinkedListObject getLast() {return this.m_last;}
	
  public static void main(String[] args) {
    LinkedList list;
    list = new LinkedList();
    list.add(new LinkedListObject((String)"A"));
    list.add(new LinkedListObject((String)"B"));
    list.add(new LinkedListObject((String)"C"));
    list.add(new LinkedListObject((String)"D"));
    list.add(new LinkedListObject((String)"E"));
    
    String s;
    LinkedListObject o;
    s = "";
    o = list.getFirst();
    while(o!=null) {
      s+=(String)o.getObject();
      o = o.getNext();
    }
    System.out.println(s);
    
    list.swap(list.getFirst().getNext(), list.getFirst().getNext().getNext());
    
    s = "";
    o = list.getFirst();
    while(o!=null) {
      s+=(String)o.getObject();
      o = o.getNext();
    }
    System.out.println(s);    
  }	
}
