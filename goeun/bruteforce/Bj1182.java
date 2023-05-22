package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1182 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 1 2 3 4 5
        // 1 1 1 1 1
        int answer = 0;
        for (int i = 1; i <= (1 << N) - 1; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if ((i & (1 << j - 1)) == (1 << j - 1)) {
                    sum += arr[j - 1];
                }
            }
            if (sum == S) answer++;
        }

        System.out.println(answer);

    }

}
