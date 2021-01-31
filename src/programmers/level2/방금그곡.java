package programmers.level2;

public class 방금그곡 {	// 36분
	public String solution(String m, String[] musicinfos) {
		if(musicinfos.length == 0) return "(None)";
		
		m = m.replace("C#", "c").replace("D#", "d").replace("E#", "e").replace("F#", "f").replace("G#","g").replace("A#","a");
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
        		len = 0;
        		len += 60 * (Integer.parseInt(t2[0]) - Integer.parseInt(t1[0])); 
    			len += Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
        	}
        	
        	info[3] = info[3].replace("C#", "c").replace("D#", "d").replace("E#", "e").replace("F#", "f").replace("G#","g").replace("A#","a");

        	StringBuilder sb = new StringBuilder();
			int idx = 0;
			for (int t = 0; t < len; t++) {
				if(idx == info[3].length()) idx = 0;
				sb.append(info[3].charAt(idx++));
			}
			
			String music = sb.toString();
			if(music.contains(m)) {
				if(MAX < len) {
					answer = info[2];
					MAX = len;
				}
			}
		}
        
        if(answer.length() == 0) {
        	return "(None)";
        }
        
        return answer;
	}

	public static void main(String[] args) {
//		System.out.println(new 방금그곡().solution("BC"
//				, new String[] {"03:00,03:04,FOO,ABC#"}));
		System.out.println(new 방금그곡().solution("ABC"
				, new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
//		System.out.println(new 방금그곡().solution("ABCDEFG"
//				, new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
//		System.out.println(new 방금그곡().solution("CC#BCC#BCC#BCC#B"
//				, new String[] {"02:50,03:20,FOO,CC#B", "04:59,04:07,BAR,CC#BCC#BCC#B"}));
	}
}
