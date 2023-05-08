package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj2225 {

    static int solution(int n, int k) {

        int divider = 1_000_000_000;
        int[][] D = new int[k + 1][n + 1];

        Arrays.fill(D[1], 1);

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) D[i][0] = 1;
                else {
                    D[i][j] = (D[i][j - 1] + D[i - 1][j]) % divider;
                }
            }
        }

        return D[k][n] % divider;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, K));

    }

}
