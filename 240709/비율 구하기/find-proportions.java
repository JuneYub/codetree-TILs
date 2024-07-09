import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static TreeMap<String, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++) {
            String key = br.readLine();
            treeMap.put(key, treeMap.getOrDefault(key, 0) + 1 );
        }

        Iterator<Map.Entry<String, Integer>> it = treeMap.entrySet().iterator();

        StringBuilder sb = new StringBuilder();

        while(it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            float output = (entry.getValue() / (float)n) * 100;
            String formattedPercentage = String.format("%.4f", output);
            sb.append(entry.getKey() + " " + formattedPercentage).append("\n");    
        }

        System.out.print(sb.toString());

    }
}