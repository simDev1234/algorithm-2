package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj13164 {

    static long solution(int N, int K, int[] arr) {
        
        List<Integer> diffs = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            diffs.add(arr[i] - arr[i - 1]);
        }

        Collections.sort(diffs);

        long result = 0;
        for (int i = 0; i < N - K; i++) {
            result += diffs.get(i);
        }
        return result;
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, K, arr));

    }

}
