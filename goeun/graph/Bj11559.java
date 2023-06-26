package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bj11559 {

    static class Puyo {
        int r;
        int c;

        public Puyo(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static char[][] map = new char[12][6];
    static int[][] dir = {{-1, 0},{1, 0},{0, -1},{0, 1}}; // 상하좌우
    static boolean[][] visited;
    static Queue<Puyo> q;
    static List<Puyo> tracks;

    static int solution(){

        visited = new boolean[12][6];
        int answer = 0;

        while(true){

            // 현재 연쇄에서 터지는 뿌요들 확인
            boolean hasOverFour = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    char puyo = map[i][j];
                    q = new LinkedList<>();
                    tracks = new ArrayList<>();

                    // 방문한적 없는 뿌요 위치라면 상하좌우 탐색
                    if (puyo != '.' && !visited[i][j]) {
                        bfs(i, j, 1);
                    }

                    if (tracks.size() >= 4) {
                        hasOverFour = true;
                    } else {
                        for (Puyo p : tracks) {
                            visited[p.r][p.c] = false;
                        }
                    }
                }
            }

            // 터질 곳이 없는 경우 중단
            if (!hasOverFour) break;

            answer++;

            resetVisits();

            takePuyoDown();

        }

        return answer;
    }

    static void resetVisits(){
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visited[i][j]) {
                    map[i][j] = '.';
                    visited[i][j] = false;
                }
            }
        }
    }

    static void takePuyoDown(){
        for (int i = 11; i >=0; i--) {
            for (int j = 0; j < 6; j++) {
                // 현재 위치가 뿌요면
                if (map[i][j] != '.') {
                    int nR = i;
                    for (int k = i; k < 12; k++) {
                        if (map[k][j] == '.') nR = k;
                    }
                    swap(i, j, nR, j);
                }
            }
        }
    }

    static void swap(int r, int c, int nR, int nC) {
        char tmp = map[r][c];
        map[r][c] = map[nR][nC];
        map[nR][nC] = tmp;
    }

    static void bfs(int r, int c, int count) {

        // 처음 방문한 위치
        visited[r][c] = true;
        q.add(new Puyo(r, c));
        tracks.add(new Puyo(r, c));

        // 처음 방문 위치부터 상하좌우 이동
        while (!q.isEmpty()) {
            Puyo cur = q.poll();

            for (int[] d : dir) {
                int nR = cur.r + d[0];
                int nC = cur.c + d[1];
                if (nR < 0 || nC < 0 || nR >= 12 || nC >= 6) continue;
                if (map[cur.r][cur.c] == map[nR][nC] && !visited[nR][nC]) {
                    visited[nR][nC] = true;
                    q.add(new Puyo(nR, nC));
                    tracks.add(new Puyo(nR, nC));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        System.out.println(solution());

    }

}
