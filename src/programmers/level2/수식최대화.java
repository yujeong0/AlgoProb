package programmers.level2;

public class 수식최대화 {
	
	public static void main(String[] args) {
		System.out.println(new 수식최대화().solution("2-990-5+2+3*2"));
	}
	
	int[] numbers = new int[3];
	boolean[] selected = new boolean[3];
	char[] operators = {'*', '+', '-'};
    public long solution(String expression) {
        
        permu(0, expression);
        
        return answer;
    }
    
    long answer = 0;
    void permu(int cnt, String expression) {
    	if(cnt == 3) {
        	for (int oper = 0; oper < 3; oper++) {
        		for (int i = 0; i < expression.length(); i++) {
        			if(i != 0 && expression.charAt(i) == operators[numbers[oper]]) {
        				
        				String prev, next;
        				int cnt1 = 0;
        				for (int j = i-1; j >= 0; j--) {
        					if(expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != '*') {
        						cnt1++;
        					} else if(j == 0 && expression.charAt(j) == '-') {
        						cnt1++;
        						break;
        					}  else if(expression.charAt(j) == '-' && (expression.charAt(j-1) == '-' || expression.charAt(j-1) == '+'|| expression.charAt(j-1) == '*')) {
        						cnt1++;
        						break;
        					} else break;
						}
        				if(cnt1 > 0) {
	        				prev = expression.substring(i-cnt1, i);
	        				
	        				int cnt2 = 0;
	        				for (int j = i+1; j < expression.length(); j++) {
	        					if(expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != '*') {
	        						cnt2++;
	        					} else if(cnt2 == 0) {
	        						cnt2++;
	        					} else break;
	        				}
	        				next = expression.substring(i+1, i+cnt2+1);
	        				
	        				long result = 0;
	        				switch(operators[numbers[oper]]) {
	        				case '*':
	        					result = Integer.parseInt(prev) * Integer.parseInt(next);
	        					break;
	        				case '+':
	        					result = Integer.parseInt(prev) + Integer.parseInt(next);
	        					break;
	        				case '-':
	        					result = Integer.parseInt(prev) - Integer.parseInt(next);
	        					break;
	        				}
	        				
	        				expression = expression.substring(0, i-cnt1) + result + expression.substring(i+cnt2+1);
	        				i = -1;
	        			}
        			}
        		}
			}
    		
        	if(answer < Math.abs(Long.parseLong(expression))) answer = Math.abs(Long.parseLong(expression));
        	
    		return;
    	}
    	
    	for (int i = 0; i < 3; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			numbers[cnt] = i;
			permu(cnt+1, expression);
			selected[i] = false;
		}
    }
}
