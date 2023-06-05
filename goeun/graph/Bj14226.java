package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Bj14226 {

    // 연산 세가지 - 1) 화면 -> 클립보드 all 저장 (덮어쓰기) 2) 클립보드 -> 화면 all 저장 3) 화면의 이모티콘 하나 삭제
    // 모든 연산은 1초 소요
    // * 클립보드가 비어 있으면 2) 연산 불가. / 2) 연산을 하면 이모티콘 갯수가 화면에 추가됨
    static class Emoji{
        int clipCnt;
        int displayCnt;
        int opCnt;

        public Emoji(int clipCnt, int displayCnt, int opCnt) {
            this.clipCnt = clipCnt;
            this.displayCnt = displayCnt;
            this.opCnt = opCnt;
        }
    }

    static int bfs(int S) {

        Queue<Emoji> q = new LinkedList<>();
        q.add(new Emoji(0, 1, 0));
        boolean[][] visited = new boolean[2001][2001];

        while (!q.isEmpty()) {

            Emoji cur = q.poll();

            if (cur.displayCnt == S) return cur.opCnt;

            for (int i = 0; i < 3; i++) {

                int nextDisplayCnt = cur.displayCnt;
                int nextClipCnt = cur.clipCnt;

                if (i == 0) {
                    // 화면 -> 클립보드
                    nextClipCnt = cur.displayCnt;
                } else if (i == 1 && cur.clipCnt != 0) {
                    // 클립보드 -> 화면
                    nextDisplayCnt = cur.displayCnt + cur.clipCnt;
                } else if (i == 2){
                    // 화면에서 1개 삭제
                    nextDisplayCnt = cur.displayCnt - 1;
                }

                if (nextDisplayCnt < 0 || nextDisplayCnt > 2000) continue;
                if (nextClipCnt < 0 || nextClipCnt > 2000) continue;;

                if (!visited[nextDisplayCnt][nextClipCnt]) {
                    visited[nextDisplayCnt][nextClipCnt] = true;
                    q.add(new Emoji(nextClipCnt, nextDisplayCnt, cur.opCnt + 1));
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        System.out.println(bfs(S));

    }

}
