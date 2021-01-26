package programmers.level2;

public class 점프와순간이동 {
    public int solution(int n) {
        int ans = 0;
        while(true) {
        	if(n == 1) {
        		ans++;
        		break;
        	}
        	if(n % 2 != 0) ans++; 
        	n /= 2;
        }

        return ans;
    }
}
