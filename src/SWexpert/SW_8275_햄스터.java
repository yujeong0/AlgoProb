package SWexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class SW_8275_햄스터 {
	
	static int N,X,M;
	static int[][] info;
	static int[] box;
	static List<int[]> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			box = new int[N+1];	// 우리 수 N개!!
			info = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
				info[i][2] = Integer.parseInt(st.nextToken());
			}
			
			solve(0, 0);
			
			if(list.size() == 0) {
				sb.append("#").append(testcase).append(" -1\n");
			}
			else {
				list.sort(new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						int sum1 = 0;
						for (int i = 0; i < o1.length; i++) {
							sum1 += o1[i];
						}
						int sum2 = 0;
						for (int i = 0; i < o2.length; i++) {
							sum2 += o2[i];
						}
						if(sum1 != sum2)
							return sum2 - sum1;
						else {
							for (int i = 0; i < o1.length; i++) {
								if(o1[i] != o2[i]) {
									return o1[i] - o2[i];
								}
							}
							return 0;
						}
					}
				});
				
				sb.append("#").append(testcase).append(" ");
				int[] arr = list.get(0);
				for (int i = 0; i < arr.length; i++) {
					sb.append(arr[i]).append(" ");
				}
				sb.append("\n");
			}
		} // tc
		
		System.out.println(sb.toString());
		
	} // main
	
	private static void solve(int order, int hamster) {
		if(order > N) {
			return;
		}
		if(hamster > X) {
			solve(order+1, 0);
			return;
		}
		
		box[order] = hamster;
		
		boolean success = true;
		for (int i = 0; i < M; i++) {
			int sum = 0;
			for (int j = info[i][0]; j <= info[i][1]; j++) {
				sum += box[j];
			}
			if(sum != info[i][2]) {
				success = false;
				break;
			}
		}
		
		if(success) {
			int[] tmp = new int[N];
			for (int j = 0; j < N; j++) {
				tmp[j] = box[j+1];
			}
			list.add(tmp);
		}
		
		for (int i = order+1; i <= N; i++) {
			for (int j = 0; j <= X; j++) {
				solve(i, j);
			}
		}
		
	} // solve
}
