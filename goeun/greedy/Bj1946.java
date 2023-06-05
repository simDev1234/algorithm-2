package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1946 {

    static class Score{
        int cv; // 서류 점수
        int interview; // 면접 순위

        public Score(int cv, int interview) {
            this.cv = cv;
            this.interview = interview;
        }
    }

    // cv 1 2 3 4 5 6 7
    // iv 4 5 6 2 7 1 3
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 - 최대 20

        while (T --> 0) {

            int N = Integer.parseInt(br.readLine()); // 지원자의 숫자 - 최대 100000 --> O(N)

            PriorityQueue<Score> pq = new PriorityQueue<>(new Comparator<Score>() {
                @Override
                public int compare(Score x, Score y) {
                    return Integer.compare(x.cv, y.cv);
                }
            });

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cv = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                pq.add(new Score(cv, interview));
            }

            int answer = 1;
            int minIV = pq.poll().interview;
            while (!pq.isEmpty()) {
                Score cur = pq.poll();
                if (cur.interview < minIV) {
                    answer++;
                    minIV = cur.interview;
                }
            }

            System.out.println(answer);
        }


    }

}
