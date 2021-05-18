package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇 {
	static class Block {
		int x;
		boolean isRobot;
		public Block(int x, boolean isRobot) {
			this.x = x;
			this.isRobot = isRobot;
		}
	}
	static int N, K;
	static Block[] A;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new Block[N*2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N*2; i++) {
			A[i] = new Block(Integer.parseInt(st.nextToken()), false);
		}
		
		System.out.println(solve());
	} // main
	
	private static int solve() {
		int ANSWER = 1;
		while(true) {
			if(isEnd()) break;
			
			// 1. 회전
			Block tmp = A[2*N-1];
			for (int i = 2*N-1; i > 0; i--) {
				A[i] = A[i-1];
			}
			A[0] = tmp;
			
			// 2. 로봇있다면 이동
			if(A[N-1].isRobot) A[N-1].isRobot = false;
			for (int i = N-2; i >= 0; i--) {
				if(!A[i].isRobot) continue;
				if(!A[i+1].isRobot && A[i+1].x > 0) {
					A[i+1].isRobot = true;
					A[i+1].x--;
					A[i].isRobot = false;
				}
			}
			if(isEnd()) break;
			if(A[N-1].isRobot) A[N-1].isRobot = false;
			
			// 3. 올라가는 위치에 로봇 없다면 로봇 올리기
			if(!A[0].isRobot && A[0].x > 0) {
				A[0].isRobot = true;
				A[0].x--;
			}
			if(isEnd()) break;
			
			ANSWER++;
		} // while
		
		return ANSWER;
	} // solve
	
	static boolean isEnd() {
		int cnt = 0;
		for (int i = 0; i < 2*N; i++) {
			if(A[i].x == 0) cnt++;
			if(cnt >= K) return true;
		}
		return false;
	}
}
