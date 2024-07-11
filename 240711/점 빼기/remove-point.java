import java.util.*;
import java.io.*;

class Position implements Comparable<Position> {
    int x, y;
    
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position p) {
        if(this.x == p.x) {
            return this.y - p.y;
        }
        return this.x - p.x;
    }
}

public class Main {
    
    public static TreeSet<Position> s = new TreeSet<>();

    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            s.add(new Position(x, y));
        }

        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            Position k = new Position(num, 0);
            
            if(s.ceiling(k) != null) {
                Position p = s.ceiling(k);
                s.remove(p);
                sb.append(p.x + " " + p.y).append("\n");
            } else {
                sb.append(-1 + " " + -1).append("\n");
            }
        }

        System.out.println(sb.toString());

    }
}