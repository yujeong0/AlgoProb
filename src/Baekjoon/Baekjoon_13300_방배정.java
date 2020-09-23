package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_13300_방배정 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] students = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			students[i][0] = Integer.parseInt(st.nextToken()); 	// 성별
			students[i][1] = Integer.parseInt(st.nextToken()); 	// 학년
		}
		
		int totalRoom = 0;
		int[][] stCnt = new int[7][2];	// [학년][성별] count
		for (int i = 0; i < N; i++) {
			if(students[i][1] == 1) stCnt[1][students[i][0]]++;
			else if(students[i][1] == 2) stCnt[2][students[i][0]]++;
			else if(students[i][1] == 3) stCnt[3][students[i][0]]++;
			else if(students[i][1] == 4) stCnt[4][students[i][0]]++;
			else if(students[i][1] == 5) stCnt[5][students[i][0]]++;
			else if(students[i][1] == 6) stCnt[6][students[i][0]]++;
		}
		
		for (int i = 1; i <= 6; i++) {
			totalRoom += stCnt[i][0] / K + (stCnt[i][0] % K != 0? 1 : 0);
			totalRoom += stCnt[i][1] / K + (stCnt[i][1] % K != 0? 1 : 0);
		}
		
		System.out.println(totalRoom);
		
	}	// end of main
	
}
