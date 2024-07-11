import java.util.*;
import java.io.*;

public class Main {

    public static final int MAX_N = 100000;

    public static int n, m;
    public static int[] arr = new int[MAX_N];

    public static TreeSet<Integer> tree = new TreeSet<>();

    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < n; i++) {
            tree.add(arr[i]);
        }

        int ans = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++) {
            int x = arr[i];

            if(tree.ceiling(m + x)  != null) {
                ans = Math.min(ans, tree.ceiling(m + x) - x);
            }

            if(tree.floor(x - m) != null) {
                ans = Math.min(ans, x - tree.floor(x- m));
            }

            if(ans == Integer.MAX_VALUE) {
                ans = -1;
            }
        }

        System.out.print(ans);




    }
}