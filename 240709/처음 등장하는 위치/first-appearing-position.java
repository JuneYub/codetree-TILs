import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static TreeMap<Integer, Integer> map = new TreeMap<>();
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n ; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(!map.containsKey(num)) {
                map.put(num, i+1);
            } 

        }
        StringBuilder sb = new StringBuilder();

        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            sb.append(entry.getKey() + " " + entry.getValue()).append("\n");
        } 
        System.out.println(sb.toString());


    }
}