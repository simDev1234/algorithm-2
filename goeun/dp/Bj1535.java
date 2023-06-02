package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1535 {

    // https://st-lab.tistory.com/141
    // 짐을 쪼갤 수 있는 경우 - Fraction Knapsack (그리디)
    // 아닌 경우 - 0/1 Knapsack Problem(DP)
    static int knapsack(int N, int[] w, int[] h) {

        int[][] D = new int[N + 1][100];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= 99; j++) {
                if (w[i] > j) {
                    // 넣을 수가 없는 경우에 안 넣는다.
                    D[i][j] = D[i - 1][j];
                } else { // 넣을 수 있을 때에
                    // 이번 걸 안 넣거나, 또는 이전 걸 안 넣고 이번걸 넣어보거나
                    D[i][j] = Math.max(D[i - 1][j], D[i - 1][j - w[i]] + h[i]);
                }
            }
        }

        return D[N][99];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] w = new int[N + 1];
        int[] h = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(knapsack(N, w, h));

    }

}
