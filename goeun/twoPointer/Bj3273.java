package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj3273 {

    static int solution(int N, int X, int[] nums) {
        int left = 0, right = N - 1, cnt = 0;

        Arrays.sort(nums);

        while (left < right && right > 0) {
            long sum = nums[left] + nums[right];

            if (sum == X) cnt++;

            if (sum > X) right--;
            else left++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int X = Integer.parseInt(br.readLine());

        System.out.println(solution(N, X, nums));

    }

}
