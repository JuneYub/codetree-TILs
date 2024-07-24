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

public class Main {

    public static final int MAX_M = 5_000;
    public static final int MAX_Q = 300_000;
    public static final int MAX_N = 2_500;

    public static int n, q;
    public static Pair[] points = new Pair[MAX_N];
    public static Tuple[] queries = new Tuple[MAX_Q];

    public static TreeSet<Integer> nums = new TreeSet<>();
    public static HashMap<Integer, Integer> mapper = new HashMap<>();

    public static int[][] prefixSum = new int[MAX_M + 2][MAX_M + 2];

    // X보다 같거나 큰 최초의 숫자를 구해
    // 이를 좌표압축 했을 때의 결과를 반환
    public static int getLowerBoundary(int x) {
        if(nums.ceiling(x) != null) {
            return mapper.get(nums.ceiling(x));
        }
        // 트리셋 ceiling이 널인 경우
        return (int) nums.size() + 1;
    }

    // x보다 같거나 작은 최초의 숫자를 구해
    // 이를 좌표압축 했을 때의 결과를 반환합니다.
    public static int getUpperBoundary(int x) {
        if(nums.floor(x) != null) {
            return mapper.get(nums.floor(x));
        }
        return 0;
    }

    // (x1, y1), (x2, y2) 직사각형 구간 내의 점의 개수를 반환합니다.
    public static int getSum(int x1, int y1, int x2, int y2) {
        
        // 
        return prefixSum[x2][y2] - prefixSum[x1 - 1][y2] -
                prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 -1];

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Pair(x, y);

            nums.add(x);
            nums.add(y);
        }

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            queries[i] = new Tuple(x1, y1, x2, y2);
        }

        // treeset에서 값이 작은 것부터 보면서
        // 1번부터 순서대로 매칭하여
        // 그 결과를 hashmap에 넣어줍니다.
        int cnt = 1;
        for(Integer num : nums) {
            mapper.put(num, cnt);
            cnt++;
        }

        // 주어진 점들에 대해
        // 누적합 배열을 완성합니다.
        for(int i = 0; i < n; i++) {
            int x = points[i].x;
            int y = points[i].y;

            // 좌표 압축을 진행합니다.
            int newX = mapper.get(x);
            int newY = mapper.get(y);

            prefixSum[newX][newY]++;
        }

        for(int i = 1; i <= cnt; i++) {
            for(int j = 1; j <= cnt; j++) {
                prefixSum[i][j] += prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        // 각 질의에 대해
        // 구간 내 점의 개수를 구합니다.
        // 각 질의에 대해
        // 구간 내 점의 개수를 구합니다.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            int x1 = queries[i].x1;
            int y1 = queries[i].y1;
            int x2 = queries[i].x2;
            int y2 = queries[i].y2;
            
            // x1, y1의 경우 같거나 큰 최초의 위치를 lowerBound로,
            // x2, y2의 경우 같거나 작은 최초의 위치를 upperBound - 1로 구해줍니다.

            int newX1 = getLowerBoundary(x1);
            int newY1 = getLowerBoundary(y1);
            int newX2 = getUpperBoundary(x2);
            int newY2 = getUpperBoundary(y2);

            // 구간 내 점의 개수를 
            // 누적합을 이용하여 계산합니다.
            int ans = getSum(newX1, newY1, newX2, newY2);
            sb.append(ans+"\n");
        }
        System.out.println(sb);

    }
}