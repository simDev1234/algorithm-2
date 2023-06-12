package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj9663 {

    static int N;
    static int[] rowQueenColPos;
    static long answer = 0;

    // 각각의 행을 밑으로 내려가면서,
    // 다음 행에서 갈 수 있는 열을 먼저 찍고,
    // 그 열이 이미 갔던 위에 행들에 놓인 퀸의 방향과 맞는지를 확인
    static long solution(int r) {

        // 마지막 행이 오게 되면 answer를 return
        if (r == N) {
            answer++;
            return answer;
        }

        // 다음 행에서 갈 수 있는 위치를 찾는다.
        for (int i = 0; i < N; i++) {

            // 각 열을 돌면서 체크
            rowQueenColPos[r] = i;

            if (isPromising(r, i)) {
                solution(r + 1);
            }
        }

        return answer;
    }

    // 갈 수 있는 열의 위치가 맞는지를 확인
    static boolean isPromising(int r, int c) {
        // 이전의 행들에서의 퀸 위치를 찾아
        for (int i = 0; i < r; i++) {
            // 현재 위치와 걸리지는지 확인
            // 1) 퀸 위치의 아래쪽 (칼럼)
            // 2) 퀸 위치의 우측 (해당 안됨)
            // 3) 퀸 위치의 우측 대각선
            // 4) 퀸 위치의 좌측 대각선
            if (rowQueenColPos[i] == c || r - i == Math.abs(c - rowQueenColPos[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        rowQueenColPos = new int[N];

        System.out.println(solution(0));

    }

}
