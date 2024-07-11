import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Integer> tree = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tree.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            if(tree.ceiling(num) == null) {
                sb.append(-1).append("\n");
            } else {
                sb.append(tree.ceiling(num)).append("\n");
            }

        }
        System.out.println(sb.toString());
    }
}