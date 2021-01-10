package programmers.level2;

public class _124나라의숫자 {
    public String solution(int n) {
    	StringBuilder answer = new StringBuilder();
    	int r = 0;
    	while(true) {
    		if(n == 0) break;
    		
    		r = n % 3;
    		if(n % 3 == 0) r = 4;
    		
    		answer.insert(0, r);
    		
    		if(n%3 == 0) n--;
			n /= 3;
    	}
        return answer.toString();
    }
}
