package programmers.level2;

public class 괄호변환 {
    public String solution(String p) {
        return process(p);
    }
    
    public String process(String p) {
    	if(p.length() == 0) return "";
    	if(isCorrect(p)) return p;
    	
    	int idx = splitIdx(p);
    	String u = p.substring(0, idx+1);
    	String v = p.substring(idx+1);
    	
    	if(isCorrect(u)) return u+=process(v);
    	else {
    		StringBuilder sb = new StringBuilder();
    		sb.append("(").append(process(v)).append(")");

    		String newU = u.substring(1, u.length()-1);
    		if(newU.length() > 0) {
	    		StringBuilder newSb = new StringBuilder();
	    		int i = 0;
	    		char c;
	    		while(i < newU.length()) {
	    			if(newU.charAt(i++) == '(') c = ')';
	    			else c = '(';
	    			newSb.append(c);
	    		}
	    		sb.append(newSb);
    		}
    		return sb.toString();
    	}
    }
    
    boolean isCorrect(String s) {
    	int cnt = 0;
    	for (int i = s.length()-1; i >= 0; i--) {
			if(cnt == 0 && s.charAt(i) == '(') return false;
			if(cnt > 0 && s.charAt(i) == '(') cnt--;
			else if(s.charAt(i) == ')') cnt++;
		}
    	
    	return true;
    }
    int splitIdx(String s) {
    	int a = 0, b = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if(s.charAt(i) == '(') a++;
    		else b++;
    		
    		if(a==b) return i;
		}
    	return s.length()-1;
    }
    public static void main(String[] args) {
		new 괄호변환().service();
	}
    public void service() {
    	System.out.println(solution("()))((()"));
    }
    
}
