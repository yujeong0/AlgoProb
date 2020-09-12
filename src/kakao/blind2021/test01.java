package kakao.blind2021;

public class test01 {
	public static void main(String[] args) {
		String id = "abcdefghijklmn.p";
		String str = solution(id);
		System.out.println(str);
		System.out.println(str.length());
		
	}
	
	public static String solution(String new_id) {
		StringBuffer sb = new StringBuffer();

		if(new_id.length() != 0) {
	        char[] arr = new_id.toLowerCase().toCharArray();
	        for (int i = 0; i < arr.length; i++) {
				if((arr[i] < 97 || arr[i] > 122) && (arr[i] < 48 || arr[i] > 57) && arr[i] != '-' && arr[i] != '_' && arr[i] != '.') continue;
				sb.append(arr[i]);
			}
	        
	        arr = sb.toString().toCharArray();
	        sb = new StringBuffer();
	        for (int i = 0; i < arr.length; i++) {
	        	if(i != arr.length-1 && arr[i] == '.') {
					int j = i+1, count = 0;
					while(j < arr.length && arr[j++] == '.') {
						count++;
					}
					i += count;
				}
				sb.append(arr[i]);
			}
	        
	        if(sb.length() != 0) {
		        if(sb.charAt(0) == '.') sb.deleteCharAt(0);
		        if(sb.length() != 0 && sb.charAt(sb.length()-1) == '.') sb.deleteCharAt(sb.length()-1);
		        if(sb.length() == 0) sb.append("aaa");
		        else {
		        	if(sb.length() >= 16) { 
		        		sb.delete(15, sb.length());
		        		while(sb.length()!= 0 && sb.charAt(sb.length()-1) == '.') {
		        			sb.deleteCharAt(sb.length()-1);
		        		}
		        		if(sb.length() == 0) sb.append("aaa");
		        	}
		        	else if(sb.length() <= 2) { 
		        		char c = sb.charAt(sb.length()-1);
		        		while(sb.length() < 3) {
		        			sb.append(c);
		        		}
		        	}
		        	
		        }
	        } else {
	        	if(sb.length() == 0) sb.append("aaa");
	        }
	        return sb.toString();
		}
		else {
			if(sb.length() == 0) sb.append("aaa");
			return sb.toString();
		}
        
    }
}
