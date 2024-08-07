import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position p) {
        return this.x - p.x;
    }
}

class TargetPosition implements Comparable<TargetPosition> {
    int x, y;

    TargetPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(TargetPosition p) {
        if(this.y == p.y) {
            return this.x - p.x;
        }
        return this.y - p.y;
    }
    
}


public class Main {

    public static TreeSet<TargetPosition> tree = new TreeSet<>();
    public static Position[] positions;

    static int n;
    static int d;

    public static int getMax() {
        if(tree.isEmpty()) return 0;
        return tree.last().y;
    }

    public static int getMin() {
        if(tree.isEmpty()) return 0;
        return tree.first().y; 
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        positions = new Position[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            positions[i] = new Position(x, y);
        }
        
        int ans = Integer.MAX_VALUE;
        Arrays.sort(positions, 1, n+1);
        int j = 0;
        for(int i = 1; i <= n; i++) {

            while(j+1 <= n && getMax() - getMin() < d) {
                j++;
                tree.add(new TargetPosition(positions[j].x, positions[j].y));
            }

            if(getMax() - getMin() < d) {
                break;
            }

            ans = Math.min(ans, positions[j].x - positions[i].x);
            
            tree.remove(new TargetPosition(positions[i].x, positions[i].y));

        }
        if(ans == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(ans);
        }
        
    }
}