package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_8911_거북이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[][] dir = new int[][] { {0, 1},{1, 0},{0, -1},{-1, 0} };
		for (int t = 0; t < T; t++) {
			String command = br.readLine();
			int maxX = 0, maxY = 0, minX = 0, minY = 0;
			int x = 0, y = 0, d = 0;
			for (int i = 0; i < command.length(); i++) {
				switch(command.charAt(i)) {
				case 'F':
					x += dir[d][0];
					y += dir[d][1];
					break;
				case 'B':
					x -= dir[d][0];
					y -= dir[d][1];
					break;
				case 'L':
					if(d == 0) d = 3;
					else d--;
					break;
				case 'R':
					d = (d+1)%4;
					break;
				}
				if(maxX < x) maxX = x;
				if(maxY < y) maxY = y;
				if(minX > x) minX = x;
				if(minY > y) minY = y;
			}
			sb.append((maxX - minX) * (maxY - minY)).append("\n");
		} // t
		
		System.out.println(sb.toString());
		
	} // main
}
