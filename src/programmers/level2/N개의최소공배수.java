package programmers.level2;

import java.util.Arrays;

public class N개의최소공배수 {
    public int solution(int[] arr) {
    	if(arr.length == 1) return arr[0];
    	
    	Arrays.sort(arr);
        
    	int answer = func2(arr[arr.length-1], arr[arr.length-2]);
    	for (int i = arr.length-3; i >= 0; i--) {
    		answer = func2(answer, arr[i]);
        }
        
        return answer;
    }
    
    int func2(int a, int b) {
    	return (a*b) / func(a, b);
    }
    
    int func(int a, int b) {
    	if(b == 0) return a;
    	return func(b, a % b);
    }
    public static void main(String[] args) {
		System.out.println(new N개의최소공배수().solution(new int[] {2,6,8,14}));
	}
}
