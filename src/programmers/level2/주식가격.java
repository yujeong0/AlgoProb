package programmers.level2;

import java.util.Arrays;

public class 주식가격 {
	
    public int[] solution(int[] prices) {
    	int N = prices.length;
    	int[] answer = new int[N];
    	
    	for (int i = 0; i < N-1; i++) {
    		int me = prices[i];
    		int count = 0;
    		for (int j = i+1; j < N; j++) {
				if(me <= prices[j]) count++;
				else {
					count++;
					break;
				}
			}
    		if(count == 0) count = 1;
    		answer[i] = count;
		}
    	
    	answer[N-1] = 0;
    	
        return answer;
    }
	
	
	public static void main(String[] args) {
		new 주식가격().service();
	}
	public void service() {
		System.out.println(Arrays.toString(solution(new int[] {1,2,3,2,3,1})));
	}
}
