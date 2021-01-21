package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class 수식최대화 {
	public static void main(String[] args) {
		System.out.println(new 수식최대화().solution("100-200*300-500+20"));
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
        	List<Long> numList = new ArrayList<>();
        	List<Character> operList = new ArrayList<>();
        	
        	for (int i = 0; i < expression.length(); i++) {
				if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') operList.add(expression.charAt(i));
				else {
					int j = i;
					while(true) {
						if(j == expression.length()) break;
						if(expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*') break;
						j++;
					}
					numList.add(Long.parseLong(expression.substring(i, j)));
					i = j-1;
				}
			}
        	
        	for (int i = 0; i < 3; i++) {
				for (int j = 0; j < operList.size(); j++) {
					if(operList.get(j) == operators[numbers[i]]) {
		        		Long tot = (long) 0;
		        		switch(operators[numbers[i]]) {
						case '*':
							tot = numList.get(j) * numList.get(j+1); 
							break;
						case '+':
							tot = numList.get(j) + numList.get(j+1); 
							break;
						case '-':
							tot = numList.get(j) - numList.get(j+1); 
							break;
						}
		        		numList.remove(j+1);
		        		numList.set(j, tot);
		        		operList.remove(j);
		        		j = -1;
					}
				}
			}
        	
        	if(answer < Math.abs(numList.get(0))) answer = Math.abs(numList.get(0));
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
