package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj2469 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        String finalResult = br.readLine();
        int questionRow = -1;

        char[][] ladder = new char[N][K - 1];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            if (input.charAt(0) == '?') questionRow = i;
            for (int j = 0; j < K - 1; j++) {
                ladder[i][j] = input.charAt(j);
            }
        }

        char[] upperPart = new char[K];
        for (int i = 0; i < K; i++) {
            upperPart[i] = (char)('A' + i);
        }

        for (int i = 0; i < questionRow; i++) {
            for (int j = 0; j < K - 1; j++) {
                if (ladder[i][j] == '-') {
                    char tmp = upperPart[j];
                    upperPart[j] = upperPart[j + 1];
                    upperPart[j + 1] = tmp;
                }
            }
        }

        char[] downPart = new char[K];
        for (int i = 0; i < K; i++) {
            downPart[i] = finalResult.charAt(i);
        }

        for (int i = N - 1; i > questionRow; i--) {
            for (int j = 0; j < K - 1; j++) {
                if (ladder[i][j] == '-') {
                    char tmp = downPart[j];
                    downPart[j] = downPart[j + 1];
                    downPart[j + 1] = tmp;
                }
            }
        }

        char[] answer = new char[K];
        boolean isPrintable = true;
        for (int i = 0; i < K - 1; i++) {
            if (upperPart[i] == downPart[i]) {
                answer[i] = '*';
            } else if (upperPart[i] == downPart[i + 1] && upperPart[i + 1] == downPart[i]) {
                answer[i] = '-';
                char tmp = upperPart[i];
                upperPart[i] = upperPart[i + 1];
                upperPart[i + 1] = tmp;
            } else {
                isPrintable = false;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (isPrintable) {
            for (int i = 0; i < K - 1; i++) {
                sb.append(answer[i]);
            }
        } else {
            for (int i = 0; i < K - 1; i++) {
                sb.append("x");
            }
        }
        System.out.println(sb);

    }

}
