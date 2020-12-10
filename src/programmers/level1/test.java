package programmers.level1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			int num = (int)iter.next();
			if(num == 2) {
				iter.remove();
			}
			else {
				System.out.println(num);
			}
		}
	}
}
