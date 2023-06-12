package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Bj16198 {

    // 에너지 구슬로 에너지를 모으는데
    // 방법 )
    // [1] 에너지 구슬을 하나 고른다 - x번호 (* 첫번째와 마지막은 고를 수 없다)
    // [2] x번째 구슬은 제거한다.
    // [3] Wx-1 * Wx+1 의 에너지를 모을 수 있다. (양 옆의 에너지의 곱하기)
    // [4] N을 1감소시키고, 에너지 구슬을 1부터 N까지 다시 번호매긴다.
    // 이때, 모을 수 있는 에너지 양의 최대값은?
    static long maxValue = 0;
    static int N;
    static int[] W;
    static boolean[] visited;

    static void solution(int cnt, long sum) {

        if (cnt == N - 2) {
            maxValue = Math.max(maxValue, sum);
            return;
        }

        for (int i = 1; i < N - 1; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                solution(cnt + 1, sum + getPartSum(i));
                visited[i] = false;
            }
        }

    }

    static int getPartSum(int middleIdx) {

        int left = middleIdx - 1, right = middleIdx + 1;
        for (int i = middleIdx - 1; i >= 0; i--) {
            if (visited[i] == false) {
                left = i;
                break;
            }
        }

        for (int i = middleIdx + 1; i <= N - 1; i++) {
            if (visited[i] == false){
                right = i;
                break;
            }
        }

        return W[left] * W[right];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }

        solution(0, 0);

        System.out.println(maxValue);

    }

}
