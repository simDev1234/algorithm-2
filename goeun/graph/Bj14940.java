package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj14940 {

    static int[] dx= {-1,1,0,0};
    static int[] dy= {0,0,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] pan = new int[n][m];
        int[][] ans = new int[n][m];

        int[] start = new int[2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                pan[i][j] = Integer.parseInt(st.nextToken());
                if(pan[i][j]==2) {
                    start = new int[]{i, j};
                } else if(pan[i][j]==0) {
                    ans[i][j]=0;
                } else {
                    ans[i][j]=-1;
                }
            }
        }

        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1], 0});
        while(q.size()>0) {
            int [] cur = q.poll();
            int x = cur[0], y = cur[1], v=cur[2];

            for(int i=0;i<4;i++) {
                if(x+dx[i]<0 || x+dx[i]>=n || y+dy[i]<0 || y+dy[i]>=m)
                    continue;
                if(pan[x+dx[i]][y+dy[i]]==1 && ans[x+dx[i]][y+dy[i]]==-1) {
                    q.add(new int[] {x+dx[i], y+dy[i], v+1});
                    ans[x+dx[i]][y+dy[i]]=v+1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

}
