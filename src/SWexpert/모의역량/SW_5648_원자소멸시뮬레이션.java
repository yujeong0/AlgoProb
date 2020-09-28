package SWexpert.모의역량;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class SW_5648_원자소멸시뮬레이션 {
	static class Atom {
		double x, y; 
		int d, energy;
		
		Atom(double x, double y, int d, int energy) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.energy = energy;
		}
		
	}
	static double[][] dir = { {-0.5, 0}, {0.5, 0}, {0, -0.5}, {0, 0.5} };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int SUM, N;
		int x, y, d, energy;
		List<Atom> atomList = new ArrayList<>();
		List<Integer> crushedList = new ArrayList<>();
		
		for (int testcase = 1; testcase <= T; testcase++) {
			atomList.clear();
			crushedList.clear();
			SUM = 0;
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				energy = Integer.parseInt(st.nextToken());
				
				atomList.add(new Atom(x, y, d, energy));
			}	// end of input
			
			Atom me, you;
			boolean crush;
			double meX, meY, youX, youY;
			Iterator<Atom> iter1, iter2; 
			Iterator<Integer> iter3;
			int i, j;
			
			
			// 여기서 0.5초마다 while 문 넣어야하는데, 영원히 소멸되지 않는 애들만 남아있으면 break 해야할것.. 근데 그걸 어떻게 체크하지..?
			
			while(true) {
				iter1 = atomList.iterator();
				if(!iter1.hasNext()) break;
				
				i = 0;
				while(iter1.hasNext()) {
					crush = false;
					me = iter1.next();
					meX = me.x + dir[me.d][0];
					meY = me.y + dir[me.d][1];
					
					if(meX > 1000 || meX < -1000 || meY > 1000 || meY < -1000)	iter1.remove(); 	//영원히 소멸 안 되는 것들 지우기

					iter2 = atomList.iterator();
					j = 0;
					while(iter2.hasNext()) {
						if(i == j) continue;
						
						you = iter2.next();
						youX = you.x + dir[you.d][0];
						youY = you.y + dir[you.d][1];
						
						if(meX == youX && meY == youY) {
							crush = true;
							crushedList.add(j);
						}
						j++;
					}
					
					
					if(crush) {
						crushedList.add(i);
					}
					
					i++;
				}	// 0.5초 끝
				
				// 모든  충돌 다 고려했으니 소멸하는 친구들 없애기
				iter3 = crushedList.iterator();
				int n;
				while(iter3.hasNext()) {
					n = iter3.next();
					atomList.remove(n);
				}
				
			}	// 0.5초 이동 반복
			
			
			sb.append("#"+ testcase + " " + SUM +"\n");
		}	// end of tc
		
	}	// end of main
}
