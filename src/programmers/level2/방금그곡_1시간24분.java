package programmers.level2;

public class 방금그곡_1시간24분 {	// 1시간 24분째 3개 틀리는 중
	public String solution(String m, String[] musicinfos) {
		if(musicinfos.length == 0) return "(None)";
		
        String answer = "";
        int MAX = 0;
        for (int i = 0; i < musicinfos.length; i++) {
        	String[] info = musicinfos[i].split(",");
        	String[] t1 = info[0].split(":");
        	String[] t2 = info[1].split(":");
        	int len;
        	if(t1[0].equals(t2[0])) {
        		len = Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]); 
        	}
        	else {
        		len = 60 - Integer.parseInt(t1[1]) + Integer.parseInt(t2[1]); 
        	}
        	
			StringBuilder sb = new StringBuilder();
			int idx = 0;
			for (int t = 0; t < len; t++) {
				if(idx == info[3].length()) idx = 0;
				sb.append(info[3].charAt(idx++));
				if(idx < info[3].length() && info[3].charAt(idx) == '#') {
					sb.append(info[3].charAt(idx++));
				}
			}
			
			String music = sb.toString();
			for (int j = 0; j < music.length(); j++) {
				if(m.length() > music.length()-j) break;
				idx = 0;
				if(m.charAt(idx) == music.charAt(j)) {
					boolean success = true;
					for (int j2 = j+1; j2 < music.length(); j2++) {
						idx++;
						if(idx == m.length()) {
							if(j2 < music.length() && music.charAt(j2) == '#')
								success = false;
							break;
						}
						if(m.charAt(idx) != music.charAt(j2)) {
							success = false;
							break;
						}
					}
					if(success) {
						if(MAX < len) {
							answer = info[2];
							MAX = len;
						}
					}
				}
			}
		}
        
        if(answer.length() == 0) {
        	return "(None)";
        }
        return answer;
	}
}
