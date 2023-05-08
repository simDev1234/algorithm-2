package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Bj17103 {

    static int getPartitionCnt(int n) {

        int cnt = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (isPrime(i) && isPrime(n - i)) {
                cnt++;
            }
        }

        return cnt;
    }

    static int[] getPrimes(int n) {

        int[] primes = new int[n];

        Arrays.fill(primes, 1);

        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (primes[i] == 0) continue;
            for (int j = 2; j < n; j+=i) {
                primes[j] = 0;
            }
        }

        return primes;
    }

    static boolean isPrime(int n) {
        if (n == 1) return false;

        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if ((n % i) == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        boolean[] num = new boolean[1000001];
        num[0] = num[1] = true;
        for (int i = 2; i * i <= 1000000; i++) {
            if (!num[i]) {
                for (int j = i + i; j <= 1000000; j += i) {
                    num[j] = true;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int ans = 0;
            for (int j = 2; j <= n / 2; j++) {
                if (!num[j] && !num[n - j]) ans++;
            }
            System.out.println(ans);
        }

    }

}
