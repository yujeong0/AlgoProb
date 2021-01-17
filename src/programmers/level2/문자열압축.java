package programmers.level2;

public class 문자열압축 {
    public int solution(String s) {
        for (int len = 1; len <= s.length()/2; len++) {
			cut(len, s);
		}
        if(MIN > s.length()) MIN = s.length();
        return MIN;
    }
    
    int MIN = Integer.MAX_VALUE;
    public void cut(int len, String s) {
    	StringBuilder sb = new StringBuilder();
    	String cur = s.substring(0, len);
    	String next;
    	int count = 1;
    	for (int i = len; i < s.length(); i+=len) {
    		if(i+len <= s.length()) next = s.substring(i, i+len);
    		else next = s.substring(i);
    		
			if(cur.equals(next)) {
				count++;
			}
			else {
				if(count > 1) sb.append(count).append(cur);
				else sb.append(cur);
				
				cur = next;
				count = 1;
			}
    	}
		if(count > 1) sb.append(count).append(cur);
		else sb.append(cur);
    	
    	if(MIN > sb.length()) MIN = sb.length();
    }
    
    public static void main(String[] args) {
		new 문자열압축().service();
	}
    public void service() {
    	System.out.println(solution("aabbaccc"));
    }
}
