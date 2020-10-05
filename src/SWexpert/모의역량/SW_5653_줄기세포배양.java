package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5653_줄기세포배양 {

	static int N, M, K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 초기 세로 크기
			M = Integer.parseInt(st.nextToken());	// 초기 가로 크기
			K = Integer.parseInt(st.nextToken());	// 배양 시간
			
			for (int i = 0; i < N; i++) {
				
			}
			
			
			sb.append("#" + testcase + " " + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
		
	} // end of main
	
	
} // end of class
