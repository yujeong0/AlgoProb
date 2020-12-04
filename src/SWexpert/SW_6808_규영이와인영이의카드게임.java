package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_6808_규영이와인영이의카드게임 {

	static int[] gyu0, in0;
	static boolean[] selected;
	static int win, lose;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			win = 0;
			lose = 0;
			selected = new boolean[19];
			gyu0 = new int[9];
			in0 = new int[9];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				gyu0[i] = Integer.parseInt(st.nextToken());
				selected[gyu0[i]] = true;
			}
			
			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if(!selected[i]) {
					in0[idx++] = i;
				}
			}
			
			Arrays.fill(selected, false);
			permu(0);
			
			sb.append("#").append(testcase).append(" ").append(win).append(" ").append(lose).append("\n");
		} // tc
		
		System.out.println(sb.toString());
	} // main
	
	static int[] order = new int[9];
	
	private static void permu(int cnt) {
		if(cnt == 9) {
			int score1 = 0, score2 = 0;
			for (int i = 0; i < 9; i++) {
				if(gyu0[i] > in0[order[i]]) score1 += gyu0[i] + in0[order[i]];
				else if(gyu0[i] < in0[order[i]]) score2 += gyu0[i] + in0[order[i]];
			}
			
			if(score1 > score2) win++;
			else if(score1 < score2) lose++;
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			order[cnt] = i;
			permu(cnt+1);
			selected[i] = false;
		}
		
	}
	
}
