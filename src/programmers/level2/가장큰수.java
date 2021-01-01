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
				String s1 = o1.toString();
				String s2 = o2.toString();
				
				return ((s2+s1).compareTo(s1+s2));
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
    	System.out.println(solution(new int[] {10, 0, 155}));
    }
}
