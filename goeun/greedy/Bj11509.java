package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11509 {

    static int solution(int N, int[] balloons) {

        int cnt = 0;
        int[] arrows = new int[1000001]; // 각 높이에 맞는 화살의 갯수

        // 1 2 3 4 5
        for (int i = 0; i < N; i++) {
            // 현재 높이로 날아오는 화살이 있는 경우
            if (arrows[balloons[i]] > 0) {
                arrows[balloons[i]]--; // 화살을 쏘고
                arrows[balloons[i] - 1]++; // 1 낮은 높이로 화살을 넣어준다.
            }
            // 현재 높이로 날아오는 화살이 없는 경우
            else {
                cnt++; // 화살이 필요하다.
                arrows[balloons[i] - 1]++; // 1 낮은 높이로 화살을 넣어준다.
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] balloons = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            balloons[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, balloons));

    }

}
