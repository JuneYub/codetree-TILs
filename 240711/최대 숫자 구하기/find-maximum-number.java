import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Integer> tree = new TreeSet<>();

        for(int i = 1; i <= m; i++) {
            tree.add(i);
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tree.remove(Integer.parseInt(st.nextToken()));
            sb.append(tree.last()).append("\n");
        }

        System.out.println(sb.toString());


    }
}