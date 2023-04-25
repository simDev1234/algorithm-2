import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1713 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Map<Integer, Integer> recommendMap = new HashMap<>();
        Map<Integer, Integer> timeMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (recommendMap.containsKey(num)) {
                recommendMap.put(num, recommendMap.get(num) + 1);
                continue;
            }

            if (recommendMap.size() >= N) {
                int deleteIndex = getRecommendedItem(recommendMap, timeMap);
                recommendMap.remove(deleteIndex);
                timeMap.remove(deleteIndex);
            }

            recommendMap.put(num, 0);
            timeMap.put(num, i);

        }

        List<Integer> list = new ArrayList<>(timeMap.keySet());
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i + " ");
        }

        System.out.println(sb);

    }

    public static int getRecommendedItem(Map<Integer, Integer> recommendMap, Map<Integer, Integer> timeMap) {
        int minRecommendValue = Integer.MAX_VALUE;
        int minTimeValue = Integer.MAX_VALUE;
        int recommendedItem = -1;

        for (Map.Entry<Integer, Integer> entry : recommendMap.entrySet()) {
            int item = entry.getKey();
            int recommendValue = entry.getValue();
            int timeValue = timeMap.get(item);

            if (recommendValue < minRecommendValue) {
                minRecommendValue = recommendValue;
                minTimeValue = timeValue;
                recommendedItem = item;
            } else if (recommendValue == minRecommendValue && timeValue < minTimeValue) {
                minTimeValue = timeValue;
                recommendedItem = item;
            }
        }

        return recommendedItem;
    }


}
