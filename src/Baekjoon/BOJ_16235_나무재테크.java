package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크 {
	static int N, M, K;
	static int[][] A;
	static class Tree implements Comparable<Tree>{
		int age;
		boolean alive;
		public Tree(int age) {
			this.age = age;
			this.alive = true;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age-o.age;
		}
	}
	static class Ground {
		int power;
		List<Tree> trees;
		
		public Ground(int power) {
			this.power = power;
			this.trees = new ArrayList<>();
		}
		
	}
	static Ground[][] grid;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		grid = new Ground[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = new Ground(5);
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			grid[x-1][y-1].trees.add(new Tree(age));
		}
		
		solve();
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += grid[i][j].trees.size();
			} // j
		} // i
		
		System.out.println(sum);
		
	} // main
	
	static int[][] dir = { {-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1} };
	private static void solve() {
		
		for (int k = 0; k < K; k++) {
			
			// 1. 봄 : 양분 먹고 나이 1 증가, 양분 부족하면 죽은 표시
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					if(grid[i][j].trees.size() == 0) continue;
					
					Collections.sort(grid[i][j].trees);
					int n = 0;
					boolean isEnd = true;
					for (; n < grid[i][j].trees.size(); n++) {
						Tree tree = grid[i][j].trees.get(n);
						if(grid[i][j].power-tree.age < 0) {
							tree.alive = false;
							isEnd = false;
							break;
						}
						grid[i][j].power -= tree.age;
						tree.age += 1;
					}
					
					if(!isEnd) {
						n++;
						for (; n < grid[i][j].trees.size(); n++) {
							Tree tree = grid[i][j].trees.get(n);
							tree.alive = false;
						}
					}
				
				} // j
			} // i
			
			// 2. 여름 : 죽은 나무가 양분으로 변함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					Iterator<Tree> iter = grid[i][j].trees.iterator();
					while(iter.hasNext()) {
						Tree tree = iter.next();
						if(!tree.alive) {
							grid[i][j].power += tree.age / 2;
							iter.remove();
						}
					}
				} // j
			} // i
			
			// 3. 가을 : 나이 5의 배수인 나무는 8방향으로 번식
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					for (Tree tree : grid[i][j].trees) {
						if(tree.age % 5 == 0) {
							for (int d = 0; d < 8; d++) {
								int nx = i + dir[d][0];
								int ny = j + dir[d][1];
								
								if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
								
								grid[nx][ny].trees.add(new Tree(1));
							}
						}
					}
				
				} // j
			} // i
			
			// 4. 겨울 : 양분 추가
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j].power += A[i][j];
				} // j
			} // i
			
		} // K
		
	} // solve
}
