import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
    int x, v, idx;

    public Position (int x, int v, int idx) {
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

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            positions.add(new Position(a, 1, i));
            positions.add(new Position(b, -1, i));
        }

        Set<Integer> set = new HashSet<>();
        int startNum = 0;
        int result = 0;
        Collections.sort(positions);

        for(int i = 0; i < positions.size(); i++) {
            Position p = positions.get(i);

            if(p.v == 1) {
                if(set.size() == 0) {
                    startNum = p.x;
                    //System.out.println(startNum);
                }

                set.add(p.idx);
            } else {
                set.remove(p.idx);

                if(set.size() == 0) {
                    result += (p.x - startNum);
                }
            }
        }  

        System.out.print(result);


    }
}