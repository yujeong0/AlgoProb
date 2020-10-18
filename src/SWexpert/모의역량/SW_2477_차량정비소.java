package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2477_차량정비소 {
	static class Customer implements Comparable<Customer>{
		int number;	// 고객 번호
		int receptionNum = -1;	// 방문한 접수 창구
		int repairNum = -1;	// 방문한 정비 창구
		int arriveTime;	// 정비소 도착 시간
		Customer(int number, int arriveTime) {
			this.number = number;
			this.arriveTime = arriveTime;
		}
		
		@Override
		public String toString() {
			return "Customer [number=" + number + ", receptionNum=" + receptionNum + ", repairNum=" + repairNum
					+ ", arriveTime=" + arriveTime + "]";
		}

		@Override
		public int compareTo(Customer o) {
			return this.receptionNum - o.receptionNum;
		}

	}
	
	static int N, M, K, A, B;
	static int[] receptionTime = new int[11];	// 각 접수 창구가 몇 초 걸리는지
	static int[] repairTime = new int[11];	// 각 정비 창구가 몇 초 걸리는지
	static int[][] reception = new int[11][2];	// 접수 창구에 [0] : 몇 번 고객이 있는 지, [1] : 몇 초 되었는지
	static int[][] repair = new int[11][2];	// 정비 창구에 [0] : 몇 번 고객이 있는 지, [1] : 몇 초 되었는지
	static Customer[] customers;	// 고객 번호와 방문한 창구 번호, 도착시간 저장
	static PriorityQueue<Integer> waitReception = new PriorityQueue<>();
	static Queue<Integer> waitRepair = new LinkedList<>();
	static PriorityQueue<Customer> waitRepairQ = new PriorityQueue<>();	// waitRepair 큐에 가기전에 동시 손님들 우선순위 처리하기 위한 큐
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());	// 접수 창구 수
			M = Integer.parseInt(st.nextToken());	// 수리 창구 수
			K = Integer.parseInt(st.nextToken());	// 고객 수
			A = Integer.parseInt(st.nextToken());	// 지갑분실고객의 접수창구번호
			B = Integer.parseInt(st.nextToken());	// 지갑분실고객의 정비창구번호
			customers = new Customer[K+1];
			
			// 넘버 1부터 시작이어서 1빼줌
			A -= 1;
			B -= 1;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				receptionTime[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				repairTime[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			int arrive;
			for (int i = 1; i <= K; i++) {
				arrive = Integer.parseInt(st.nextToken());
				customers[i] = new Customer(i, arrive);
			} // end of input

//			System.out.println(Arrays.toString(customers));
			
			customers[0] = new Customer(0, -1);	// 0은 안쓰는데 sort 떄문에 넣었음
			Arrays.sort(customers, new Comparator<Customer>() {
				@Override
				public int compare(Customer o1, Customer o2) {
					return o1.arriveTime - o2.arriveTime;
				}
			});	// 도착시간 순서대로 sort
			
			sb.append("#" + testcase + " " + solve() + "\n");

		} // end of tc
	
		System.out.println(sb.toString());
	} // main
	
	private static int solve() {
		
		int time = 0;
		int arriveCustomer = 1;
		
		waitRepairQ.clear();
		while(true) {
//			System.out.println("들어옴");
			if(waitReception.size() == 0 && waitRepair.size() == 0 && customers[K].arriveTime < time) {
				boolean isEnd = true;
				for (int i = 0; i < N; i++) {
					if(reception[i][0] > 0) {
						isEnd = false;
						break;
					}
				}
				if(isEnd) {
					for (int i = 0; i < M; i++) {
						if(repair[i][0] > 0) {
							isEnd = false;
							break;
						}
					}
					if(isEnd) break;
				}
			} // while 종료조건 : 접수, 정비 창구가 다 빈 상태이고 대기 손님도 없을 때 (+ 마지막손님이 도착한 시간보다 뒤일때?)
			
			for (int i = arriveCustomer; i <= K; i++) {	// 도착한 손님들을 접수 대기에 넣기
				if(customers[i].arriveTime == time) {
					waitReception.offer(i);
					arriveCustomer++;
				}
				else break;
			}
			
			for (int i = 0; i < N; i++) {	// 접수 창구 원래 손님 처리시간 줄이기
				if(reception[i][0] != 0) {	// 해당 접수 창구에 손님이 있을 경우
					if(reception[i][1] == 1) {	// 처리시간 종료
						waitRepairQ.offer(customers[reception[i][0]]);
//						waitRepair.offer(reception[i][0]);
						reception[i][0] = 0;
					}
					else {
						reception[i][1]--;
					}
				}
			}	
			
			for (int i = 0; i < N; i++) {	// 접수 창구에 새로운 손님 받을 수 있으면 받기
				if(reception[i][0] == 0) {	// 손님이 없을 때 새로운 손님 받기
					if(waitReception.size() > 0) {
						reception[i][0] = waitReception.poll();
						reception[i][1] = receptionTime[i];	// 초기 시간은 해당 창구의 처리시간
						
						customers[reception[i][0]].receptionNum = i;
					}
				}
			}
			
			// 동시에 repair 대기에 가는 손님들을 접수 창구번호 작은 순으로 정렬한 waitRepairQ에서 꺼내서 waitRepair 에 넣기
			Customer c;
			while(!waitRepairQ.isEmpty()) {
				c = waitRepairQ.poll();
				waitRepair.offer(c.number);
			}
			
			for (int i = 0; i < M; i++) {	
				if(repair[i][0] != 0) {	// 해당 접수 창구에 손님이 있을 경우
					if(repair[i][1] == 1) {	// 처리시간 종료
						repair[i][0] = 0;
					}
					else {
						repair[i][1]--;
					}
				}
			}
			
			for (int i = 0; i < M; i++) {	
				if(repair[i][0] == 0) {	// 손님이 없을 때 새로운 손님 받기
					if(waitRepair.size() > 0) {
						repair[i][0] = waitRepair.poll();
						repair[i][1] = repairTime[i];	// 초기 시간은 해당 창구의 처리시간
						
						customers[repair[i][0]].repairNum = i;
					}
				}
			}
			
			time++;
		} // while
		
		int count = 0;
		for (int i = 1; i <= K; i++) {
			if(customers[i].receptionNum == A && customers[i].repairNum == B) 
				count += i;
		}
		
		return count == 0? -1 : count;
		
	} // solve
	
} // class
