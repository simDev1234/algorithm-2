import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bj11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<String> list = new ArrayList<>();

        for (int i = s.length() -1 ; i >= 0 ; i--) {
            list.add(s.substring(i));
        }

        list.stream().sorted().forEach(System.out::println);
    }
}
