import java.io.*;
import java.util.*;

class Value implements Comparable<Value> {
    int absValue;
    int orgValue;

    Value(int orgValue, int absValue) {
        this.orgValue = orgValue;
        this.absValue = absValue;
    }

    public int compareTo(Value v) {
        if(this.absValue != v.absValue) {
            return this.absValue - v.absValue;
        } else {
            return this.orgValue - v.orgValue;
        }
    }

}

public class Main {
    public static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Value> pq = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x != 0) {
                pq.add(new Value(x, Math.abs(x)));
            } else {
                if(pq.isEmpty()) {
                    sb.append(0 + "\n");
                } else {
                    sb.append(pq.poll().orgValue + "\n");
                }
                
            }
        }

        System.out.print(sb);
    }
}