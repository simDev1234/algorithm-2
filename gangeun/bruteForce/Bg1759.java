import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bg1759 {

    private static int C;
    private static int L;
    private static String[] arr;
    private static Set<String> set = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new String[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }

        Arrays.sort(arr);

        boolean[] visited = new boolean[C + 1];
        for (int i = 0; i < C-L+1; i++) {
            visited[i] = true;
            dfs(0, arr[i], visited, i);
            visited[i] = false;
        }


        List<String> list = new ArrayList<>(set);
        Collections.sort(list);
        list.forEach(System.out::println);
    }

    private static void dfs(int depth, String word, boolean[] visited, int order) {

        if (depth == L - 1 && containsVowelAndMultipleConsonants(word)) {
            set.add(word);
        }

        for (int i = order+1; i < C; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            dfs(depth + 1, word + arr[i], visited, i);
            visited[i] = false;
        }
    }

    static boolean containsVowelAndMultipleConsonants(String str) {
        int vowelCount = 0, consonantCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        return vowelCount >= 1 && consonantCount >= 2;
    }

}
