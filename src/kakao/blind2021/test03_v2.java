package kakao.blind2021;

import java.util.Arrays;

public class test03_v2 {
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
		String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
		
		System.out.println(Arrays.toString(solution(info, query)));
	}
	
	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		String queryStr, infoStr;
		String[] attrs = new String[5], tmp;
		boolean hasAll;
		int count;
		
		
		String[][] applicants = new String[info.length][];
		for (int i = 0; i < info.length; i++) {	// 속성별로 지원자 정보 저장
			applicants[i] = info[i].split(" ");
		}
		
		for (int i = 0; i < query.length; i++) {	// 쿼리별
			count = 0;
			queryStr = query[i];
			tmp = queryStr.split(" and ");
			for (int j = 0; j < 4; j++) {
				attrs[j] = tmp[j];
			}
			tmp = attrs[3].split(" ");
			attrs[3] = tmp[0];
			attrs[4] = tmp[1];
//			System.out.println(Arrays.toString(attrs));
			
			for (int j = 0; j < applicants.length; j++) {	// 모든 지원자에 대해 검사
				hasAll = true;
				for (int k = 0; k < 4; k++) {	// 속성 4개 검사
					if(attrs[k].equals("-")) continue;
					if(!applicants[j][k].equalsIgnoreCase(attrs[k])) {
						hasAll = false;
						break;
					}
				}
				if(hasAll) {
					// 제시된 점수 이상인지 확인
					int score = Integer.parseInt(attrs[4]);	// 기준점수
					int applicantScore = Integer.parseInt(applicants[j][4]);	// 지원자점수
					if(score <= applicantScore)
						count++;
				}
			}
			
			answer[i] = count;
		}
		
        return answer;
    }
}
