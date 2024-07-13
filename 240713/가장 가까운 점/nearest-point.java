import java.io.*;
import java.util.*;


class Position implements Comparable<Position> {
    int x, y;
    
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distance() {

        return Math.abs(this.x) + Math.abs(this.y);

    }

    public int compareTo(Position p) {
        if(this.distance() != p.distance()) {
            return this.distance() - p.distance();
        }
        else {
            if(this.x != p.x) {
                return this.x - p.x;
            } else {
                return this.y - p.y;
            }
        }
    }

}
public class Main {
    public static int n, m;
    public static PriorityQueue<Position> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Position(x, y));
        }

        for(int i = 0; i < m; i++) {
            
            Position p = pq.poll();
            p.x += 2;
            p.y += 2;
            pq.add(p);
        }
        System.out.print(pq.peek().x + " " + pq.peek().y);
    }
}