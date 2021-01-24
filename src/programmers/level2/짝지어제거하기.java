package programmers.level2;

import java.util.Stack;

public class 짝지어제거하기 {
	public int solution(String s) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}
		
		Stack<Character> wait = new Stack<>();
		char c1 = stack.pop();
		while(true) {
			if(stack.size() == 0) return 0;
			char c2 = stack.peek();
			if(c1 == c2) {
				stack.pop();
				if(wait.size() > 0) stack.push(wait.pop());
				if(stack.size() == 0) return 1;
				c1 = stack.pop();
			}
			else {
				wait.push(c1);
				c1 = stack.pop();
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.println(new 짝지어제거하기().solution("bacabaac"));
	}
}
