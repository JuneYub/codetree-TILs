import java.io.*;
import java.util.*;

public class Main {

    static int n;
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken()); 

            if(map.containsKey(x)) {
                int old = map.get(x);
                map.put(x, Math.min(old, y));
            } else {
                map.put(x, y);
            }
        }

        long result = 0;
        for (int key : map.keySet()) {
            result += map.get(key);
        }
        System.out.println(result);
    }
}