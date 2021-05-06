package programmers.level1;

public class 수박수박수박수박수박수 {
	public String solution(int n) {
		String s = "수박";
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < n / 2; i++) {
			answer.append(s);
		}

		if (n % 2 != 0) {
			answer.append("수");
		}
		return answer.toString();
	}
}
