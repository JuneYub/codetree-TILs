import java.util.*;
import java.io.*;

public class Main {

    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        HashSet<Integer> set = new HashSet<>();
        
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            if("find".equals(oper)) {
                sb.append(set.contains(n)).append("\n");
            }
            else if("add".equals(oper)) {
                set.add(n);
            } else if("remove".equals(oper)) {
                set.remove(n);
            }

        }
        System.out.println(sb.toString());
    }
}