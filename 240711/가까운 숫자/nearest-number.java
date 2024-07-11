import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(0);
        StringBuilder sb = new StringBuilder();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            tree.add(num);


            if(tree.lower(num) != null && tree.higher(num) != null) {

                int left = tree.lower(num);
                int right = tree.higher(num);

                min = Math.min(Math.min(num - left, min), right - num);
            } else if(tree.lower(num) == null) {
                
                int right = tree.higher(num);
                min = Math.min(min, right-num);
                
            } else if(tree.higher(num) == null) {

                int left = tree.lower(num);
                min = Math.min(min, num - left);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());

    }
}