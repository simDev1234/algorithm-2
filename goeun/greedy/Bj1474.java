package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj1474 {

    static void solution (int N, int M, String[] words, int wordsLen) {

        int averageLen = (M - wordsLen) / (N - 1);
        int leftLen = (M - wordsLen) % (N - 1);
        String averageBar = averageBar(averageLen);

        for (int i = 0; i < N - 1; i++) {
            if (leftLen > 0) {
                // 다음 단어가 만약 a ~ z 까지의 알파벳이거나, 남은 수가 있는 경우
                if ((words[i + 1].charAt(0) >= 'a' && words[i + 1].charAt(0) <= 'z') || i >= N - 1 - leftLen) {
                    words[i] += "_";
                    leftLen--;
                }
            }
            words[i] += averageBar;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(words[i]);
        }
        System.out.println(sb);
    }

    private static String averageBar(int averageLen) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < averageLen; i++) {
            sb.append("_");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] words = new String[N];
        int wordsLen = 0;
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            wordsLen += words[i].length();
        }
        solution(N, M, words, wordsLen);

    }

}
