import java.util.*;
import java.io.*;

public class Main {

    public static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            set.add(num);
        }
        
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());

            if(set.contains(num)) {
                sb.append(1 + " ");
            } else {
                sb.append(0 + " ");
            }
        }
        System.out.println(sb.toString());


        

        
    }
}