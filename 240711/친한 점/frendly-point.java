import java.util.*;
import java.io.*;

class Position implements Comparable<Position> {

    int x,y;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position o) {
        if(this.x != o.x) {
            return this.x - o.x;
        } else {
            return this.y - o.y;
        }
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TreeSet<Position> tree = new TreeSet<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            tree.add(new Position(x, y));

        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(tree.ceiling(new Position(x, y)) != null) {
                sb.append(tree.ceiling(new Position(x, y)).x + " " +  tree.ceiling(new Position(x, y)).y).append("\n");
            } else {
                sb.append(-1 + " " + -1).append("\n");
            }
        }

        System.out.println(sb.toString());

    }
}