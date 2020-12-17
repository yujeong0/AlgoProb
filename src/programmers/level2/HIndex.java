package programmers.level2;

import java.util.Arrays;

public class HIndex {
    public int solution(int[] citations) {
    	Arrays.sort(citations);
    	int N = citations.length;
    	int MAX = -1;
    	for (int i = 0; i <= citations[N-1]; i++) {
			int low = 0, high = 0;
    		for (int j = 0; j < N; j++) {
				if(citations[j] < i) low++;
				if(citations[j] >= i) high++;
			}
    		if(high >= i && N-high == low) {
    			if(MAX < i) MAX = i;
    		}
		}
    	
        return MAX;
    }
}
