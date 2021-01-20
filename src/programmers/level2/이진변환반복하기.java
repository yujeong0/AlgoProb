package programmers.level2;

import java.util.Arrays;

public class 이진변환반복하기 {
    public int[] solution(String s) {
    	int[] answer = new int[2];
        answer[0]++;
        
        int prevLen = 0;
        while(true) {
        	if(s.equals("1")) break;
        	prevLen = s.length();
        	s = s.replaceAll("0", "");
        	answer[1] += prevLen - s.length();
        	
        	if(s.equals("1")) break;
        	s = Integer.toBinaryString(s.length());
        	answer[0]++;
        }
        
        return answer;
    }
    public static void main(String[] args) {
		System.out.println(Arrays.toString(new 이진변환반복하기().solution("110010101001")));
	}
}
