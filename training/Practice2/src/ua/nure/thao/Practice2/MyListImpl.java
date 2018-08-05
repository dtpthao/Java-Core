package ua.nure.thao.Practice2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyListImpl implements MyList, ListIterable{
	
	private List<Object> list;
	private int currentIndex;
	private boolean lastChange;
	
	public MyListImpl() {
		list = new ArrayList<Object>();
	}
	
	@Override
	public void add(Object e) {
		if (e != null) { 
			list.add(e);
		}
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[list.size()];
		int i = 0;
		for (Object o : list) {
			array[i++] = o;
		}
		return array;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean contains(Object o) {
		for (Object obj : list) {
			if (obj.equals(o)) 
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList c) {
		if (c instanceof  MyListImpl) {
			
			for (Object o : ((MyListImpl)c).list) {
				if (this.contains(o) == false) 
					return false;
			}
			return true;
		}
		return false;
	}
	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(Object o : list) {
			sb.append(o).append(", ");
		}
		if (list.size() > 0)
			sb.delete(sb.length() - 2, sb.length());
		return sb.append("]").toString();
    }
	
	public Iterator<Object> iterator() {
	    return new IteratorImpl();
	}
	 
	private class IteratorImpl implements Iterator<Object> {
		
		public IteratorImpl() {
			MyListImpl.this.currentIndex = -1;
			MyListImpl.this.lastChange = false;
		}
		
		public boolean hasNext() {
			return MyListImpl.this.currentIndex 
					< MyListImpl.this.list.size() - 1; 
		}
	 
		public Object next() {
			if (this.hasNext()) {
				MyListImpl.this.lastChange = true;
				return MyListImpl.this
						.list.get(++MyListImpl.this.currentIndex);
			}
			return null;
		}
	      
		public void remove() {
			if (MyListImpl.this.lastChange == false) 
				throw new IllegalStateException();
			MyListImpl.this.remove(MyListImpl.this
					.list.get(MyListImpl.this.currentIndex--));
			MyListImpl.this.lastChange = false;
		}    	
	}
	
	public ListIterator listIterator() {
	    return new ListIteratorImpl();
	}
	
	private class ListIteratorImpl extends IteratorImpl implements ListIterator {
		
		@Override
		public boolean hasPrevious() {
			return MyListImpl.this.currentIndex > -1;
		}

		@Override
		public Object previous() {
			if (this.hasPrevious()) {
				MyListImpl.this.lastChange = true;
				return MyListImpl.this
						.list.get(MyListImpl.this.currentIndex--);
			}
			return null;
		}

		@Override
		public void set(Object e) {
			if (MyListImpl.this.lastChange == false) 
				throw new IllegalStateException();
			MyListImpl.this.list.set(--MyListImpl.this.currentIndex, e);
			MyListImpl.this.lastChange = false;		
		}
	}
	
}
