package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj9084 {

    static void solution(int N, int[] coins, int target){

        int[] D = new int[10001];

        for (int i = 0; i < N; i++) {
            int coin = coins[i];

            D[coin]++;
            for (int j = coin; j <= target; j++) {
                D[j] += D[j - coin];
            }
        }

        System.out.println(D[target]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T --> 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int target = Integer.parseInt(br.readLine());

            solution(N, coins, target);
        }

    }

}
