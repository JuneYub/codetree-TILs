import java.util.*;
import java.io.*;

public class Main {
    public static int n, m;
    public static TreeSet<Integer> tree = new TreeSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            tree.add(num);
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(tree.floor(num) != null) {
                sb.append(tree.floor(num) + "\n");
                tree.remove(tree.floor(num));
            }
            else {
                sb.append(-1 + "\n");
            }
        }

        System.out.println(sb);
    }
}