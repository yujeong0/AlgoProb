package SWexpert.모의역량;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {
	static class Number {
		int n;
		Number(int n) {
			this.n = n;
		}
		@Override
		public String toString() {
			return "Number [n=" + n + "]";
		}
		
	}
	public static void main(String[] args) {
		List<Number> list = new ArrayList<>();
		list.add(new Number(1));
		list.add(new Number(2));
		list.add(new Number(3));

		int target = -1;
		Iterator<Number> iter = list.iterator();
		int i = 0;
		while(iter.hasNext()) {
			Number num = iter.next();

			num.n = 0;
			
			i++;
		}
		
		System.out.println(list);
		
		
	}
}
