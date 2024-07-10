import java.io.*;
import java.util.*;


public class Main {

    public static int n, m;

    public static HashSet<Integer> A = new HashSet<>();
    public static HashSet<Integer> A2 = new HashSet<>();
    public static HashSet<Integer> B = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            A.add(num);
            A2.add(num);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        A.removeAll(B);
        B.removeAll(A2);

        int aSize = A.size();
        int bSize = B.size();

        System.out.println(Math.abs(aSize) + Math.abs(bSize));


    }
}