import java.io.*;
import java.util.*;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Tuple {
    int x1, y1, x2, y2;

    public Tuple(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

// x와 y점을 좌표압축할 경우 n개의 페어가 주어지니깐 최대 2n개의 결과로 이어진다.
public class Main {
    public static final int MAX_N = 2_500;
    public static final int MAX_Q = 300_000;
//    public static final int m = 5_002;
    public static final int m = 9;
    public static int n, q;

//    public static int[][] prefixSum = new int[m+2][m+2];
//    public static Pair[] points = new Pair[MAX_N];
//    public static Tuple[] queries = new Tuple[MAX_Q];

    public static int[][] prefixSum;
    public static Pair[] points;
    public static Tuple[] queries;

    public static TreeSet<Integer> tree = new TreeSet<>();
    public static HashMap<Integer, Integer> mapper = new HashMap<>();

    // 트리셋에서 ceiling으로 lowerBound 가져오기
    public static int getLowerBound(int x) {
        Integer ceiling = tree.ceiling(x);
        if(ceiling != null) {
            return mapper.get(ceiling);
        } else {
            return tree.size() + 1; // x보다 크거나 같은 숫자가 없다? 그러면 현재 그러면 걍 점이 찍힌 모든 범위보다 큰 숫자로 리턴
        }
    }

    // 트리셋에서 floor로 upperBound 가져오기
    public static int getUpperBound(int x) {
        Integer floor = tree.floor(x);
        if(floor != null) {
            return mapper.get(floor);
        } else {
            return 0;
        }
    }

    // getSum 메서드는 누적합 배열에서 점의 위치를 가져온다.
    public static int getSum(int x1, int y1, int x2, int y2) {
        return prefixSum[x2][y2] - prefixSum[x1-1][y2] -
                prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        prefixSum = new int[10][10];
        points = new Pair[n];
        queries = new Tuple[q];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i] = new Pair(x, y);
            // 핵심은 트리셋에 일단 다 넣는것이다.
            tree.add(x);
            tree.add(y);
        }

        // 매퍼에 정렬된 숫자를 넣는다.
        int cnt = 1;
        for(int num : tree) {
            mapper.put(num, cnt++);
        }

        // 찍어놓은 점을 누적합 배열에 표시한다.
        for(int i = 0; i < n; i++) {
            int x = points[i].x;
            int y = points[i].y;

            int newX = mapper.get(x);
            int newY = mapper.get(y);

            prefixSum[newX][newY]++;
        }

        // 누적합 진행
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= m; j++) {
                prefixSum[i][j] += prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        // 질의 넣는다.
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            // 좌하단
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            // 우상단
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            queries[i] = new Tuple(x1, y1, x2, y2);

            // 트리셋에 정렬된 것중에서 작은 것으로 가져온다.
            int newX1 = getLowerBound(x1);
            int newY1 = getLowerBound(y1);

            // 트리셋에 정렬된 것중에서 큰 것으로 가져온다.
            int newX2 = getUpperBound(x2);
            int newY2 = getUpperBound(y2);

            int ans = getSum(newX1, newY1, newX2, newY2);
            sb.append(ans + "\n");
        }

        System.out.print(sb);




    }
}