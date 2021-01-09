package programmers.level3;

import java.util.Arrays;


public class 입국심사 {
    public long solution(int n, int[] times) {
    	Arrays.sort(times);
    	long left = 0;
    	long N = n;
    	long right = times[times.length-1] * N;
    	long mid = 0;
    	long min = Long.MAX_VALUE;
    	while(true) {
    		if(left > right) break;
    		mid = (left + right) / 2;
    		
    		long count = 0;
    		for (int i = 0; i < times.length; i++) {
				count += mid / times[i];
			}
    		
    		if(count < N) left = mid+1;
    		else {
    			right = mid-1;
    			min = mid;
    		}
    	}
    	return min;
    }
    
}
