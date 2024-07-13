import java.io.*;
import java.util.*;

public class Main {
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                pq.add(x);
            } else {
                if(pq.isEmpty()) {
                    sb.append(0 + "\n");
                } else {
                    sb.append(pq.poll() + "\n");
                }
                
            }
        }

        System.out.print(sb);
    }
}