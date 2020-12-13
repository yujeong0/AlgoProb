package programmers.level2;

public class 큰수만들기 {
	
	private static String solution(String number, int k) {
		StringBuilder sb = new StringBuilder(number);
		int N = number.length();
		
		int cur = 0;
		while(true) {
			if(k <= 0) break;
			
			if(cur == N-1) {
				return sb.substring(0, N-k);
			}
			if(sb.charAt(cur)-'0' < sb.charAt(cur+1)-'0') {
				sb.delete(cur, cur+1);	
				N--;
				k--;
				if(--cur < 0) cur = 0;
			}
			else cur++;
		}
		
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(solution("4177252841", 4));
		
	} // main
	
}
