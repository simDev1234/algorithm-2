package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj12865 {

    static int solution(int N, int K, int[] w, int[] v) {

        int[][] D = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 베낭에 넣을 수 없는 무게이면
                if (w[i] > j) {
                    // 지금 물건을 안 넣음
                    D[i][j] = D[i - 1][j];
                }
                // 베낭에 넣을 수 있는 무게이면
                else {
                    // 지금 물건을 안 넣음 vs 지금 물건을 넣음
                    D[i][j] = Math.max(D[i - 1][j], D[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        return D[N][K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] w = new int[N + 1];
        int[] v = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, K, w, v));

    }

}
