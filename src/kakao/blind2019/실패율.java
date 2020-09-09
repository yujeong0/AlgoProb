package kakao.blind2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class 실패율 {

	public static void main(String[] args) throws Exception {
		int[] stages = { 2, 1, 2, 6, 2, 4, 3, 3 };
		int N = 5;

		System.out.println(Arrays.toString(solution(5, stages)));
		
	}

	public static int[] solution(int N, int[] stages) {
		int NumOfUser = stages.length;
		int[] answer = new int[N + 1];
		int[] users = new int[N+1];
		int stage;
		for (int i = 0; i < NumOfUser; i++) {	// 유저 수만큼
			stage = stages[i];
			if(stage > N) continue;
			users[stage]++;	// 스테이지의 머물러있는 유저 수 저장
		}
		
		System.out.println("users");
		System.out.println(Arrays.toString(users));

		List<double[]> list = new ArrayList<>();
		int num = users[1];
		list.add(new double[] {1, (double)((double)num/(double)NumOfUser)});	//{ 스테이지 번호, 실패율 }
		int a;
		for (int i = 2; i <= N; i++) {
			if(NumOfUser-num == 0) list.add(new double[] {i, 0});
			else {
				
				list.add(new double[] {i, (double)((double)users[i]/(double)(NumOfUser-num))});
			}
			num += users[i];
		}
		
		Collections.sort(list, new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				if(o1[1] < o2[1]) 
					return 1;
				
				else if(o1[1] > o2[1])
					return -1;
				
				else 
					return (int) (o1[0]-o2[0]);
				
			}
			
		});
		
		Iterator<double[]> iter = list.iterator();
		int i = 1;
		while(iter.hasNext()) {
			answer[i++] = (int) iter.next()[0];
		}
		
		return Arrays.copyOfRange(answer, 1, N+1);
	}

}
