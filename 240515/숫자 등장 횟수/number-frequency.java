import java.util.*;
import java.io.*;

public class Main {

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1 );
            } else {
                map.put(num, 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)) {
                System.out.print(map.get(num) + " ");
            } else {
                System.out.print(0 + " ");
            }
        }

        
    }
}