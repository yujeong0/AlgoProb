package programmers.level2;

public class 카펫 {
	
	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		
		int N = brown + yellow;
		int a, b;
		for (int i = 3; i <= N/2; i++) {
			if(N % i == 0) {
				a = i;
				b = N / a;
				
				if((a-2) * (b-2) == yellow) {
					answer[0] = b;
					answer[1] = a;
					
					break;
				}
				
			}
		}
		
        return answer;
    }
	
	public static void main(String[] args) {
		
		solution(10, 2);
		
		
		
	} // main
	
	
} // class
