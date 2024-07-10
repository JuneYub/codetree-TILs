import java.io.*;
import java.util.*;

public class Main {
    public static int n ;
    public static TreeSet<Integer> tree = new TreeSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();

            if("add".equals(oper)) {
                int num = Integer.parseInt(st.nextToken());
                tree.add(num);
            } else if("largest".equals(oper)) {
                if(tree.isEmpty()) {
                    sb.append("None").append("\n");
                } else {
                    sb.append(tree.last()).append("\n");
                }
            } else if("smallest".equals(oper)) {
                if(tree.isEmpty()) {
                    sb.append("None").append("\n");
                } else {
                sb.append(tree.first()).append("\n");
                }

            } else if("find".equals(oper)) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(tree.contains(num)).append("\n");
            } else if("lower_bound".equals(oper)) {
                int num = Integer.parseInt(st.nextToken());
                if(tree.isEmpty()) {
                    sb.append("None").append("\n");
                } else {
                    if(tree.ceiling(num) == null) {
                    	sb.append("None").append("\n");
                    } else {
                    	sb.append(tree.ceiling(num)).append("\n");
                    }
                }
            } else if("upper_bound".equals(oper)) {
                if(tree.isEmpty()) {
                    sb.append("None").append("\n");
                } else {
                    int num  = Integer.parseInt(st.nextToken());
                    if(tree.higher(num) == null) {
                    	sb.append("None").append("\n");
                    } else {
                    	sb.append(tree.higher(num)).append("\n");
                    }
                    
                }

            } else if("remove".equals(oper)) {
            	int num = Integer.parseInt(st.nextToken());
            	tree.remove(num);
            }
            
            
        }
        System.out.println(sb.toString());


    }
}