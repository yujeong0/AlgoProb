package kakao.blind2020;

public class 자물쇠와열쇠 {
	static int [][] map;
	static int size;
	public static boolean solution(int[][] key, int[][] lock) {
		size = (lock.length) + (key.length -1)*2;
		map = new int[size][size];
		
		for (int rot = 0; rot < 4; rot++) {	// 키를 90도씩 돌리기
			int[][] arr = rotate(key);
			key = arr;
			
			int keyX = 0, keyY = 0;
			while(true) {
				if(keyY == lock.length + key.length -1) {
					keyX++;
					keyY = 0;
				}
				if(keyX == lock.length + key.length -1) break;

				initMap(key.length, lock);	// map 초기화
				int x = keyX, y;
				for (int i = 0; i < key.length; i++) {
					y = keyY;
					for (int j = 0; j < key.length; j++) {
						map[x][y++] += key[i][j];
					}
					x++;
				}
				if(isSuccess(key, lock)) {
					return true;
				}
				
				keyY++;
			}
			
		}
		
		return false;
	}
	
	public static void initMap(int keylen, int[][] lock) {
		int r = keylen-1, c = keylen-1;
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				map[r][c++] = lock[i][j];
			}
			r++;
			c = keylen-1;
		}
	}
	public static boolean isSuccess(int[][] key, int[][] lock) {
		int r = key.length-1, c = key.length-1;
		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock.length; j++) {
				if(map[r][c++] != 1) {
					return false;
				}
			}
			r++;
			c = key.length-1;
		}
		
		return true;
	}
	
	public static int[][] rotate(int[][] key) {
		int size = key.length;
		int[][] copy = new int[size][size];
		
		for (int j = 0; j < size; j++) {	
			int[] arr = new int[size];
			int idx = size-1;
			for (int i = 0; i < size; i++) {
				arr[i] = key[idx--][j];
			}
			copy[j] = arr;
		}
		
		return copy;
	}
	
	public static void main(String[] args) {
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		System.out.println(solution(key, lock));
	}
}
