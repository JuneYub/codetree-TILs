import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String key = br.readLine();
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() > maxValue) {
                maxValue = entry.getValue();
            }
        }

        System.out.println(maxValue);
        


    }
}