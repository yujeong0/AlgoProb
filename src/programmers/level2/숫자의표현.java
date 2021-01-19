package programmers.level2;

public class 숫자의표현 {
    public int solution(int n) {
        int answer = 0, sum = 0, start = 1, num = start;
        while(true) {
        	if(sum == n) {
        		answer++;
        		if(++start > n) {
        			break;
        		}
        		sum = 0;
        		num = start;
        	}
        	else if(sum > n) {
        		start++;
        		sum = 0;
        		num = start;
        	}
        	sum += num++;
        }
        
        return answer;
    }
    public static void main(String[] args) {
		System.out.println(new 숫자의표현().solution(15));
	}
}
