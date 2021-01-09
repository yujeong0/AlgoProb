package programmers.level2;

public class 올바른괄호 {
    boolean solution(String s) {
        boolean front = false;
        for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') front = true;
			else {
				if(front) {
					if(s.charAt(i) == '(') return false;
					if(s.charAt(i) == ')') front = false;
				}
				if(!front && s.charAt(i) == ')') return false;
			}
		}

        return true;
    }
}
