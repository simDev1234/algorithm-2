package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj22869 {

    static boolean solution(int n, int k, int[] arr) {

        int[] D = new int[n];

        Arrays.fill(D, Integer.MAX_VALUE);

        D[0] = 0;

        for (int i = 0; i < n; i++) {

            if (D[i] > k) continue;

            for (int j = i + 1; j < n; j++) {
                int power = (j - i) * (1 + Math.abs(arr[i] - arr[j]));
                if (D[i] <= k) {
                    D[j] = Math.min(D[j], power);
                }
            }
        }

        return D[n - 1] <= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, K, arr) == true ? "YES" : "NO");

    }

}
