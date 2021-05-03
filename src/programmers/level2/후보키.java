package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class 후보키 {
	public int solution(String[][] relation) {
		N = relation.length;
		ANSWER_KEY = new int[N][];
		
		RELATION = relation;
		for (int i = 1; i < relation[0].length; i++) {
			R = i;
			nums = new int[R];
			combination(0, 0);
		}

		return ANSWER;
	}

	int N, R;
	String[][] arr;
	String[][] RELATION;
	static int ANSWER = 0;
	int[] nums;
	static int[][] ANSWER_KEY;
	Set<String> set = new HashSet<>();
	public void combination(int start, int cnt) {
		if (cnt == R) {
			arr = new String[N][R];
			for (int r = 0; r < N; r++) {
				int idx = 0;
				for (int i = 0; i < nums.length; i++) {
					arr[r][idx++] = RELATION[r][nums[i]];
				}
			}

			if (solve()) { // 후보키 가능
				
				boolean ok = true;
				int idx = 0;
			out:for (int i = 0; i < R; i++) {
					int me = nums[i];
					for (int i2 = 0; i2 < ANSWER; i2++) {
						for (int j2 = 0; j2 < ANSWER_KEY[i2].length; j2++) {
							if(ANSWER_KEY[i2][j2] == me) {
								ok = false;
								idx = i2;
								break out;
							}
						}
					}
				}
				if(!ok) { // 내가 더 짧으면 짧은 걸로 교체
					if(ANSWER_KEY[idx].length > nums.length) {
						ANSWER_KEY[idx] = nums;
					}
				}
				if(ok) {
					ANSWER_KEY[ANSWER] = new int[R];
					for (int i = 0; i < R; i++) {
						ANSWER_KEY[ANSWER][i] = nums[i];
					}
					
					Arrays.sort(ANSWER_KEY, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return o1.length - o2.length;
						}
					});
					
					ANSWER++;
				}
			}
			return;
		}

		for (int i = start; i < RELATION[0].length; i++) {
			nums[cnt] = i;
			combination(i + 1, cnt + 1);
		}
	}

	public boolean solve() {
		for (int i = 0; i < N - 1; i++) {
			String[] ME = arr[i];
			for (int j = i + 1; j < N; j++) {
				boolean isSame = true;
				for (int c = 0; c < R; c++) {
					if (!ME[c].equalsIgnoreCase(arr[j][c])) {
						isSame = false;
						break;
					}
				}
				if (isSame) {
					return false;
				}
			}
		}
		return true;
	} // solve

	public static void main(String[] args) {
//		new 후보키().solution(new String[][] { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
//				{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
//				{ "600", "apeach", "music", "2" } });
		new 후보키().solution(new String[][] { 
			{"a","b","c"},
			{"1","b","c"},
			{"a","b","4"},
			{"a","5","c"} 
		});
		System.out.println(ANSWER);
		for (int i = 0; i < ANSWER; i++) {
			System.out.println(Arrays.toString(ANSWER_KEY[i]));
		}
	} //main
}
