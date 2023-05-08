package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj1309 {

    static int solution(int n) {

        int[][] D = new int[n][3];
        Arrays.fill(D[0], 1);

        for (int i = 1; i < n; i++) {
            // 이번 행의 없는 경우 전 행의 0, 1, 2 모두 가능
            D[i][0] = (D[i - 1][0] + D[i - 1][1] + D[i - 1][2]) % 9901;
            // 이번 행의 1에 있는 경우, 전 행에 없거나 2에 있어야 함
            D[i][1] = (D[i - 1][0] + D[i - 1][2]) % 9901;
            // 이번 행의 2에 있는 경우, 전 행에 없거나 1에 있어야 함
            D[i][2] = (D[i - 1][0] + D[i - 1][1]) % 9901;
        }

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += D[n - 1][i];
        }

        return answer % 9901;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));

    }

}
