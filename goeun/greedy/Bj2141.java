package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Bj2141 {

    // 각 사람들의 거리의 합이 최소가 되기 위해서는,
    // 우체국 위치가 총 인구의 중간값과 가장 가까운 마을에 설치되어야 한다.
    static class Town implements Comparable<Town>{
        long position;
        long personnel;

        public Town(long position, long personnel) {
            this.position = position;
            this.personnel = personnel;
        }

        @Override
        public int compareTo(Town o) {
            return Long.compare(this.position, o.position);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Town> towns = new ArrayList<>();
        long totalPersonnel = 0, midPersonnel = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long position = Long.parseLong(st.nextToken());
            long personnel = Long.parseLong(st.nextToken());
            towns.add(new Town(position, personnel));
            totalPersonnel += personnel;
        }
        midPersonnel = (totalPersonnel + 1)/2;

        Collections.sort(towns);

        long curTotalPersonnel = 0;
        for (int i = 0; i < N; i++) {
            curTotalPersonnel += towns.get(i).personnel;
            if (curTotalPersonnel >= midPersonnel) {
                System.out.println(towns.get(i).position);
                break;
            }
        }

    }

}
