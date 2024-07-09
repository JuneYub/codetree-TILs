import java.util.*;
import java.io.*;

public class Main {

    public static int n, m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++ ) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println(set.size());
    }
}