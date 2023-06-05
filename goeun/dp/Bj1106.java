package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj1106 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] D = new int[C + 101];

        Arrays.fill(D, 999999999);
        D[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            for (int j = people; j < C + 101; j++) {
                D[j] = Math.min(D[j], cost + D[j - people]);
            }
        }

        int answer = D[C];
        for (int i = C + 1; i < C + 101; i++) {
            answer = Math.min(answer, D[i]);
        }
        System.out.println(answer);

    }

}
