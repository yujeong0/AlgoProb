package programmers.level3;

public class N으로표현 {
	
    public static int solution(int N, int number) {
    	int NUM = N;
    	int cnt = 1;
    	while(true) {
    		if(cnt > 8) break;
    		solve(N, number, NUM, cnt);
    		NUM = NUM*10 + N;
    		cnt++;
    	}
    	if(MIN > 8)
    		return -1;
        return MIN;
    }
    
    static int MIN = Integer.MAX_VALUE;
    public static void solve(int N, int number, int result, int cnt) {
    	if(cnt > 8) return;
    	if(result == number) {
    		if(MIN > cnt) MIN = cnt;
    		return;
    	}
    	
    	int n = N;
    	int CNT = cnt+1;
    	while(true) {
    		if(CNT > 8) break;
    		solve(N, number, result+n, CNT);
    		solve(N, number, result-n, CNT);
    		solve(N, number, result*n, CNT);
    		solve(N, number, result/n, CNT);
    		n = n*10+N;
    		CNT++;
    	}
    	int n1 = N;
    	CNT = cnt+1;
    	while(true) {
    		if(CNT > 8) break;
    		int n2 = N;
    		int CNT2 = CNT+1;
    		while(true) {
    			if(CNT2 > 8) break;
    			solve(N, number, result+(n1*n2), CNT2);
    			solve(N, number, result-(n1*n2), CNT2);
    			solve(N, number, result+(n1/n2), CNT2);
    			solve(N, number, result-(n1/n2), CNT2);
    			n2 = n2*10+N;
    			CNT2++;
    		}
    		n1 = n1*10+N;
    		CNT++;
    	}
    	
    }
    
    public static void main(String[] args) {
		System.out.println(solution(5, 12));
	}
}
