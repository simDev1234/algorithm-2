package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj14891 {

    static List<List<Integer>> gears;

    public static int solution(int[][] rotates, int rotateCnt) {

        for (int i = 0; i < rotateCnt; i++) {
            int gearNo = rotates[i][0];
            int dir = rotates[i][1];
            moveLeftGear( gearNo - 1, -dir);
            moveRightGear( gearNo + 1, -dir);
            rotate(gearNo, dir);
        }

        int answer = 0; int tmp = 1;
        for (int i = 1; i < 5; i++) {
            answer += gears.get(i).get(0) * tmp;
            tmp *= 2;
        }

        return answer;
    }

    private static void moveRightGear(int gearNo, int dir){
        if (gearNo > 4) return;
        if (gears.get(gearNo).get(6) == gears.get(gearNo - 1).get(2)) return;
        moveRightGear(gearNo + 1, -dir);
        rotate(gearNo, dir);
    }

    private static void moveLeftGear(int gearNo, int dir){
        if (gearNo < 1) return;
        if (gears.get(gearNo).get(2) == gears.get(gearNo + 1).get(6)) return;
        moveLeftGear(gearNo - 1, -dir);
        rotate(gearNo, dir);
    }

    private static void rotate(int gearNo, int dir) {
        if (dir == -1) {
            int first = gears.get(gearNo).remove(0);
            gears.get(gearNo).add(first);
        } else {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(gears.get(gearNo).remove(7));
            for (int i = 0; i <= 6; i++) {
                tmp.add(gears.get(gearNo).get(i));
            }
            gears.set(gearNo, tmp);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears = new ArrayList();

        for (int i = 0; i < 5; i++) {
            gears.add(new ArrayList<>());
        }

        for (int i = 1; i <= 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears.get(i).add(input.charAt(j) - '0');
            }
        }

        int rotateCnt = Integer.parseInt(br.readLine());
        int[][] rotates = new int[rotateCnt][2];
        for (int i = 0; i < rotateCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rotates[i][0] = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
            rotates[i][1] = Integer.parseInt(st.nextToken()); // 방향
        }

        System.out.println(solution(rotates, rotateCnt));

    }

}
