package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj2133 {

    // pf https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2133-%EC%9E%90%EB%B0%94-%ED%83%80%EC%9D%BC-%EC%B1%84%EC%9A%B0%EA%B8%B0-BOJ-2133-JAVA
    // f(n) = f(n-1)*3 + 2 + **1~(n-4)/2에 대해 f(2k)*2
    static int solution(int n) {

        if (n % 2 == 1) {
            return 0;
        }

        int[] D = new int[Math.max(n / 2, 2)];
        D[0] = 3;
        D[1] = 11;
        int tmp = 0;
        for (int i = 2; i < n / 2; i++) {
            D[i] = D[i - 1] * 3 + 2 + (tmp += D[i - 2] * 2);
        }

        return D[n / 2 - 1];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(solution(N));

    }

}
