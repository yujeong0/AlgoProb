package SW통합공채;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'lastLetters' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING word as parameter.
     */

    public static String lastLetters(String word) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(word.charAt(word.length()-1) + " ");
    	sb.append(word.charAt(word.length()-2));
    	
    	return sb.toString();
    }

}

public class Test02 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String word = bufferedReader.readLine();

        String result = Result.lastLetters(word);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
