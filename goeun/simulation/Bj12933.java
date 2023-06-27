package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// quqaquuacakcqckkuaquckqauckack
// 1. 문자열 리스트에 하나씩 문자를 넣는다.
// 2. 각 문자열 리스트를 돌며 현재 sound 문자를 넣을 자리를 찾는다.
//    list.get(i).length() % 5 == 0 이면 'q'를 넣는다.
//    없으면 새로 문자열을 추가한다.
//    현재 문자가 'q'가 아니면 맞는 위치를 찾는다.

public class Bj12933 {

    static int solution(String sound) {

        List<StringBuilder> docks = new ArrayList<>();

        for (int i = 0; i < sound.length(); i++) {
            char c = sound.charAt(i);

            // 문자열 리스트의 문자들에서 다음 자리에 넣을 수 있는 곳 확인
            int nextIdx = -1;
            for (int j = 0; j < docks.size(); j++) {
                nextIdx = getIndex(docks.get(j).toString(), c);
                if (nextIdx != -1) {
                    docks.get(j).append(c);
                    break;
                }
            }

            if (nextIdx == -1) {
                docks.add(new StringBuilder("q"));
            }
        }

        int answer = 0;
        for (StringBuilder sb : docks) {
            boolean misMatch = Arrays.stream(sb.toString().split("quack"))
                    .filter(x -> x.length() > 0).count() > 0;
            if (misMatch) return -1;
            else answer++;
        }

        return answer;
    }

    static int getIndex(String origin, char c) {
        String quack = "quack";
        int nextIdx = 0;
        for (int i = 0; i < 5; i++) {
            if (origin.charAt(origin.length() - 1) == quack.charAt(i)) {
                nextIdx = (i + 1) % 5;
                break;
            }
        }
        return quack.charAt(nextIdx) == c ? nextIdx : -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(br.readLine()));

    }

}
