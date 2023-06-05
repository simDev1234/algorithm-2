package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj14719 {

    static int solution(int H, int W, int[] heights) {

        int answer = 0;

        // 양 옆의 블럭을 제외하고
        for (int i = 1; i < W; i++) {

            // 현재 위치를 기준으로
            int left = heights[i];
            int right = heights[i];

            // 좌측 부분에서 높이가 가장 높은 값
            for (int j = 0; j < i; j++) {
                left = Math.max(left, heights[j]);
            }

            // 우측 부분에서 높이가 가장 높은 값
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, heights[j]);
            }

            // 중에 저 낮은 높이를 찾는다.
            int min = Math.min(left, right);

            // 그리고 현재 블록의 높이만큼 고이는 높이를 답에 더해준다.
            answer += min - heights[i];
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] heights = new int[W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(H, W, heights));

    }

}
