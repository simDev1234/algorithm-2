public class Pg77485 {
}

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] map = new int[rows][columns];
        int val = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = val++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = turn(map, query);
        }

        return answer;
    }

    public int turn(int[][] map, int[] query){
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;
        int prev = map[x1][y1];
        int minVal = prev;

        // 왼쪽 세로라인 회전
        for (int i = x1; i < x2; i++) {
            map[i][y1] = map[i + 1][y1];
            minVal = Math.min(minVal, map[i][y1]);
        }

        // 아래쪽 가로라인 회전
        for (int j = y1; j < y2; j++) {
            map[x2][j] = map[x2][j + 1];
            minVal = Math.min(minVal, map[x2][j]);
        }

        // 오른쪽 세로라인 회전
        for (int i = x2; i > x1; i--) {
            map[i][y2] = map[i - 1][y2];
            minVal = Math.min(minVal, map[i][y2]);
        }

        // 위쪽 가로라인 회전
        for (int j = y2; j > y1; j--) {
            map[x1][j] = map[x1][j - 1];
            minVal = Math.min(minVal, map[x1][j]);
        }

        // 회전 마무리
        map[x1][y1 + 1] = prev;

        return minVal;
    }
}