import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        TreeSet<Integer> tree = new TreeSet<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= m; i++) {
            tree.add(i);
        }

        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(tree.floor(num) != null) {
                
                tree.remove(tree.floor(num));
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}