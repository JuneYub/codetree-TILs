import java.util.*;
import java.io.*;

class Tuple implements Comparable<Tuple> {
    int len, s, e;
    
    public Tuple(int len, int s, int e) {
        this.len = len;
        this.s = s;
        this.e = e;
    }

    public int compareTo(Tuple t) {
        if(this.len != t.len) {
            return t.len - this.len;
        } else if(this.s != t.s) {
            return this.s - t.s;
        } else {
            return this.e - t.e;
        }
    }
}

public class Main {
    public static int n, m;
    public static TreeSet<Integer> sNum = new TreeSet<>();
    public static TreeSet<Tuple> sLen = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sNum.add(-1);
        sNum.add(n+1);

        sLen.add(new Tuple(n+1, -1, n+1));

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int y = Integer.parseInt(st.nextToken());

            // 입력받은 숫자는 제거 목록에 추가한다.
            sNum.add(y);
            
            // x y z 순으로 
            // y의 바로 뒤 숫자
            int z = sNum.higher(y);
            // y의 바로 앞에 숫자
            int x = sNum.lower(y);

            sLen.remove(new Tuple(z - x - 1, x, z));
            sLen.add(new Tuple(y-x-1, x, y));
            sLen.add(new Tuple(z-y-1, y,z));

            System.out.println(sLen.first().len);


        }

    }
}