package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW_2477_차량정비소 {
	static class Customer {
		int number;	// 고객 번호
		int receptionNum = -1;	// 방문한 접수 창구
		int repairNum = -1;	// 방문한 정비 창구
		
		Customer(int number) {
			this.number = number;
		}
	}
	static int N, M, K, A, B;
	static int[] receptionTime = new int[11];	// 각 접수 창구가 몇 초 걸리는지
	static int[] repairTime = new int[11];	// 각 정비 창구가 몇 초 걸리는지
	static List<Integer> cusArriveTime = new ArrayList<>();
	static int[][] reception = new int[11][2];	// 접수 창구에 [0] : 몇 번 고객이 있는 지, [1] : 몇 초 되었는지
	static int[][] repair = new int[11][2];	// 정비 창구에 [0] : 몇 번 고객이 있는 지, [1] : 몇 초 되었는지
	static Customer[] customers = new Customer[1010];	// 고객 번호와 방문한 창구 번호 저장
	static PriorityQueue<Integer> waitReception = new PriorityQueue<>();
	static PriorityQueue<Integer> waitRepair = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			cusArriveTime.clear();
			waitReception.clear();
			waitRepair.clear();
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 접수 창구 수
			M = Integer.parseInt(st.nextToken());	// 수리 창구 수
			K = Integer.parseInt(st.nextToken());	// 고객 수
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < K; i++) {
				cusArriveTime.add(Integer.parseInt(st.nextToken()));
			} // end of input
			
			Collections.sort(cusArriveTime);
			int n = 0;
			for(int i : cusArriveTime) {
				customers[0] = new Customer(n);	// 도착 순서대로 고객별 번호 부여
			}
			
			
			
			
			sb.append("#" + testcase + "\n");
		} // end of tc
	
		System.out.println(sb.toString());
	} // main
	
	private static void solve() {
		int currentTime = cusArriveTime.get(0);	// 현재시간을 첫 고객이 도착하는 시간부터
		int currentCustomer;	// 현재 처리하는 고객번호 저장
		while(true) {
			for(int i = 0; i < K; i++) {	// 현재 시간까지 도착한 고객들 밀어넣어라~!
				waitReception.add(i);	
			}
			currentCustomer = waitReception.poll();
			
			boolean isEndReception = false;
			for (int i = 0; i < N; i++) {	// 모든 접수 창구를 돌면서 갈 수 있는 곳에 들어감
				if(reception[i][0] == -1) {
					customers[currentCustomer].receptionNum = i;
					reception[i][0] = customers[currentCustomer].number;
					reception[i][1] = receptionTime[i];
					isEndReception = true;
					break;
				}
				else {	// 창구가 차있다면 시간 하나 줄이기..
					reception[i][1]--;
					if(reception[i][1] == 0) {	// 시간 다 끝났으면 정비로 옮길건데 정비 다 차있으면 정비대기로
						
						boolean isEndRepair = false;
						for (int m = 0; m < M; m++) {
							if(repair[i][0] == -1) {
								customers[reception[i][0]].repairNum = m;
								repair[m][0] = customers[reception[i][0]].number;
								repair[m][1] = repairTime[i];
								isEndRepair = true;
								break;
							}
						}
						if(!isEndRepair) {	// 배정이 되지 않았다면 정비대기에 넣기
							waitRepair.add(reception[i][0]);
						}
						
						reception[i][0] = -1;	// 정비로 배정되거나 정비대기로 갔으니 접수에서는 빼줌
					}
				} // else
			} // 접수창구
			
			if(!isEndReception) {	// 고객이 들어갈 접수창구가 없었다면
				waitReception.add(currentCustomer);	// 다시 접수대기에 넣기
			}
			
			for (int cus = 0; cus < K; cus++) {	// 모든 고객에 대해 처리
				if(cusArriveTime.get(cus) >= currentTime) continue;
				
				if(customers[cus].receptionNum == -1) {	// 아직 접수창구에 안 갔으면
					if(!waitReception.isEmpty()) {
						
					}
					
					
					boolean isEnd = false;
					for (int i = 0; i < N; i++) {	// 모든 접수 창구를 돌면서 갈 수 있는 곳에 들어감
						if(reception[i][0] == -1) {
							customers[cus].receptionNum = i;
							reception[i][0] = customers[cus].number;
							reception[i][1] = receptionTime[i];
							isEnd = true;
							break;
						}
						else {	// 이미 사람이 있다면 시간 하나 줄이기..
							reception[i][1]--;
							if(reception[i][1] == 0) {	// 시간 다 끝났으면 정비로 옮길건데 정비 다 차있으면 정비대기로
								reception[i][0] = -1;
								for (int m = 0; m < M; m++) {
									if(repair[i][0] == -1) {
										customers[cus].repairNum = i;
										repair[i][0] = customers[cus].number;
										repair[i][1] = repairTime[i];
										isEnd = true;
										break;
									}
								}
								if(!isEnd) {	// 배정이 되지 않았다면 정비대기에 넣기
									waitRepair.add(cus);
								}
								
							}
						}
					}
					if(!isEnd) {	// 배정이 되지 않았다면 접수대기에 넣기
						waitReception.add(cus);
					}
				
				}
				else if (customers[cus].repairNum == -1) {// 아직 정비창구에 안 갔으면
					boolean isEnd = false;
					for (int i = 0; i < M; i++) {	// 모든 정비 창구를 돌면서 갈 수 있는 곳에 들어감
						if(repair[i][0] == -1) {
							customers[cus].repairNum = i;
							repair[i][0] = customers[cus].number;
							repair[i][1] = repairTime[i];
							isEnd = true;
							break;
						}
					}
					if(!isEnd) {	// 배정이 되지 않았다면 정비대기에 넣기
						waitRepair.add(cus);
					}
				}
				
			}
			
			
			
			
			
			
			
			boolean isEmpty = false;
			// 접수창구와 정비창구가 모두 빌 때까지 수행
			for (int i = 0; i < N; i++) {
				if(reception[i][0] != -1 || repair[i][0] != -1) {
					isEmpty = true;
					break;
				}
			}
			if(isEmpty) break;
			
		} // while
		
	} // solve
	
} // class
