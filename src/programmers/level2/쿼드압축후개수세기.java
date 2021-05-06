package programmers.level2;

public class 쿼드압축후개수세기 {
	public int[] solution(int[][] arr) {
		solve(arr, 0, 0, arr.length);

		return new int[] { zeroCnt, oneCnt };
	}

	static int zeroCnt, oneCnt;

	void solve(int[][] grid, int startx, int starty, int len) {
		int val = grid[startx][starty];
		boolean isSame = true;
		out: for (int i = startx; i < startx + len; i++) {
			for (int j = starty; j < starty + len; j++) {
				if (grid[i][j] != val) {
					isSame = false;
					solve(grid, startx, starty, len / 2);
					solve(grid, startx, starty + len / 2, len / 2);
					solve(grid, startx + len / 2, starty, len / 2);
					solve(grid, startx + len / 2, starty + len / 2, len / 2);
					break out;
				}
			}
		}
		if (isSame) {
			if (val == 0)
				zeroCnt++;
			else
				oneCnt++;
		}

	}
}
