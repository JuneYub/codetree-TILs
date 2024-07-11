import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        TreeSet<Integer> tree = new TreeSet<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tree.add(Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < k; i++) {
            sb.append(tree.last()+ " ");
            tree.remove(tree.last());
        }

        System.out.println(sb.toString());

    }
}