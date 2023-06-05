package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// N 개의 돌이 일렬로 나열
// 돌-돌 점프 3가지 : 1) 작은 점프 (현재 - 다음), 2) 큰 점프 (1개 돌 건너뛰기), 3) 매우 큰 점프 (2개 돌 건너뛰기)
// 에너지 소비량 : 1),2)는 돌의 번호에 따라 다름, 3)은 단 한 번만 가능하고 k만큼 소비
// ㄴ 에너지의 최솟값

public class Bj21317 {

    static int solution(int N, int K, int[][] energy) {

        int[][] D = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            D[i][0] = Integer.MAX_VALUE;
            D[i][1] = Integer.MAX_VALUE;
        }

        D[1][0] = 0;
        D[1][1] = 0;

        for (int i = 1; i < N; i++) {
            if (i + 1 <= N) {
                D[i + 1][0] = Math.min(D[i + 1][0], D[i][0] + energy[i][0]);
                D[i + 1][1] = Math.min(D[i + 1][1], D[i][1] + energy[i][0]);
            }

            if (i + 2 <= N) {
                D[i + 2][0] = Math.min(D[i + 2][0], D[i][0] + energy[i][1]);
                D[i + 2][1] = Math.min(D[i + 2][1], D[i][1] + energy[i][1]);
            }

            if (i + 3 <= N) {
                D[i + 3][1] = Math.min(D[i + 3][1], D[i][0] + K);
            }
        }

        return Math.min(D[N][0], D[N][1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] energy = new int[N][2];
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            energy[i][0] = Integer.parseInt(st.nextToken());
            energy[i][1] = Integer.parseInt(st.nextToken());
        }

        int K = Integer.parseInt(br.readLine());

        System.out.println(solution(N, K, energy));

    }

}
