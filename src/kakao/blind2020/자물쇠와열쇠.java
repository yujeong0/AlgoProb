package kakao.blind2020;

public class 자물쇠와열쇠 {
	public static boolean solution(int[][] key, int[][] lock) {
//		int size = lock.length % 2 == 0? (lock.length)*2 : (lock.length)*2+1;
//		int[][] map = new int[size][size];
//		
//		int x = -1, y;
//		for (int i = key.length-1; i < key.length-1+lock.length; i++) {
//			x++;
//			y = 0;
//			for (int j = key.length-1; j < key.length-1+lock.length; j++) {
//				map[i][j] = lock[x][y++];
//			}
//		}
//		
//		for (int i = 0; i < size; i++) {
//			for (int j = 0; j < size; j++) {
//				System.out.print(map[i][j]+ " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		for (int i = 0; i < 4; i++) {
			int[][] arr = rotate(key);
			key = arr;
			for (int x = 0; x < key.length; x++) {
				for (int y = 0; y < key.length; y++) {
					System.out.print(arr[x][y]+ " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		
		boolean answer = true;
		return answer;
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
		solution(key, lock);
	}
}
