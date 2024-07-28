import java.io.*;
import java.util.*;


class Position implements Comparable<Position>{
    int x, v, idx;

    public Position(int x, int v, int idx) {
        this.x = x;
        this.v = v;
        this.idx = idx;
    }

    public int compareTo(Position p) {
        return this.x - p.x;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Position> positions = new ArrayList<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            positions.add(new Position(x1, 1, i));
            positions.add(new Position(x2, -1, i));

            treeSet.add(i+1);
        }

        Collections.sort(positions);
        

        int startNum = 0;
        int[] result = new int[n];

        for(int i = 0; i < positions.size(); i++) {
            Position p = positions.get(i);

            if(p.v == 1) {
                result[p.idx] = treeSet.first();
                treeSet.remove(result[p.idx]);
                
            } else {
                treeSet.add(result[p.idx]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(result[i] + " ");
        }
        System.out.print(sb);
    }
}