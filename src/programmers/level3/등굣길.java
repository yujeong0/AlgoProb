package programmers.level3;

public class 등굣길 {
	int[][] Puddles;
    public int solution(int m, int n, int[][] puddles) {
    	Puddles = puddles;
    	int[][] map = new int[n+1][m+1];
    	map[1][1] = 1;
    	for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if((i == 1 && j == 1) || isPuddle(i, j)) continue;
				
				if(i-1 <= n) {
					map[i][j] += map[i-1][j] % 1000000007;
				}
				if(j-1 <= m) {
					map[i][j] += map[i][j-1] % 1000000007;
				}
				map[i][j] %= 1000000007;
			}
		}
    	
    	return map[n][m] % 1000000007;
    }
    public boolean isPuddle(int x, int y) {
    	for (int i = 0; i < Puddles.length; i++) {
			if(Puddles[i][0] == y && Puddles[i][1] == x) return true;
		}
    	return false;
    }
    public static void main(String[] args) {
		new 등굣길().solve();
	}
    public void solve() {
    	System.out.println(solution(4, 3, new int[][] {{1,3},{3,1}}));
    }
}	
