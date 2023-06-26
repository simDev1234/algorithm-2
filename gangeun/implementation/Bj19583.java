import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj19583 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer S = timeToNumber(st.nextToken()); // 개강총회 시작
        Integer E = timeToNumber(st.nextToken()); // 개강총회 끝
        Integer Q = timeToNumber(st.nextToken()); // 스트리밍 종료

        Set<String> students = new HashSet<>();

        int count = 0;
        String line;

        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) break;

            Integer time = timeToNumber(st.nextToken());
            String id = st.nextToken();

            if (time <= S) {
                students.add(id);
            }

            if (time > S && time >= E && time <= Q) {
                boolean remove = students.remove(id);
                if(remove) count++;
            }
        }

        System.out.println(count);


    }

    private static Integer timeToNumber(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int HH = Integer.parseInt(st.nextToken()) * 60;
        int MM = Integer.parseInt(st.nextToken());
        return HH + MM;
    }
}
