package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj20117 {

    static int solution(int N, int[] hobanus) {

        Arrays.sort(hobanus);

        int answer = 0;
        if (N % 2 == 0) {
            for (int i = N - 1; i >= N / 2; i--) {
                answer += hobanus[i] * 2;
            }
        } else {
            for (int i = N - 1; i > N / 2; i--) {
                answer += hobanus[i] * 2;
            }
            answer += hobanus[N / 2];
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] hobanus = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hobanus[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, hobanus));

    }

}
