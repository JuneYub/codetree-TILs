import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        TreeMap<String, Integer> tree = new TreeMap<>();

        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            tree.put(str, tree.getOrDefault(str, 0) + 1);
        }

        Iterator<Map.Entry<String, Integer>> it = tree.entrySet().iterator();

        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            sb.append(entry.getKey() + " " + entry.getValue()).append("\n");
        }

        System.out.println(sb.toString());


    }
}