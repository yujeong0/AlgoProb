//package NHN;
//
//import java.util.Scanner;
//
//class test02 {
//	private static void solution(int day, int width, int[][] blocks) {
//		int[][] status = new int[]
//		
//		int[] height = new int[width];
//		int count, idx;
//		for (int i = 0; i < width; i++) {
//			count = 0;
//			idx = blocks[i].length;
//			while(true) {
//				if(blocks[idx--][i] == 0) break;
//				
//				count++;
//			}
//			height[i] = count;
//		}
//		
//		int totalSiment = 0;
//		for (int d = 0; d < day; d++) {
//			
//			int max = -1;
//			for (int i = 0; i < width; i++) {
//				for (int j = i-1; j >= 0; j--) {	// 왼쪽
//					if(height[i] < height[j]) max = height[j];
//				}
//				for (int j = i+1; j < width; j++) {	// 오른쪽
//					if(height[i] < height[j]) max = height[j];
//				}
//				
//				for (int j = height[i]+1; j < height[i]+1+max; j++) {
//					
//				}
//			}
//			
//		}
//	}
//
//	private static class InputData {
//		int day;
//		int width;
//		int[][] blocks;
//	}
//
//	private static InputData processStdin() {
//		InputData inputData = new InputData();
//
//		try (Scanner scanner = new Scanner(System.in)) {
//			inputData.day = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
//			inputData.width = Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
//
//			inputData.blocks = new int[inputData.day][inputData.width];
//			for (int i = 0; i < inputData.day; i++) {
//				String[] buf = scanner.nextLine().trim().replaceAll("\\s+", " ").split(" ");
//				for (int j = 0; j < inputData.width; j++) {
//					inputData.blocks[i][j] = Integer.parseInt(buf[j]);
//				}
//			}
//		} catch (Exception e) {
//			throw e;
//		}
//
//		return inputData;
//	}
//
//	public static void main(String[] args) throws Exception {
//		InputData inputData = processStdin();
//
//		solution(inputData.day, inputData.width, inputData.blocks);
//	}
//}