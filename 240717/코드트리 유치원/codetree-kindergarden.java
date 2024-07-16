import java.util.*;
import java.io.*;

class Node {
    int num;
    Node left, right;

    public Node(int num) {
        this.num = num;
    }

    public String printNext() {
        if(left != null && right != null) {
            return left.num + " " + right.num;
        }
        else {
            return "-1";
        }
    }
}

public class Main {


    public static int q;
    public static HashMap<Integer, Node> map = new HashMap<>();
    public static int idx = 2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        q = Integer.parseInt(br.readLine());

        map.put(1, new Node(1));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 1) {
              int a = Integer.parseInt(st.nextToken());
              int b = Integer.parseInt(st.nextToken());

              afterRand(a, b);

            } else if(num == 2) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                beforeRand(a, b);

            } else if(num == 3) {
                int a = Integer.parseInt(st.nextToken());
                sb.append(map.get(a).printNext() + "\n");
            }
        }
        System.out.println(sb);

    }

    public static void conn(Node s, Node e) {
        s.right = e;
        e.left = s;
    }

    public static void afterRand(int numA, int count) {
        Node curr = map.get(numA);
        // 시작 노드의 오른쪽 값
        Node initNext = curr.right;

        for(int i = 0; i < count; i++) {
            // 새로운 노드를 만들고 전체 노드 인덱스를 올려준다.
            Node newNode = new Node(idx);
            map.put(idx++, newNode);
            conn(curr, newNode);
            // 현재 노드는 다음 노드로 변경해준다.
            curr = newNode;
        }

        if(initNext != null) {
            conn(curr, initNext);
        }
    }

    public static void beforeRand(int numA, int count) {
        // 시작 노드
        Node initNode = map.get(numA);
        Node curr = initNode.left;

        for(int i = 0; i < count; i++) {
            Node newNode = new Node(idx);
            map.put(idx++, newNode);
            if(newNode != null && curr != null) {
                conn(newNode, curr);
            }
            // 현재 노드는 다음 노드로 변경해준다.
            curr = newNode;
        }

        conn(curr, initNode);
    }
}