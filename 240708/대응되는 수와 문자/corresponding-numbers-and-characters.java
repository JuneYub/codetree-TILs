import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> map2 = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String key = br.readLine();
            
            map.put(key, i+1);
            map2.put(i+1, key);
        }


        for(int i = 0; i < m; i++) {
            String str = br.readLine();
            if(map.containsKey(str)) {
                System.out.println(map.get(str));
            } else {
                System.out.println(map2.get(Integer.parseInt(str)));
            }
        }


    }
}