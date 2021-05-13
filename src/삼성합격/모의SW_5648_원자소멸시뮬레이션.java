package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 모의SW_5648_원자소멸시뮬레이션 {
	static int N, SUM;
	static List<int[]> atoms;
	static List<Integer>[][] grid = new ArrayList[4001][4001];
	static int[][] dir = new int[][] { {0, 1},{0, -1},{-1, 0},{1, 0} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i <= 4000; i++) {
			for (int j = 0; j <= 4000; j++) {
				grid[i][j] = new ArrayList<>();
			}
		}
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int testcase = 1; testcase <= T; testcase++) {
			SUM = 0;
			N = Integer.parseInt(br.readLine().trim());
			atoms = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int x = (Integer.parseInt(st.nextToken())+1000)*2;
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				atoms.add(new int[] { x,y,
						Integer.parseInt(st.nextToken()),	// 이동방향
						Integer.parseInt(st.nextToken()),	// 보유에너지 K
						0,	// 사라졌는지. 안사라진것은 0, 사라진 것은 1
				});
			}
			
			for (int t = 0; t < 4000; t++) {
				for (int i = 0; i < N; i++) {
					int[] atom = atoms.get(i);
					if(atom[4] == 1) continue;
					int nr = atom[0] + dir[atom[2]][0];
					int nc = atom[1] + dir[atom[2]][1];
					if(nr > 4000 || nr < 0 || nc > 4000 || nc < 0) {
						atom[4] = 1;
						continue;
					}
					atom[0] = nr;
					atom[1] = nc;
					grid[nr][nc].add(i);
				} // i
				
				for (int i = 0; i <= 4000; i++) {
					for (int j = 0; j <= 4000; j++) {
						if(grid[i][j].size() > 1) {
							for(int num : grid[i][j]) {
								SUM += atoms.get(num)[3];
								atoms.get(num)[4] = 1;
							}
						}
						else grid[i][j].clear();
					}
				}
				
			} // t
			
			sb.append("#").append(testcase).append(" ").append(SUM).append("\n");
		} // tc
		
		System.out.println(sb.toString());
	} // main
}
