package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 튜플 {
    public int[] solution(String s) {
    	s = s.substring(2, s.length()-2);
    	String[] strings = s.split("\\},\\{");
    	
    	List<int[]> list = new ArrayList<>();

    	for (int i = 0; i < strings.length; i++) {
			String[] ss = strings[i].split(",");
			int[] arr = new int[ss.length];
			for (int j = 0; j < ss.length; j++) {
				arr[j] = Integer.parseInt(ss[j]);
			}
			list.add(arr);
		}
    	
    	list.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1.length-o2.length;
			}
		});
    	
    	List<Integer> answer = new ArrayList<>();
    	int idx = 0;
    	while(idx < list.size()) {
    		int[] arr = list.get(idx);
			for (int j = 0; j < arr.length; j++) {
				if(!answer.contains(arr[j])) {
					answer.add(arr[j]);
					break;
				}
			}
    		idx++;
    	}
        
    	int[] arr = new int[answer.size()];
    	for (int i = 0; i < answer.size(); i++) {
    		arr[i] = answer.get(i);
    	}
        return arr;
    }
    public static void main(String[] args) {
		new 튜플().service();
	}
    void service() {
    	System.out.println(Arrays.toString(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
    }
}
