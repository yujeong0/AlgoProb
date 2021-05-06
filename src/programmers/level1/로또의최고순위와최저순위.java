package programmers.level1;

public class 로또의최고순위와최저순위 {
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		int cnt = 0, zeroCnt = 0;
		for (int i = 0; i < lottos.length; i++) {
			int me = lottos[i];
			if (me == 0) {
				zeroCnt++;
				continue;
			}
			for (int j = 0; j < win_nums.length; j++) {
				if (me != win_nums[j])
					continue;
				System.out.println(win_nums[j]);
				cnt++;
				break;
			}
		}

		answer[0] = getRank(cnt + zeroCnt);
		answer[1] = getRank(cnt);

		return answer;
	}

	int getRank(int cnt) {
		int rank;
		switch (cnt) {
		case 6:
			rank = 1;
			break;
		case 5:
			rank = 2;
			break;
		case 4:
			rank = 3;
			break;
		case 3:
			rank = 4;
			break;
		case 2:
			rank = 5;
			break;
		default:
			rank = 6;
		}

		return rank;
	}
}
