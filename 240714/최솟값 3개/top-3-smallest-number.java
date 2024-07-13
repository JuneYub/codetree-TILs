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
                
                long a = pq.poll();
                long b = pq.poll();
                long c = pq.poll();
                long result = a*b*c;
                sb.append(result + "\n");

                pq.add((int)a);
                pq.add((int)b);
                pq.add((int)c);
            }
        }

        System.out.print(sb);
    }
}