package 삼성합격;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모의SW_2477_차량정비소  {
	static class Customer {
		int receptionNum, repairNum;
		public Customer() {}
		public Customer(int receptionNum, int repairNum) {
			this.receptionNum = receptionNum;
			this.repairNum = repairNum;
		}
	}
	static class Box {
		int customerNum, remainTime;
		
		public Box() {
			this.customerNum = -1;
		}
		public Box(int customerNum, int remainTime) {
			this.customerNum = customerNum;
			this.remainTime = remainTime;
		}
	}
	static Customer[] customerInfo;
	static int N, M, K, A, B;
	static int[] receptionTimes, repairTimes;
	static int[][] cusArrTimes;
	static Box[] receptions, repairs;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			receptionTimes = new int[N];
			repairTimes = new int[M];
			cusArrTimes = new int[K][2];
			customerInfo = new Customer[K+1];
			for (int i = 1; i <= K; i++) {
				customerInfo[i] = new Customer();
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				receptionTimes[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				repairTimes[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				cusArrTimes[i][0] = i+1;
				cusArrTimes[i][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(cusArrTimes, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1]-o2[1];
				}
			});
			receptions = new Box[N];
			for (int i = 0; i < N; i++) {
				receptions[i] = new Box();
			}
			repairs = new Box[M];
			for (int i = 0; i < M; i++) {
				repairs[i] = new Box();
			}
			
			solve();
			
			int sum = 0;
			for (int i = 1; i <= K; i++) {
				if(customerInfo[i].receptionNum == A && customerInfo[i].repairNum == B) 
					sum += i;
			}
			
			System.out.println("#" + testcase + " " + (sum == 0 ? -1 : sum));
		} // tc
		
		
	}

	private static void solve() {
		int t = 0, cusIdx = 0, cnt = 0;
		Queue<Integer> cusArrWaitQ = new LinkedList<>(), repairWaitQ = new LinkedList<>();
		while(true) {
			if(cnt == K && cusArrWaitQ.size() == 0 && repairWaitQ.size() == 0) {
				boolean isEnd = true;
				for (int i = 0; i < N; i++) {
					if(receptions[i].customerNum > -1) {
						isEnd = false;
						break;
					}
				}
				if(isEnd) {
					for (int i = 0; i < M; i++) {
						if(repairs[i].customerNum > -1) {
							isEnd = false;
							break;
						}
					}
				}
				if(isEnd) break;
			}
			
			for (int i = cusIdx; i < K; i++) {
				if(cusArrTimes[i][1] == t) {
					cusArrWaitQ.add(i+1);
					cnt++;
				}
				else if(cusArrTimes[i][1] > t) {
					cusIdx = i;
					break;
				}
			} // i
			
			for (int i = 0; i < N; i++) {
				if(receptions[i].customerNum > -1) {
					receptions[i].remainTime--;
					if(receptions[i].remainTime == 0) {
						customerInfo[receptions[i].customerNum].receptionNum = i+1;
						repairWaitQ.add(receptions[i].customerNum);
						receptions[i].customerNum = -1;
					}
				}
				if(receptions[i].customerNum == -1 && cusArrWaitQ.size() > 0) {
					receptions[i].customerNum = cusArrWaitQ.poll();
					receptions[i].remainTime = receptionTimes[i];
				}
			}

			for (int i = 0; i < M; i++) {
				if(repairs[i].customerNum > -1) {
					repairs[i].remainTime--;
					if(repairs[i].remainTime == 0) {
						customerInfo[repairs[i].customerNum].repairNum = i+1;
						repairs[i].customerNum = -1;
					}
				}
				if(repairs[i].customerNum == -1 && repairWaitQ.size() > 0) {
					repairs[i].customerNum = repairWaitQ.poll();
					repairs[i].remainTime = repairTimes[i];
				}
			}
			
			t++;
			
		} //while

	} //solve
	
}
