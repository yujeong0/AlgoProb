package programmers.level2;

public class 올바른괄호 {
    boolean solution(String s) {
        int close = 0, idx = s.length()-1;
        char c;
        while(idx >= 0) {
        	c = s.charAt(idx--);
        	if(close == 0 && c == '(') return false;
        	if(c == ')') close++;
        	else if(close > 0 && c == '(') close--;
        }
        
        if(close > 0) return false;
        return true;
    }

}
