package programmers.level2;

// 37분 동안 풀었지만.. 효율성 한 개가 안 됨..
public class 가장긴팰린드롬 {
	public int solution(String s) {
		int answer = 1;
		
		int len = s.length();
		while(true) {
			if(len == 1) break;
			
			for (int i = 0; i < s.length()-1; i++) {
				if(i+len > s.length()) break;
				String slice = s.substring(i, i+len);
				int half = slice.length()/2;
				int idx1 = 0, idx2 = slice.length()-1;
				boolean success = true;
				while(true) {
					if(idx1 > half) break;
					if(slice.charAt(idx1) != slice.charAt(idx2)) {
						success = false;
						break;
					}
					
					idx1++;
					idx2--;
				}
				
				if(success) {
					return slice.length();
				}
			}
			
			len--;
		}
		
		return answer;
	}
	public static void main(String[] args) {
		System.out.println(new 가장긴팰린드롬().solution("atotcabd"));
	}
}
