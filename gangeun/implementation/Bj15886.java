import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj15886 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String s = br.readLine();
        int count = 0;

        int index = 0;
        while ((index = s.indexOf("EW", index)) != -1) {
            count++;
            index += 2;
        }
        System.out.println(count);

    }

}
