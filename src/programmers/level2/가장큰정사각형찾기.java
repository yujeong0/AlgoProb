package programmers.level2;

//효ㅕ율성.,., 미완
public class 가장큰정사각형찾기 {
	public int solution(int[][] board) {
		int MAX = 0;
		int N = board.length;
		int M = board[0].length;
		for (int i = 0; i < N; i++) {
			if(Math.sqrt(MAX) >= N-i) break;
			for (int j = 0; j < M; j++) {
				if(Math.sqrt(MAX) >= M-j) break;
				if(board[i][j] == 1) {
					int cnt = 1;
					for (int j2 = j+1; j2 < M; j2++) {
						if(board[i][j2] == 1) cnt++;
						else break;
					}
					if(cnt > 1) {
						if(cnt * cnt > MAX) {
							int cnt2 = 0;
						out:for (int i2 = i+1; i2 < N; i2++) {
								int cnt3 = 0;
								for (int j2 = j; j2 < M; j2++) {
									if(board[i2][j2] != 1) {
										break;
									}
									cnt3++;
									if(cnt3 >= cnt) break;
								}
								if(cnt3 <= 1) break;
								if(cnt3 < cnt) cnt = cnt3;
								cnt2++;
								if(cnt2 >= cnt-1) break;
							}
							if(cnt2 >= 1) {
								int val = cnt <= cnt2+1? cnt*cnt : cnt2+1*cnt2+1;
								if(MAX < val) MAX = val;
							}
						}
					}
					else {	 // cnt == 1
						if(MAX < 1) MAX = 1;
					}
				}
			}
		}
		
		return MAX;
	}
    public static void main(String[] args) {
    	new 가장큰정사각형찾기().service();
	}
    public void service() {
    	System.out.println(solution(new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}}));
//    	System.out.println(solution(new int[][] {{0,0,1,1},{1,1,1,1}}));
    }
}
