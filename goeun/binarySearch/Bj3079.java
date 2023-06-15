package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        long left = 1, right = 0;
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, times[i]);
        }
        right = right * M;

        while (left <= right) {

            long mid = (left + right)/2;
            long cnt = 0;

            for (int i = 0; i < N; i++) {
                long peopleCapacity = mid / times[i];
                if (cnt >= M) break;
                cnt += peopleCapacity;
            }

            if (cnt >= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);

    }

}
