package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1759 {

    // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
    // 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것
    static String[] out;
    static String[] kinds;
    static boolean[] visited;
    static List<String> vowels = Arrays.asList("a", "e", "i", "o", "u");
    static Set<String> answers = new TreeSet<>();
    static int L;
    static int C;

    static void solution() {

        for (int i = 0; i <= C - L; i++) {
            visited[i] = true;
            out[0] = kinds[i];
            dfs(i, 1);
            visited[i] = false;
        }

        for (String s : answers) {
            System.out.println(s);
        }

    }

    static void dfs(int cur, int cnt){

        if (cnt == L) {
            addToAnswerIfHasVowel(L);
            return;
        }

        for (int i = 0; i < C; i++) {
            if (visited[i] == false && cur < i){
                visited[i] = true;
                out[cnt] = kinds[i];
                dfs(i, cnt + 1);
                visited[i] = false;
            }
        }

    }

    private static void addToAnswerIfHasVowel(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(out[i]);
        }
        String tmp = sb.toString();
        int vowelCnt = getVowelCnt(tmp);
        if (vowelCnt >= 1 && L - vowelCnt >= 2) {
            answers.add(tmp);
        }
    }

    static int getVowelCnt(String s) {
        return (int)vowels.stream().filter(x -> s.contains(x)).count();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        out = new String[L];
        kinds = new String[C];
        visited = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            kinds[i] = st.nextToken();
        }

        Arrays.sort(kinds);

        solution();

    }

}
