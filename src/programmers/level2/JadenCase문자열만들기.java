package programmers.level2;

public class JadenCase문자열만들기 {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
        	if(arr[i] == ' ') {
        		answer.append(' ');
        		continue;
        	}
        	
			if((97 <= arr[i] && arr[i] <= 122)) {	// 소문자일 때
				if(i == 0 || (i != 0 && arr[i-1] == ' ')) {
					arr[i] = (char)(arr[i] - 32);	// 앞이 공백이면 대문자로 바꿈
				}
			}
			else if(i != 0 && (65 <= arr[i] && arr[i] <= 90) && arr[i-1] != ' ') {	// 대문자일 때
					arr[i] = (char)(arr[i] + 32);
			}
			answer.append(arr[i]);
        }
        
        return answer.toString();
    }
    public static void main(String[] args) {
		System.out.println(new JadenCase문자열만들기().solution("    3people      unFollow3d   me"));
	}
}
