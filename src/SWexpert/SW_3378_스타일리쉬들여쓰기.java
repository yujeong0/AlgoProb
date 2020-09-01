package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_3378_스타일리쉬들여쓰기 {
	
	static int p, q;
	static int R = -1, C = -1, S = -1;
	static char[][] master, me;
	static int[] result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			
			master = new char[p][];
			me = new char[q][];
			result = new int[q];		// 자신 코드의 각 행마다 들여쓰기로 결정된 칸 수(-1 : 결정이 불가능한 값으로 사용)
			Arrays.fill(result, -2);	// 초기상태인지 확인하기 위해서 -2로 채움
			
			for (int i = 0; i < p; i++) 
				master[i] = br.readLine().toCharArray();
			
			for (int i = 0; i < q; i++) 
				me[i] = br.readLine().toCharArray();
			
	//		System.out.println(master.toString());
			
			for (int r = 1; r <= 20; r++) {
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						if(isAvailable(r,c,s)) {	// 마스터 코드에 r,c,s 적용하여 가능한 지 확인
							// 내거에 들여쓰기 하기
							processIndent(r,c,s);
							
						}
					}
				}
			}
			
			sb.append("#" + testcase + " ");
			for(int r : result) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}	// end of main
	
	private static void processIndent(int r, int c, int s) {
		int rCount = 0, cCount = 0, sCount = 0;
		for (int i = 0; i < q; i++) {
			if(result[i] == -2) {
				result[i] = r*rCount + c*cCount + s*sCount;
			}
			else {	// 이미 기존의 rcs로 계산된 적 있는 경우
				if(result[i] != r*rCount + c*cCount + s*sCount) {
					result[i] = -1;		// 다른 rcs로 해보니까 다르게 나와서 결정할 수 없는 경우?
				}
			}
			
			for (char ch : me[i]) {
				switch(ch) {
				case '(': rCount++; break;
				case ')': rCount--; break;	// 어차피 괄호 차이니까 닫는 것 나오면 마이너스 하면 됨
				case '{': cCount++; break;
				case '}': cCount--; break;
				case '[': sCount++; break;
				case ']': sCount--; break;
				}
			}
		}
		
	}

	private static boolean isAvailable(int r, int c, int s) {
		
		int rCount = 0, cCount = 0, sCount = 0;
		for (int i = 0; i < p; i++) {
			int cnt = 0;
			// 온점 개수 세기
			for (char ch : master[i]) {
				if(ch=='.') ++cnt;
				else break;
			}
			int indent = r*rCount + c*cCount + s*sCount;
			
			if(indent != cnt) return false;	// 계산된 띄워쓰기 개수와 실제 마스터 들여쓰기 개수가 다르면 r,c,s 답 x
			
			for (char ch : master[i]) {	
				switch(ch) {
				case '(': rCount++; break;
				case ')': rCount--; break;	// 어차피 괄호 차이니까 닫는 것 나오면 마이너스 하면 됨
				case '{': cCount++; break;
				case '}': cCount--; break;
				case '[': sCount++; break;
				case ']': sCount--; break;
				}
			}
			
		}
		
		return true;
	}
	
	
}
