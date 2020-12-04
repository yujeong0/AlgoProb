package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3349_최솟값으로이동하기 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int W,H,N;
	static Position[] locations;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			locations = new Position[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				locations[i] = new Position(x, y);
			}
			int sum = 0;
			for (int i = 0; i < N-1; i++) {
				if(locations[i].x < locations[i+1].x && locations[i].y < locations[i+1].y) {
					while(true) {
						if(locations[i].x == locations[i+1].x || locations[i].y == locations[i+1].y) break;
						locations[i].x++;
						locations[i].y++;
						sum++;
					}
					
					if(locations[i].x == locations[i+1].x) {
						sum += locations[i+1].y - locations[i].y;
					} else {
						sum += locations[i+1].x - locations[i].x;
					}
				}
				else if(locations[i].x > locations[i+1].x && locations[i].y > locations[i+1].y) {
					while(true) {
						if(locations[i].x == locations[i+1].x || locations[i].y == locations[i+1].y) break;
						locations[i].x--;
						locations[i].y--;
						sum++;
					}
					
					if(locations[i].x == locations[i+1].x) {
						sum += locations[i].y - locations[i+1].y;
					} else {
						sum += locations[i].x - locations[i+1].x;
					}
					
				}
				else {
					sum += Math.abs(locations[i].x-locations[i+1].x) + Math.abs(locations[i].y-locations[i+1].y);
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(sum).append("\n");
		} //tc
		
		System.out.println(sb.toString());
	} // main
	
}
