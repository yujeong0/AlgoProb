package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 정사각형 타일에서 자르는 경우
 M 이 큰거 내가 원하는 거 S
 남은 타일 2개로 보면
 하나는 M-S * S
 다른 하나는 M-S * M
 
 직사각형 타일에서 자르는 경우
 직사각형 두 변 중 작은 게 min
 하나는 min-S *S
 다른 하나는 min * max-S
 
 1. 수정이가 만들고자 하는 타일 중 큰 타일을 머저 만듦
 입력받은 타일 크기를 내림차순으로 정렬
 
 2. 남아있는 타일 중에 min변의 길이가 최대인 타일과 만들고자하는 타일의 변의 길이를 비교
 가능하지 않다면 새 타일 구입
 가능하다면 타일 만들고 자투리 2개 타일 보관
 */

public class SW_1812_수정이의타일자르기_쌤풀이 {
	static class Rectangle implements Comparable<Rectangle> {
		int max, min;

		public Rectangle(int width, int height) {
			this.min = Math.min(width, height);
			this.max = Math.max(width, height);
		}

		@Override
		public int compareTo(Rectangle o) {
			return o.min- this.min;
		}
	}
	static int N, M, size[], cnt;
	static PriorityQueue<Rectangle> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			size = new int[N];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			}
			
			cut();
			
			System.out.println("#" + testcase + " " + cnt + "\n");
			
		} // tc
		
		
	} // main
	
	private static void cut() {
		// 만들고자 하는 크기가 큰 타일부터 처리
		Arrays.sort(size);
		
		queue = new PriorityQueue<Rectangle>();
		queue.offer(new Rectangle(M, M));	// 타일 1장 사고 시작
		++cnt;	// 타일 갯수 증가
		
		for (int i = N-1; i >= 0; i--) {	// 제일 큰 타일부터 만들어 보자
			go(1 << size[i]);	// 1 	1<<1 : 10(2),	1<<2 : 100(4)
		}
		
	}

	private static void go(int size) {
		// 자투리 타일 중에 min변의 크기가 최대인 놈 꺼내서 비교
		Rectangle r = queue.poll();
		// 원하는 크기의 타일을 만들 수 있다면 만들고 잘라낸 2개의 자투리 타일을 다시 보관
		if(r.min >= size) {
			queue.offer(new Rectangle(r.min-size, size));
			queue.offer(new Rectangle(r.min, r.max-size));
		} else {
			// 원하는 크기의 타일을 만들 수 없다면 새로 타일을 구매해서 잘라내고 (이 때 구매한 타일 개수 카운트 증가)
			// 							잘라낸 2개의 자투리 타일을 다시 보관
			//							꺼내고 사용하지 않은 타일 다시 보관
			++cnt;
			queue.offer(r);
			queue.offer(new Rectangle(M-size, size));
			queue.offer(new Rectangle(M-size, M));
		}
		
	}
	
}
