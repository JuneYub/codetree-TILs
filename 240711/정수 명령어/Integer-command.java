import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        TreeSet<Integer> tree = new TreeSet<Integer>();
        for(int i = 0; i < t; i++) {

            int n = Integer.parseInt(br.readLine());

            for(int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();


                if("I".equals(oper)) {
                    int num = Integer.parseInt(st.nextToken());
                    tree.add(num);
                } else if("D".equals(oper) ) {

                    int num = Integer.parseInt(st.nextToken());

                    if(num == 1) {

                        if(tree.isEmpty()) {
                            continue;
                        } else {
                            int last = tree.last();
                            tree.remove(last);
                        }
                    }

                    if(num == -1) {
                        if(tree.isEmpty()) {
                            continue;
                        } else {
                            int first = tree.first();
                            tree.remove(first);
                        }
                    }
                }
            } // 반복문 종료

            if(tree.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(tree.last() + " " + tree.first());
            }
        }
    }
}