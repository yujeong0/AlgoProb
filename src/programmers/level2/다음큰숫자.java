package programmers.level2;

public class 다음큰숫자 {
    public int solution(int n) {
    	Integer N = n;
    	String s = N.toBinaryString(N);
    	int countOne = 0;
    	for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '1') countOne++;
		}
    	
    	Integer num = N+1;
    	int count = 0;
    	while(true) {
    		count = 0;
    		s = N.toBinaryString(num);
    		for (int i = 0; i < s.length(); i++) {
    			if(s.charAt(i) == '1') count++;
    		}
    		if(count == countOne) break;
    		num++;
    	}
    	
        return num;
    }
    public static void main(String[] args) {
		new 다음큰숫자().service();
	}
    void service() {
    	System.out.println(solution(0));
    }
}
