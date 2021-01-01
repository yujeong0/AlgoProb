package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

//미완
public class 가장큰수 {
    static int N;
    static boolean[] selected;
    static int[] numbers;
	public String solution(int[] inputs) {
		N = inputs.length;
    	selected = new boolean[N];
    	numbers = new int[N];
    	
    	Integer[] numbers = new Integer[N];
    	boolean isAllZero = true;
    	for (int i = 0; i < N; i++) {
			numbers[i] = inputs[i];
			if(numbers[i] != 0) isAllZero = false;
		}
    	if(isAllZero) return "0";
    	
    	Arrays.sort(numbers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 == 0) return 1;
				if(o2 == 0) return -1;
				
				String s1 = o1.toString();
				String s2 = o2.toString();
				
				if(Integer.valueOf(s1+s2) == Integer.valueOf(s2+s1)) 
					return 0;
				if(Integer.valueOf(s1+s2) > Integer.valueOf(s2+s1))
					return -1;
				return 1;
			}
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < N; i++) {
			sb.append(numbers[i]);
		}
        return sb.toString();
    }

    public static void main(String[] args) {
		new 가장큰수().service();
	}
    public void service() {
    	System.out.println(solution(new int[] {10, 10, 155}));
    }
}
