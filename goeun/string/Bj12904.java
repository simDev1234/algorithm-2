package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj12904 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        // ABBA
        StringBuilder sb = new StringBuilder(t);
        while (s.length() < sb.length()) {

            // 끝에가 A이면 A를 제거한다.
            if (sb.charAt(sb.length() - 1) == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            }
            // 끝에가 B이면 reverse를 해준다.
            else if (sb.charAt(sb.length() - 1) == 'B'){
                sb.deleteCharAt(sb.length() - 1);
                sb.reverse();
            }
        }

        System.out.println(s.equals(sb.toString()) ? 1 : 0);
    }

}
