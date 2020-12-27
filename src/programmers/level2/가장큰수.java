package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수 {
    static int N;
    static boolean[] selected;
    static int[] numbers;
	public String solution(int[] inputs) {
		N = inputs.length;
    	selected = new boolean[N];
    	numbers = new int[N];
    	
    	Integer[] numbers = new Integer[N];
    	for (int i = 0; i < N; i++) {
			numbers[i] = inputs[i];
		}
    	Arrays.sort(numbers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 == 0) return -1;
				if(o2 == 0) return 1;
				
				String s1 = o1.toString();
				String s2 = o2.toString();
				if(s1.length() < s2.length()) 
					return o2-o1;
				
				if(s1.length() > s2.length()) 
					return o1-o2;
				
				if(s1.length() == s2.length()) {
					for (int i = 0; i < s1.length(); i++) {
						if(s1.charAt(i)-'0' > s2.charAt(i)-'0') return o1-o2;
						else if(s1.charAt(i)-'0' < s2.charAt(i)-'0') return o2-o1;
					}
				}
					
				return 0;
			}
    		
    	});
    	
    	System.out.println(Arrays.toString(numbers));
    	
        return Integer.toString(MAX);
    }
	static int MAX = -1;
    public void permu(int cnt, int[] inputs) {
    	if(cnt == N) {
    		StringBuilder sb = new StringBuilder();
    		for (int i = 0; i < numbers.length; i++) {
    			sb.append(inputs[numbers[i]]);
			}
    		MAX = Math.max(MAX, Integer.parseInt(sb.toString()));
    		return;
    	}
    	
    	for (int i = 0; i < N; i++) {
			if(selected[i]) continue;
			if(i == 0 && inputs[i] == 0) continue;
			selected[i] = true;
			numbers[cnt] = i;
			permu(cnt+1, inputs);
			selected[i] = false;
		}
    }
    public static void main(String[] args) {
		new 가장큰수().service();
	}
    public void service() {
    	System.out.println(solution(new int[] {6,10,2}));
    }
}
