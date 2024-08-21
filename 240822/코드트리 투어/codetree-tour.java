import java.util.*;
import java.io.*;

class Product implements Comparable<Product> {
    
    int id;
    int revenue;
    int dest;
    int cost;
    int profit;

    public Product(int id, int revenue, int dest, int cost) {
        this.id = id;
        this.revenue = revenue;
        this.dest = dest;
        this.cost = cost;
        this.profit = revenue - cost;
    }
    
    public int compareTo(Product p) {
        if(this.profit == p.profit) {
            return this.id - p.id;
        }
        return Integer.compare(p.profit, this.profit);
    }


}

public class Main {



    final static int INF = Integer.MAX_VALUE;
    final static int MAX_N = 2000;
    final static int MAX_ID = 30005;

    static int n, m;
    static int[][] map;
    static int[] D = new int[MAX_N];

    static int S = 0;
    static int q;

    static boolean[] isMade = new boolean[MAX_ID];
    static boolean[] isCancel = new boolean[MAX_ID];
    

    static PriorityQueue<Product> pq = new PriorityQueue<>();

    public static void changeStart(int s) {
        S = s;
        dijkstra();

        List<Product> products = new ArrayList<>();
        while(!pq.isEmpty()) {
            products.add(pq.poll());
        }

        for(Product p : products) {
            addProduct(p.id, p.revenue, p.dest);
        }
    }

    public static int sellPackage() {
        while(!pq.isEmpty()) {
            Product p = pq.peek();
            if(p.profit < 0) break;
            
            pq.poll();

            if(!isCancel[p.id]) return p.id;
        }

        return -1;
    }

    public static void addProduct(int id, int revenue, int dest) {
        isMade[id] = true;
        int cost = D[dest];
        pq.add(new Product(id, revenue, dest, cost));
    }

    public static void cancelProduct(int id) {
        isCancel[id] = true;
    }

    public static void dijkstra() {
        boolean[] visited = new boolean[MAX_N];
        Arrays.fill(D, INF);

        D[S] = 0;

        for(int i = 1; i < n; i++) {
            int v = 0;
            int minDist = INF;
            for(int j = 0; j < n; j++) {
                if(!visited[j] && minDist > D[j]) {
                    v = j;
                    minDist = D[j];
                }
            }
            visited[v] = true;
            for(int j = 0; j < n; j++) {
                if(!visited[j] && D[v] != INF && map[v][j] != INF && D[j] > D[v] + map[v][j] ) {
                    D[j] = D[v] + map[v][j];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < q; i++) {
            
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            switch(command) {
                case 100:
                    n = Integer.parseInt(st.nextToken());
                    m = Integer.parseInt(st.nextToken());
                    
                    map = new int[MAX_N][MAX_N];
                    
                    for(int j = 0; j < MAX_N; j++) {
                        Arrays.fill(map[j], INF);
                        map[j][j] = 0;
                    }

                    for(int j = 0; j < m; j++) {
                        int v = Integer.parseInt(st.nextToken());
                        int u = Integer.parseInt(st.nextToken());
                        int w = Integer.parseInt(st.nextToken());

                        map[v][u] = Math.min(map[v][u], w);
                        map[u][v] = Math.min(map[u][v], w);
                    }

                    dijkstra();

                    break;
                
                case 200:

                    int id = Integer.parseInt(st.nextToken());
                    int revenue = Integer.parseInt(st.nextToken());
                    int dest = Integer.parseInt(st.nextToken());

                    addProduct(id, revenue, dest);

                    break;

                case 300:
                    int cancelId = Integer.parseInt(st.nextToken());
                    cancelProduct(cancelId);
                    break;

                case 400:
                    System.out.println(sellPackage());
                    break;

                case 500:
                    int s = Integer.parseInt(st.nextToken());
                    changeStart(s);
                    break;
            }


        }
        
    }
}