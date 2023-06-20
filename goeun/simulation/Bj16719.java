package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj16719 {

    static boolean[] visited;

    static void divideAndConquer(char[] words, int left, int right, int deleteCnt) {

        if (left > right) return;
        if (deleteCnt == words.length) return;

        // 가장 작은 문자를 찾는다.
        int minIdx = left;
        for (int i = left + 1; i <= right; i++) {
            if (words[minIdx] > words[i]){
                 minIdx = i;
            }
        }

        // 가장 작은 문자를 뽑고 출력한다.
        visited[minIdx] = true;
        print(words);

        // 양 옆을 분할 한다.
        divideAndConquer(words, minIdx + 1, right, deleteCnt + 1);
        divideAndConquer(words, left, minIdx - 1, deleteCnt + 1);

    }

    static void print(char[] words){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                sb.append(words[i]);
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        visited = new boolean[s.length()];

        divideAndConquer(s.toCharArray(), 0, s.length() - 1, 0);

    }

}
