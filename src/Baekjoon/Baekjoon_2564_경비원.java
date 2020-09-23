package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baekjoon_2564_경비원 {

	static int sum  = 0, N, M;
	static int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
	static boolean[][] visited;
	static int[] dongpos;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();	// 가로길이
		N = sc.nextInt();	// 세로길이
		int[][] grid = new int[N+1][M+1];
		
		List<int[]> list = new ArrayList<>();
		int numStore = sc.nextInt();	// 상점 개수
		int d, p;
		// 동근이의 위치는 numStore로 표시됨
		for (int i = 0; i <= numStore; i++) {
			d = sc.nextInt();	// 방향
			p = sc.nextInt();	// 거리
			
			switch(d) {
			case 1:
				grid[0][p] = i;
				list.add(new int[] {0, p});
				break;
			case 2:
				grid[N-1][p] = i;
				list.add(new int[] {N, p});
				break;
			case 3:
				grid[p][0] = i;
				list.add(new int[] {p, 0});
				break;
			case 4:
				grid[p][M-1] = i;
				list.add(new int[] {p, M});
				break;
			}
		}
		
		sc.close();
		
		dongpos = list.remove(numStore);	// 리스트의 마지막 위치가 동근이 위치
		for(int[] target: list) 
			sum += getMinDistance(target);
		
		System.out.println(sum);
		
	}	// end of main
	
	private static int getMinDistance(int[] target) {
		if((dongpos[0] == 0 && target[0] == N) || (dongpos[0] == N && target[0] == 0)) {	// 남북으로 마주보고 있을 때
			return Math.min(dongpos[1]+N+target[1], M-dongpos[1]+N+M-target[1]);
		}
		else if((dongpos[1] == 0 && target[1] == M) || (dongpos[1] == M && target[1] == 0)) {	// 동서로 마주보고 있을 때
			return Math.min(dongpos[0]+M+target[0], N-dongpos[0]+M+N-target[0]);
		}
		else {	// 마주보고있지 x
			return Math.abs(dongpos[0]-target[0]) + Math.abs(dongpos[1]-target[1]);
		}
	}
	
}
