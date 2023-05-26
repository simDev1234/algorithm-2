package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2138 {

    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[] bulbs;
    static int[] bulbs2;
    static int[] target;

    static void solution(int cur, int cnt) {

        if (cur == N) {
            if (bulbs[cur - 1] == target[cur - 1]) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        if (bulbs[cur - 1] != target[cur - 1]) {
            useSwitch(cur);
            solution(cur + 1, cnt + 1);
        } else {
            solution(cur + 1, cnt + 1);
        }

    }

    static void useSwitch(int idx) {
        if (idx - 1 >= 0) bulbs[idx - 1] = (bulbs[idx - 1] + 1) % 2;
        bulbs[idx] = (bulbs[idx] + 1) % 2;
        if (idx + 1 < N)bulbs[idx + 1] = (bulbs[idx + 1] + 1) % 2;
    }

    // 0 0 0
    // 0 1 0
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        bulbs = new int[N];
        bulbs2 = new int[N];
        target = new int[N];
        String input1 = br.readLine();
        String input2 = br.readLine();
        for (int i = 0; i < N; i++) {
            bulbs[i] = input1.charAt(i) - '0';
            bulbs2[i] = input1.charAt(i) - '0';
            target[i] = input2.charAt(i) - '0';
        }

        solution(1, 0);
        bulbs = bulbs2;
        useSwitch(0);
        solution(1, 1);

        System.out.println(answer);

    }

}
