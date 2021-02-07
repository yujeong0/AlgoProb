package programmers.level3;

import java.util.Arrays;

public class 야근지수 {
    public long solution(int n, int[] works) {
        long answer = 0;
        boolean isZero = false;
    out:while(true) {
	        Arrays.sort(works);
	        int large = works[works.length-1];
	        if(large == 0) {
	        	isZero = true;
	        	break;
	        }
	        int idx = works.length-1;
	        while(true) {
	        	if(n == 0) break out;
	        	if(idx < 0) break;
	        	if(large == works[idx]) {
	        		works[idx--]--;
	        		n--;
	        	}
	        	else break;
	        }
        
        }
    	if(!isZero) {
	        for (int i = 0; i < works.length; i++) {
	        	answer += works[i] * works[i];
	 		}
    	}
        return answer;
    }
}
