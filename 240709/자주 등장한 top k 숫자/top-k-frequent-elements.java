import java.io.*;
import java.util.*;

class Num implements Comparable<Num> {
    int key, value;

    Num(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int compareTo(Num obj) {
        if(this.value == obj.value) {
            return obj.key- this.key;
        } else {
            return obj.value - this.value;
        }
    }

}
public class Main {

    public static final int MAX_N = 1000;

    // 변수 선언
    public static int n,k;
    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        PriorityQueue<Num> pq = new PriorityQueue<>();
        for( Map.Entry<Integer, Integer> entry : map.entrySet() ) {
            pq.add(new Num(entry.getKey(), entry.getValue()));
        }

        for(int i = 0; i < k; i++) {
            System.out.print(pq.poll().key + " ");
        }

    }
}