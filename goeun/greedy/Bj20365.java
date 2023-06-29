package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj20365 {

    //BBRBRBBR
    //11122333
    //BBRBRBRR
    //11122333
    static int solution(int N, String s) {

        int blueCnt = 0, redCnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i != 0 && s.charAt(i - 1) == s.charAt(i)) continue;
            if (s.charAt(i) == 'B') blueCnt++;
            else redCnt++;
        }

        if (blueCnt > redCnt) {
            return 1 + redCnt;
        } else {
            return 1 + blueCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String s = br.readLine();

        System.out.println(solution(N, s));

    }
}
