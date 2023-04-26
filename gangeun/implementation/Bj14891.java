import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Bj14891 {

    private static int[][] gearArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gearArr = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gearArr[i][j] = s.charAt(j) - 48;
            }
        }

        int k = Integer.parseInt(br.readLine());

        while (k-- != 0) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int arrNum = Integer.parseInt(st.nextToken()) - 1;

            //true 시계   false 반시계
            boolean direction = Integer.parseInt(st.nextToken()) == 1 ? true : false;

            switch (arrNum) {

                case 0:

                    // 0, 1 번째 맞닿은 곳이 같으면 로직 끝
                    if (isEqualsMagnetic(0, 1, 2, 6)) {
                        shiftArray(0, direction);
                        continue;
                    }

                    // 0,1번째 맞닿은 곳이 다르고, 1, 2번째 맞닿은 곳이 같으면 0번만 돌려주고 로직 끝
                    if (isEqualsMagnetic(1, 2, 2, 6)) {
                        shiftArray(0, direction);
                        shiftArray(1, !direction);
                        continue;
                    }

                    // 0,1,2, 전부 맞닿은 곳이 다ㅏ르고 2,3, 맞닿은 곳이 같으면 0,1 번만 돌려주고 로직 끝
                    if (isEqualsMagnetic(2, 3, 2, 6)) {
                        shiftArray(0, direction);
                        shiftArray(1, !direction);
                        shiftArray(2, direction);
                        continue;
                    }

                    // 여기까지 통과하면 전부다 돌려줌
                    shiftArray(0, direction);
                    shiftArray(1, !direction);
                    shiftArray(2, direction);
                    shiftArray(3, !direction);
                    break;

                case 1:

                    /**
                     * 0,1 같고 , 1, 2 같으면 걍 1번달 돌려주고 끝
                     */

                    // 0,1,2, 다 같으면 1번 톱니바퀴만 회전하고 끝
                    if (isEqualsMagnetic(0, 1, 2, 6) && isEqualsMagnetic(1,2,2,6)) {
                        shiftArray(1, direction);
                        continue;
                    }

                    // 0,1 같고 1,2, 다르면 ?
                    if (isEqualsMagnetic(0, 1, 2, 6) && !isEqualsMagnetic(1, 2, 2, 6)) {

                        // 2,3 다르면 1,2번만 돌려줌
                        if (isEqualsMagnetic(2, 3, 2, 6)) {
                            shiftArray(1, direction);
                            shiftArray(2, !direction);
                        }  else {
                            shiftArray(1, direction);
                            shiftArray(2, !direction);
                            shiftArray(3, direction);
                        }
                        continue;
                    }

                    // 0,1 다르고 1,2, 같으면 0,1 만 돌려주고 끝
                    if (!isEqualsMagnetic(0, 1, 2, 6) && isEqualsMagnetic(1, 2, 2, 6)) {
                        shiftArray(0, !direction);
                        shiftArray(1, direction);
                        continue;
                    }

                    // 0,1 다르고 1,2도 다르면
                    if (!isEqualsMagnetic(0, 1, 2, 6) && !isEqualsMagnetic(1,2,2,6)) {

                        // 2,3 다르면 1,2번만 돌려줌
                        if (isEqualsMagnetic(2, 3, 2, 6)) {
                            shiftArray(0, !direction);
                            shiftArray(1, direction);
                            shiftArray(2, !direction);
                        }  else {
                            shiftArray(0, !direction);
                            shiftArray(1, direction);
                            shiftArray(2, !direction);
                            shiftArray(3, direction);
                        }
                    }
                    break;
                case 2:

                    // 1,2,3 다 같으면 2번만 회전하고 끝
                    if (isEqualsMagnetic(1, 2, 2, 6) && isEqualsMagnetic(2,3,2,6)) {
                        shiftArray(2, direction);
                        continue;
                    }

                    // 2,3 같고 1,2 다르면
                    if (isEqualsMagnetic(2, 3, 2, 6) && !isEqualsMagnetic(1, 2, 2, 6)) {

                        // 0,1 같으면 1,2번만 돌려줌
                        if (isEqualsMagnetic(0, 1, 2, 6)) {
                            shiftArray(1,!direction);
                            shiftArray(2,direction);
                        } else {
                            shiftArray(0,direction);
                            shiftArray(1,!direction);
                            shiftArray(2,direction);
                        }
                        continue;
                    }

                    // 2,3 다르고 1,2, 같으면 2,3 만 돌려주고 끝
                    if (!isEqualsMagnetic(2, 3, 2, 6) && isEqualsMagnetic(1, 2, 2, 6)) {
                        shiftArray(2,direction);
                        shiftArray(3,!direction);
                        continue;
                    }

                    // 2,3도 다르고 1,2,도 다르면
                    if (!isEqualsMagnetic(2, 3, 2, 6) && !isEqualsMagnetic(1, 2, 2, 6)) {
                        // 0,1 같으면 1,2,3만 돌려줌
                        if (isEqualsMagnetic(0, 1, 2, 6)) {
                            shiftArray(1,!direction);
                            shiftArray(2,direction);
                            shiftArray(3,!direction);
                        } else {
                            shiftArray(0,direction);
                            shiftArray(1,!direction);
                            shiftArray(2,direction);
                            shiftArray(3,!direction);
                        }
                    }
                    break;
                case 3:

                    // 2, 3 번째 맞닿은 곳이 같으면 로직 끝
                    if (isEqualsMagnetic(2, 3, 2, 6)) {
                        shiftArray(3, direction);
                        continue;
                    }

                    // 2,3번째 맞닿은 곳이 다르고, 1, 2번째 맞닿은 곳이 같으면 2,3번만 돌려주고 로직 끝
                    if (isEqualsMagnetic(1, 2, 2, 6)) {
                        shiftArray(2, !direction);
                        shiftArray(3, direction);
                        continue;
                    }

                    // 3,2,1 전부 맞닿은 곳이 다ㅏ르고 0,1 맞닿은 곳이 같으면 3,2,1 번만 돌려주고 로직 끝
                    if (isEqualsMagnetic(0, 1, 2, 6)) {
                        shiftArray(1, direction);
                        shiftArray(2, !direction);
                        shiftArray(3, direction);
                        continue;
                    }

                    // 여기까지 통과하면 전부다 돌려줌
                    shiftArray(0, !direction);
                    shiftArray(1, direction);
                    shiftArray(2, !direction);
                    shiftArray(3, direction);
                    break;

            }
        }
        System.out.println(getSum());

    }

    private static void shiftArray(int arrNum, boolean direction) {
        if (direction) { // 시계방향
            int temp = gearArr[arrNum][7];
            for (int i = 6; i >= 0; i--) {
                gearArr[arrNum][i + 1] = gearArr[arrNum][i];
            }
            gearArr[arrNum][0] = temp;
        }

        if (!direction) { // 반시계 방향
            int temp = gearArr[arrNum][0];
            for (int i = 0; i < 7; i++) {
                gearArr[arrNum][i] = gearArr[arrNum][i + 1];
            }
            gearArr[arrNum][7] = temp;
        }
    }

    private static boolean isEqualsMagnetic(int arr1, int arr2, int arr1Index, int arr2Index) {
        return gearArr[arr1][arr1Index] == gearArr[arr2][arr2Index];
    }

    private static int getSum() {

        int sum = 0;

        sum += gearArr[0][0] == 0 ? 0 : 1;
        sum += gearArr[1][0] == 0 ? 0 : 2;
        sum += gearArr[2][0] == 0 ? 0 : 4;
        sum += gearArr[3][0] == 0 ? 0 : 8;

        return sum;
    }
}
