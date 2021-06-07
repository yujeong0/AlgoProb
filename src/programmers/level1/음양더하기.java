package programmers.level1;

public class 음양더하기 {
	public int solution(int[] absolutes, boolean[] signs) {
		int answer = absolutes[0] * (signs[0] ? 1 : -1);

		for (int i = 1; i < absolutes.length; i++) {
			answer += absolutes[i] * (signs[i] ? 1 : -1);
		}

		return answer;
	}
}
