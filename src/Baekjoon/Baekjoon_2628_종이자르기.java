package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2628_종이자르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int garo = Integer.parseInt(st.nextToken());
		int sero = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());	// 점선 개수
		
		boolean[] rows = new boolean[sero];
		boolean[] cols = new boolean[garo];
		int idx;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if(Integer.parseInt(st.nextToken()) == 0) {	// 가로 자르기
				idx = Integer.parseInt(st.nextToken());
				rows[idx] = true;
			} else {	// 세로 자르기
				idx = Integer.parseInt(st.nextToken());
				cols[idx] = true;
			}
		}
		
		int[] cutHeight = new int[sero];
		int[] cutWidth = new int[garo];
		
		int numGaro = 0, numSero = 0;
		int len = 1;
		for (int i = 1; i < garo; i++) {
			if(!cols[i]) {
				len++;
				if(i == garo-1) cutWidth[numGaro++] = len;
			}
			else {
				cutWidth[numGaro++] = len;
				len = 1;
			}
		}
		
		len = 1;
		for (int i = 1; i < sero; i++) {
			if(!rows[i]) {
				len++;
				if(i == sero-1) cutHeight[numSero++] = len;
			}
			else {
				cutHeight[numSero++] = len;
				len = 1;
			}
		}
		
		int max = 0;
		int g;
		for (int i = 0; i < numGaro; i++) {
			g = cutWidth[i];
			for (int j = 0; j < numSero; j++) {
				if(max < g * cutHeight[j]) max = g * cutHeight[j];
			}
		}
		
		System.out.println(max);
	}	// end of main
	
	
}
