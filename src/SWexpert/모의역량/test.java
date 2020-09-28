package SWexpert.모의역량;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		
		Iterator<Integer> iter = list.iterator();
		int i = 0;
		while(iter.hasNext()) {
			int n = iter.next();
			if(i == 1)
				iter.remove();
			i++;
		}
		
		System.out.println(list);
		
		
	}
}
