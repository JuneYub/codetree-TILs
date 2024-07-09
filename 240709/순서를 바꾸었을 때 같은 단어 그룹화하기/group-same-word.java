import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {

            String str = br.readLine();
            char tempArray[] = str.toCharArray();
            Arrays.sort(tempArray);
            str = new String(tempArray);

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        int result = 0;
        for(String key : map.keySet()) {
            result = Math.max(result, map.get(key));
        }
        System.out.println(result);



    }
}