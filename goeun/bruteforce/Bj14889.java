package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Bj14889 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 스타트팀은 0, 링크팀은 1
        int minDiff = Integer.MAX_VALUE;

        // 링크팀이 될 수 있는 모든 가능성에 대해서
        for (int i = 1; i <= (1 << N) - 1; i++) {

            int cnt = 0;
            int[] check = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                if ((1 & (1 << j - 1)) == (1 << j - 1)) {
                    cnt++;
                    check[j] = 1;
                }
            }

            if (cnt != N / 2) continue;

            int startSum = 0;
            int linkSum = 0;
            for (int j = 1; j <= N; j++) {
                for (int k = i + 1; k <= N; k++) {
                    if (check[i] != check[k]) continue;
                    if (check[i] == 1) linkSum += arr[j][k] + arr[k][j];
                    else startSum += arr[j][k] + arr[k][j];
                }
            }
            minDiff = Math.min(minDiff, Math.abs(startSum - linkSum));

        }

        System.out.println(minDiff);

    }

}
