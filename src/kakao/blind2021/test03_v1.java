package kakao.blind2021;

public class test03_v1 {
	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];
		String queryStr, infoStr;
		String[] attrs = new String[5], tmp;
		boolean hasAll;
		int count;
		
		for (int i = 0; i < query.length; i++) {
			count = 0;
			queryStr = query[i];
			tmp = queryStr.split(" and ");
			for (int j = 0; j < 4; j++) {
				attrs[j] = tmp[j];
			}
			tmp = attrs[3].split(" ");
			attrs[3] = tmp[0];
			attrs[4] = tmp[1];
			for (int j = 0; j < info.length; j++) {	// 모든 지원자에 대해 검사
				infoStr = info[j];
				hasAll = true;
				for (int k = 0; k < 4; k++) {	// 속성 4개 검사
					if(attrs[k].equals("-")) continue;
					if(!infoStr.contains(attrs[k])) {
						hasAll = false;
						break;
					}
				}
				if(hasAll) {
					// 제시된 점수 이상인지 확인
					int score = Integer.parseInt(attrs[4]);	// 기준점수
					
					// query에서 점수만 빼오기
					int len = infoStr.length()-1, c = 0;
					while(infoStr.charAt(len) != ' ') {
						c++;
						len--;
					}
					int userScore = Integer.parseInt(infoStr.substring(infoStr.length()-c, infoStr.length()));	// 지원자점수
					if(score <= userScore)
						count++;
				}
			}
			
			answer[i] = count;
		}
		
        return answer;
    }
}
