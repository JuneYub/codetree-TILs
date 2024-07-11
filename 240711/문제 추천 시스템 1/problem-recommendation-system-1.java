import java.util.*;
import java.io.*;

class Problem implements Comparable<Problem> {
    int no, lv;
    Problem(int no, int lv) {
        this.no = no;
        this.lv = lv;
    }

    public int compareTo(Problem p) {
        if(this.lv == p.lv) {
            return this.no - p.no;
        }
        return this.lv - p.lv;
    }
}

public class Main {
    
    public static TreeSet<Problem> s = new TreeSet<>();

    public static void main(String[] args) throws Exception  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());
            int lv = Integer.parseInt(st.nextToken());

            s.add(new Problem(no, lv));
        }

        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if("ad".equals(oper)) {            
                int no = Integer.parseInt(st.nextToken());
                int lv = Integer.parseInt(st.nextToken());
                s.add(new Problem(no, lv));
            } else if("sv".equals(oper)) {
                int no = Integer.parseInt(st.nextToken());
                int lv = Integer.parseInt(st.nextToken());
                s.remove(new Problem(no, lv));
            } else if("rc".equals(oper)) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 1) {
                    sb.append(s.last().no).append("\n");
                } else if(num == -1) {
                    sb.append(s.first().no).append("\n");
                }
            }
        }
        
        System.out.println(sb.toString());



    }
}