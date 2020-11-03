package kakao.blind2018;

import java.text.NumberFormat;
import java.time.LocalTime;

// 2020-11-03 12:43~
public class 추석트래픽 {

	public static int solution(String[] lines) throws Exception {
		LocalTime[][] times = new LocalTime[lines.length][2];
		
		int N = lines.length;
		int h, m, s, ms;
		for (int i = 0; i < N; i++) {
			h = Integer.parseInt(lines[i].substring(11, 13));
			m = Integer.parseInt(lines[i].substring(14, 16));
			s = Integer.parseInt(lines[i].substring(17, 19));
			ms = Integer.parseInt(lines[i].substring(20, 23)) * 1000000;
			LocalTime time = LocalTime.of(h, m, s, ms);
			System.out.println(time.toString());
			
			long T = (long)(Double.parseDouble(lines[i].substring(24, lines[i].length()-1)) * 1000000);	// 걸린 시간
			
			times[i][0] = time.minusNanos(T).plusNanos(1000000);	// 시작 시간 저장 (끝난시간 - 걸린시간 + 0.001)
			times[i][1] = time;	// 끝난 시간 저장
		} 
		
		int i = 1;
		LocalTime start = times[0][1];
		int cnt = 0;	// 해당 초에 몇 개 작업하는지 세기
		int max = 0;
		while(true) {
			System.out.println(start);
			if(start.isAfter(times[times.length-1][0])) break;	// 지금 확인하는 시간이 작업들 중 제일 마지막의 끝보다 크면 종료
			if(times[i][1].isBefore(start)) {	// 끝난 시간이 내가 지금 확인하는 시간보다 전이면 pass
				i++;
				continue;
			}
			if(times[i][0].isAfter(start.plusSeconds(1))) {	// 시작시간이 내가 지금확인하는 시간+1초보다 늦으면 카운트 다 한 거임
				if(max < cnt) max = cnt;
				cnt = 0;
				start = start.plusSeconds(1);	// 1초씩 더하면서 몇 개 있는지 세기
				i = 0;
				continue;
			}
			cnt++;
			i++;
		}
	
        return max;
    }
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
//		System.out.println(solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"}));
		
		
		
		
		
	} // main
	
	
} // class
