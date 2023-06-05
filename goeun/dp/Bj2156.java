package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2156 {

    static int solution(int N, int[] arr) {

        int[] D = new int[N + 3];

        for (int i = 3; i < N + 3; i++) {
            // 1 1 0 1, 1 0 1 1
            D[i] = Math.max(D[i - 3] + arr[i - 1] + arr[i], D[i - 2] + arr[i]);
            // 1 1 0 1 1, 1 0 1 1 0
            D[i] = Math.max(D[i], D[i - 1]);
        }

        return D[N + 2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 3];
        for (int i = 3; i < N + 3; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(solution(N, arr));

    }

}
