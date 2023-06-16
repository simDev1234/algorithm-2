package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj20436 {

    final static Map<Character, int[]> KEYBOARD_POSITIONS = new HashMap<>();
    final static List<Character> vowels = Arrays.asList('y','u','i','o','p','h','j','k','l','b','n','m');
    static int solution(char L, char R, String target) {

        int minTime = 0;
        char left = L, right = R;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (vowels.contains(c)) {
                minTime += getTime(KEYBOARD_POSITIONS.get(right), KEYBOARD_POSITIONS.get(c));
                right = c;
            } else {
                minTime += getTime(KEYBOARD_POSITIONS.get(left), KEYBOARD_POSITIONS.get(c));
                left = c;
            }
            minTime++;
        }

        return minTime;
    }

    static int getTime(int[] cur, int[] destination) {
        return Math.abs(cur[0] - destination[0]) + Math.abs(cur[1] - destination[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char L = st.nextToken().charAt(0);
        char R = st.nextToken().charAt(0);
        String target = br.readLine();

        final char[][] KEYBOARD = {
                {'q','w','e','r','t','y','u','i','o','p'},
                {'a','s','d','f','g','h','j','k','l'},
                {'z','x','c','v','b','n','m'}
        };

        for (int i = 0; i < KEYBOARD.length; i++) {
            for (int j = 0; j < KEYBOARD[i].length; j++) {
                KEYBOARD_POSITIONS.put(KEYBOARD[i][j], new int[]{i, j});
            }
        }

        System.out.println(solution(L, R, target));

    }

}
