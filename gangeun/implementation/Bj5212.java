import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj5212 {

    public static void main(String[] args) throws IOException {

        int[] moveX = {-1, 1, 0, 0};
        int[] moveY = {0, 0, -1, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] remakeMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                if (s.charAt(j) == 'X') {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] == 0) {
                    continue;
                }

                int count = 0;

                for (int k = 0; k < 4; k++) {

                    int nx = i + moveX[k];
                    int ny = j + moveY[k];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C || map[nx][ny] == 0) {
                        count++;
                    }
                }

                if (count < 3) {
                    remakeMap[i][j] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();


        //TODO
        // 먼저 시작 i 값 세로 가장 위
        // i 종료 값 세로 가장 밑
        // j 시작값 가로 가장 왼쪽
        // j 종료값 가로 가장 오른쪽 1인거 구하면 됨

        int topLeft = Integer.MAX_VALUE;
        int topRight = -1;
        int lowerLeft = Integer.MAX_VALUE;
        int lowerRight = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (remakeMap[i][j] == 0) {
                    continue;
                }

                if (i < topLeft) {
                    topLeft = i;
                }

                if (j < lowerLeft) {
                    lowerLeft = j;
                }

                if (i > topRight) {
                    topRight = i;
                }

                if (j > lowerRight) {
                    lowerRight = j;
                }
            }
        }


        for (int i = topLeft; i <= topRight; i++) {
            for (int j = lowerLeft; j <= lowerRight; j++) {
                if (remakeMap[i][j] == 0) {
                    sb.append(".");
                } else {
                    sb.append("X");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
