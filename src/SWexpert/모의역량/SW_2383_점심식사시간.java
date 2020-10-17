package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2383_점심식사시간 {
	static class Step {
		int value;
		int x, y;
		Step(int value, int x, int y) {
			this.value = value;
			this.x = x;
			this.y = y;
		}
	}
	static class Person {
		int x, y;
		Person(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static Step[] steps = new Step[2];
	static Person[] personPos = new Person[12];	// 모든 사람의 위치
	static int N;
	static int countPerson;
	static int[] people = new int[12];	// 사람별 가는 계단 저장
	static int[] position = new int[12];	// 사람별 현재위치기준 계단까지 거리
	static Queue<Integer> step1Q = new LinkedList<>();
	static Queue<Integer> step2Q = new LinkedList<>();
	static int[] wait = new int[2];
	static int MIN;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			MIN = Integer.MAX_VALUE;
			
			countPerson = 0;
			int stepcount = 0;
			N = Integer.parseInt(br.readLine());	// 한 변 길이
			int input;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					input = Integer.parseInt(st.nextToken());
					if(input == 1) {
						personPos[countPerson++] = new Person(i, j);
					}
					else if(input > 1) {
						steps[stepcount++] = new Step(input, i, j);
					}
					
				}
			} // end of input
			
			combination(0);
			
			sb.append("#" + testcase + " " + MIN + "\n");
		} // end of tc
	
		System.out.println(sb.toString());
	} // end of main
	private static void combination(int cnt) {
		if(cnt == countPerson) {
			MIN = Math.min(MIN, solve());
			return;
		}
		
		people[cnt] = 0;	// people : 사람별로 몇 번 계단 갈 지
		combination(cnt+1);
		
		people[cnt] = 1;
		combination(cnt+1);
	
	} // combination
	
	private static int solve() {
		step1Q.clear();
		step2Q.clear();
		
		for (int i = 0; i < countPerson; i++) {
			position[i] = getDistance(people[i], i);	// 사람별 계단까지 남은 거리
		}
		
		int time = 1;
		boolean isPosZero = false;
		int remain;
		while(true) {
			if(isPosZero && step1Q.size() == 0 && step2Q.size() == 0 && wait[0] == 0 && wait[1] == 0)
				break;
			
			if(!isPosZero) {
				int count = 0;
				for (int i = 0; i < countPerson; i++) {
					if(position[i] == 0) {
						position[i]--;
						count++;
						if(people[i] == 0) {
							if(step1Q.size() != 3)
								step1Q.offer(steps[0].value);
							else {
								wait[0]++;
							}
						}
						else {	// 1
							if(step2Q.size() != 3)
								step2Q.offer(steps[1].value);
							else {
								wait[1]++;
							}
						}
					} // if
					
					else if(position[i] > 0) position[i]--;
					else if(position[i] < 0) count++;
				} // for
				
				if(count == countPerson) isPosZero = true;
				
			} // if
			
			if(step1Q.size() != 0) {
				int i = step1Q.size();
				while(!step1Q.isEmpty()) {
					if(i == 0) break;
					remain = step1Q.poll() - 1;
				
					if(remain > 0) step1Q.offer(remain);
					i--;
				}
			}
			if(step1Q.size() < 3 && wait[0] > 0) {
				while(true) {
					if(wait[0] == 0 || step1Q.size() == 3) break;
					wait[0]--;
					step1Q.offer(steps[0].value);
				}
			}
		
			if(step2Q.size() != 0) {
				int i = step2Q.size();
				while(!step2Q.isEmpty()) {
					if(i == 0) break;
					remain = step2Q.poll() - 1;
				
					if(remain > 0) step2Q.offer(remain);
					i--;
				}
			}
			if(step2Q.size() < 3 && wait[1] > 0) {
				while(true) {
					if(wait[1] == 0 || step2Q.size() == 3) break;
					wait[1]--;
					step2Q.offer(steps[1].value);
				}
			}
			
			time++;
		} // while

		return time;
	} // solve
	
	private static int getDistance(int stepNum, int personNum) {
		return Math.abs(steps[stepNum].x-personPos[personNum].x) + Math.abs(steps[stepNum].y-personPos[personNum].y);
	}
} // end of class
