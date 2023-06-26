package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj17615 {

    // BBRBBBBR
    static int solution(int N, String balls){

        int answer = Integer.MAX_VALUE, redCnt = 0, blueCnt = 0;

        for (int i = 0; i < N; i++) {
            if (balls.charAt(i) == 'R') redCnt++;
            else blueCnt++;
        }
        // 1. 전체를 다 옮기는 경우
        answer = Math.min(redCnt, blueCnt);

        // 2. 좌측부터 시작하여 연속된 색깔의 공을 제거한 경우
        int sequentialCnt = 0;
        for (int i = 0; i < N; i++) {
            if (balls.charAt(0) != balls.charAt(i)) break;
            sequentialCnt++;
        }

        if (balls.charAt(0) == 'R') {
            // 좌측에서부터 연속된 R의 갯수를 제거한 수
            answer = Math.min(answer, redCnt - sequentialCnt);
        } else {
            // 좌측에서부터 연속된 B의 갯수를 제거한 수
            answer = Math.min(answer, blueCnt - sequentialCnt);
        }

        // 3. 우측부터 시작하여 연속된 색깔의 공을 제거한 경우
        sequentialCnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (balls.charAt(N - 1) != balls.charAt(i)) break;
            sequentialCnt++;
        }

        if (balls.charAt(N - 1) == 'R') {
            // 우측에서부터 연속된 R의 갯수를 제거한 수
            answer = Math.min(answer, redCnt - sequentialCnt);
        } else {
            // 우측에서부터 연속된 B의 갯수를 제거한 수
            answer = Math.min(answer, blueCnt - sequentialCnt);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        System.out.println(solution(N, s));

    }

}
