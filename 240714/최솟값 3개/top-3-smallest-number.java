import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);

            if(pq.size() < 3) {
                sb.append(-1 + "\n");
            } else {
                
                int a = pq.poll();
                int b = pq.poll();
                int c = pq.poll();
                sb.append((long)a*b*c + "\n");

                pq.add(a);
                pq.add(b);
                pq.add(c);
            }
        }

        System.out.print(sb);
    }
}