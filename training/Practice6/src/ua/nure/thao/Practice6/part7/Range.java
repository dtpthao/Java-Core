package ua.nure.thao.Practice6.part7;

import java.util.Iterator;

public class Range implements Iterable<Integer>{
	
	private int end;
	private int cur;
	private boolean reverse = false;

	public Range(int n, int m) {
		this.cur = n - 1;
		this.end = m;
	}
	
	public Range(int n, int m, boolean reverse) {
		
		if (reverse) {
			this.cur = m + 1;
			this.end = n;
			this.reverse = true;
		}
		else {
			this.end = m;
			this.cur = n - 1;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<Integer>{

		@Override
		public boolean hasNext() {
			if (cur != end) return true;
			return false;
		}

		@Override
		public Integer next() {
			if (this.hasNext()) {
				cur += (reverse) ? -1 : 1;
				return cur;
			}
			return null;
		}
		
	}

}
