package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj15565 {

    // 라이언과 어피치 인형 N개에 대해, 라이언 인형이 K개 이상 있는 작은 연속된 인형들의 집합의 크기
    // * 라이언 1, 어피치 2
    static int solution(int N, int K, int[] dolls) {

        int left = 0, right = 0;
        int count = 0, answer = Integer.MAX_VALUE;

        // 오른쪽 포인터를 옮기면서
        for (right = 0; right < N; right++) {

            // 라이언의 갯수를 카운팅
            if (dolls[right] == 1) count++;

            // 라이언의 갯수가 K 보다 크면 왼쪽 포인터를 이동
            while ((dolls[left] == 2 || count > K) && left < right) {
                if (dolls[left] == 1) count--;
                left++;
            }

            if (count == K) answer = Math.min(answer, right - left + 1);

        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] dolls = new int[N];
        for (int i = 0; i < N; i++) {
            dolls[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, K, dolls));

    }

}
