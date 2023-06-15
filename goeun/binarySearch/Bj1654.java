package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 오영식의 랜선 갯수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 갯수
        int[] cables = new int[K];
        long left = 1, right = 0;
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, cables[i]);
        }
        long mid = (left + right) / 2;

        while (left <= right) {

            mid = (left + right) / 2;
            int cnt = 0;

            for (int i = 0; i < K; i++) {
                cnt += cables[i] / mid;
            }

            if (cnt >= N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);

    }

}
