package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class temp {

	static int N;
	static int map[][];
	static int dx[] = {0,-1,0,1,0}; // 상 우 하 좌
	static int dy[] = {0,0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 9; // 사과는 9
		}
		int L = Integer.parseInt(br.readLine());
		Bam bam[] = new Bam[L];
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			bam[i] = new Bam(Integer.parseInt(st.nextToken()),st.nextToken().charAt(0));
		}
		
		int x=1;
		int y=1;
		dir = 2;
		map[x][y] = 2;
		int tailX = 1;
		int tailY = 1;
		int time=0;
		int idx=0;
		while(true) {
			time++;
			
			x = x+dx[dir];
			y = y+dy[dir];
			if(x < 1 || x >N || y < 1 || y>N || (map[x][y] >= 1 && map[x][y] <= 4)) {
				break;
			}
			if(map[x][y] == 9) {
				map[x][y] = dir;
			}
			else { // 사과가 아닐때
				map[x][y] = dir;
				int tempX = tailX;
				int tempY = tailY;
				tailX = tailX + dx[map[tempX][tempY]];
				tailY = tailY + dy[map[tempX][tempY]];
				map[tempX][tempY] = 0;
			}
			
			if(idx<L && (bam[idx].time == time)) {
				if(bam[idx].dir == 'L') {
					dir = dir - 1;
					if(dir == 0) {
						dir = 4;
					}
				}
				else {
					dir = dir + 1;
					if(dir==5) {
						dir= 1;
					}
				}
				map[x][y] = dir;
				idx++;
			}
			
		}
		System.out.println(time);
	}

	static int dir;

	
	static class Bam{
		int time;
		char dir;
		public Bam(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
		
	}
}
