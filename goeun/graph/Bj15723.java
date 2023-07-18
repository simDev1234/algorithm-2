package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj15723 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] D = new int[26][26];

        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" is ");
            int s = tmp[0].charAt(0) - 'a';
            int e = tmp[1].charAt(0) - 'a';
            D[s][e] = 1;
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (D[i][k] == 1 && D[k][j] == 1) {
                        D[i][j] = 1;
                    }
                }
            }
        }

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] tmp = br.readLine().split(" is ");
            int s = tmp[0].charAt(0) - 'a';
            int e = tmp[1].charAt(0) - 'a';
            if (D[s][e] == 1) sb.append("T");
            else sb.append("F");
            if (i != T - 1) sb.append("\n");
        }
        System.out.println(sb);

    }
}
