package array;

import java.util.Arrays;

class Solution{

    static int[][] pan;

    public int[] solution(int rows, int columns, int[][] queries) {

        // 판
        pan = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                pan[i][j] = i * columns + j + 1;
            }
        }

        // 정답 배열
        int[] answer = new int[queries.length];

        // 이동하면서 최솟값을 입력
        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }

        return answer;
    }

    private int rotate(int[] query) {

        // 좌표값 구하기
        int r1 = query[0] - 1;
        int c1 = query[1] - 1;
        int r2 = query[2] - 1;
        int c2 = query[3] - 1;

        // 시작점 값
        int start = pan[r1][c1];
        
        // 최솟값
        int min = start;

        // 좌측면
        for (int i = r1; i < r2; i++) {
            
            pan[i][c1] = pan[i + 1][c1];

            min = Math.min(min, pan[i][c1]);
            
        }

        // 하단면
        for (int i = c1; i < c2; i++) {

            pan[r2][i] = pan[r2][i + 1];

            min = Math.min(min, pan[r2][i]);

        }

        // 우측면
        for (int i = r2; i > r1; i--) {

            pan[i][c2] = pan[i - 1][c2];

            min = Math.min(min, pan[i][c2]);

        }

        // 상층면
        for (int i = c2; i > c1; i--) {

            pan[r1][i] = pan[r1][i - 1];

            min = Math.min(min, pan[r1][i]);

        }

        // 시작점 변경
        pan[r1][c1 + 1] = start;

        return min;
    }

}

public class Pg77485 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] answer = solution.solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}});
        System.out.println(Arrays.toString(answer));
    }

}
