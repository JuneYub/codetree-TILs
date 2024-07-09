import java.util.*;
import java.io.*;


public class Main {
    public static final int MAX_N = 500;

    public static int n, m;
    public static int ans;
    public static String[] A = new String[MAX_N];
    public static String[] B = new String[MAX_N];
    public static HashSet<String> s = new HashSet<>();

    // x,y,z 순번을 정했을 때 A와 B에 있는 페이지는 구분되는가?
    public static boolean testLocation(int x, int y, int z) {
        s = new HashSet<>();
        
        // A쪽 모든 페이지를 돌면서 파라미터로 받은 순서에 해당하는 모든 문자열을 set에 넣는다.
        for(int i = 0; i < n; i++) {
            s.add(A[i].substring(x, x+1) + A[i].substring(y, y + 1) + A[i].substring(z, z + 1) );
        }

        // 해당  인덱스를 가지고 B쪽을 돌면서 구분되는지 확인한다.
        for(int i = 0; i < n; i++) {
            if(s.contains(B[i].substring(x, x+1) + B[i].substring(y, y+1) + B[i].substring(z, z+1))) {
                return false;
            }
        }

        return true;

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            A[i] = br.readLine();
        }

        for(int i = 0; i < n; i++) {
            B[i] = br.readLine();
        }

        // 문자열에서 서로 다른 세 자리의 조합을 모두 순회한다.
        for(int i = 0; i < m; i++) {
            for(int j = i + 1; j < m; j++) {
                for(int k = j+1; k < m; k++) {
                    if(testLocation(i, j, k)) ans++;
                }
            }
        }

        System.out.print(ans);

    }
}