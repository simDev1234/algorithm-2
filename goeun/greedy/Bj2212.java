package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj2212 {

    static int solution(int N, int K, int[] positions) {

        // 센서의 개수가 집중국의 수보다 작으면 모든 것에 다 집중국 세우기
        if (K >= N) return 0;

        int answer = 0;

        Arrays.sort(positions);

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            distances.add(positions[i + 1] - positions[i]);
        }

        Collections.sort(distances, Collections.reverseOrder());

        for (int i = K - 1; i < N - 1; i++) {
            answer += distances.get(i);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 센서의 갯수
        int K = Integer.parseInt(br.readLine()); // 집중국의 갯수

        int[] positions = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, K, positions));

    }

}
