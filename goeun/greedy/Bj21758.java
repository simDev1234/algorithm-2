package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj21758 {

    // 1. 꿀통을 우측 끝에 배치할 경우 : 첫번째는 0에 배치
    // 2. 꿀통을 좌측 끝에 배치할 경우  : 첫번째는 N - 1에 배치
    // 3. 꿀통을 중간 어디에 배치할 경우 : 양끝에 배치
    static long solution(int N, int[] honey) {

        long firstBee = 0, secondBee = 0;
        long answer = 0;

        long[][] sum = new long[N][2];

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                sum[i][0] = honey[i];
                sum[i][1] = Arrays.stream(honey).sum();
            }
            else {
                sum[i][0] = sum[i - 1][0] + honey[i];
                sum[i][1] = sum[i - 1][1] - honey[i];
            }
        }

        // 꿀통을 우측 끝에 배치할 경우 : 첫번째는 0에 배치
        for (int i = 1; i < N - 1; i++) {
            firstBee = sum[N - 1][0] - honey[0] - honey[i];
            secondBee = sum[N - 1][0] - sum[i][0];
            answer = Math.max(answer, firstBee + secondBee);
        }

        // 꿀통을 좌측 끝에 배치할 경우  : 첫번째는 N - 1에 배치
        for (int i = N - 2; i >= 1; i--) {
            firstBee = sum[0][1] - honey[N - 1] - honey[i];
            secondBee = sum[0][1] - sum[i][1];
            answer = Math.max(answer, firstBee + secondBee);
        }

        // 꿀통을 중간 어디에 배치할 경우 : 양끝에 배치
        for (int i = 1; i < N - 1; i++) {
            long tmp = sum[N - 1][0] - honey[0] - honey[N - 1] + honey[i];
            answer = Math.max(answer, tmp);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] honey = new int[N];
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, honey));

    }

}
