package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Bj17087 {

    static int solution(int curPos, int[] siblings) {

        // 내 위치 - 동생 위치 차이의 절대값의 최소값
        int[] arr = Arrays.stream(siblings)
                .map(x -> Math.abs(curPos - x))
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(x -> (int)x)
                .toArray();

        return gcdArray(arr, 0, arr.length);

    }

    static int gcdArray(int[] arr, int start, int no){
        if (no == 1) {
            return arr[start]; // 숫자가 1개일 때 본인이 gcd
        } else if (no == 2) {
            return gcd(arr[start], arr[start + 1]); // 숫자가 2개일 때
        } else {
            return gcd(arr[start], gcdArray(arr, start + 1, no - 1));
        }
    }


    // 유클리드 호제법 - gcd(a, b) == gcd(b, r) 이며, r이 0이면 b가 최대공약수이다. (단, a > b)
    static int gcd(int a, int b) {

        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] siblings = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            siblings[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(S, siblings));

    }

}
