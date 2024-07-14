import java.io.*;
import java.util.*;

class Node {
    int data;
    Node prev, next;

    Node(int data) {
        this.data = data;
    }
}

class Pair {
    Node head;
    Node tail;

    int size = 0;
    
    Pair() {};
}

public class Main {

    public static Pair[] pairs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(br.readLine());

        Node[][] nodes = new Node[k+1][n+1];
        pairs = new Pair[k+1];

        for(int i = 1; i <= k; i++) {
            pairs[i] = new Pair();
        }

        Node curr = new Node(1);
        nodes[1][1] = curr;
        for(int i = 2; i <= n; i++) {
            Node newNode = new Node(i);
            nodes[1][i] = newNode;
            curr.next = newNode;
            newNode.prev = curr;
            curr = newNode;
        }

        pairs[1].head = nodes[1][1];
        pairs[1].tail = nodes[1][n];
        pairs[1].size = n;

        // 초기화
        Node head = nodes[1][1];
        Node tail = nodes[1][n];
        
        StringBuilder sb = new StringBuilder();

        for(int idx = 0; idx < q; idx++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            if(oper == 1) {
                oper1(i, j);
            } else if(oper == 2) {
                oper2(i, j);
            } else if(oper == 3) {
                oper3(i, j);
            } else {
                oper4(i, j);
            }
        }

        for(int i = 1; i <= k; i++) {
            sb.append(pairs[i].size);

            Node node = pairs[i].head;
            while(node != null) {
                sb.append(" " + node.data);
                node = node.next;
            }
            sb.append("\n");
        }

        System.out.print(sb);

    }

    public static void oper1(int i, int j) {
        if(pairs[i].head == null) return;

        Node iHead = pairs[i].head;
        
        
        // i번 책꽂이의 머리의 다음이 비어있지 않다면
        if(pairs[i].head.next != null) {
            pairs[i].head = pairs[i].head.next;
            pairs[i].head.prev = null;
        } else {
            // 비어있다면
            pairs[i].head = null;
            pairs[i].tail = null;
        }
        
        
        // j번 책꽂이의 꼬리가 비어있지 않다면 맨 뒤에 꽂는다.
        if(pairs[j].tail != null) {
           pairs[j].tail.next = iHead;
           iHead.prev = pairs[j].tail;
           pairs[j].tail = iHead;
        } else {
            // 비어있다면
            pairs[j].head = iHead;
            pairs[j].head.prev = null;
            pairs[j].tail = iHead;
        }
        
        pairs[j].tail.next = null;

        pairs[i].size--;
        pairs[j].size++;
    }

    public static void oper2(int i, int j) {
        if(pairs[i].head == null) return;

        Node iTail = pairs[i].tail;
        
        // i번째 책꽂이의 꼬리의 이전이 비어있지 않다면
        if(pairs[i].tail.prev != null) {
            pairs[i].tail = pairs[i].tail.prev;
            pairs[i].tail.next = null;
        } else { // 비어있다면
            pairs[i].tail = null;
            pairs[i].head = null;
        }

        
        // i번 책꽂이의 맨 뒷 책을 j번 책꽂이의 맨 앞에 꽂는다.
        // j번 책꽂이의 맨 앞이 비어있지 않다면
        if(pairs[j].head != null) {
            pairs[j].head.prev = iTail;
            iTail.next = pairs[j].head;
            pairs[j].head = iTail;
        } else {
            // 비어있다면
            pairs[j].head = iTail;
            pairs[j].tail = iTail;
        }
        
        pairs[j].head.prev = null;

        pairs[i].size--;
        pairs[j].size++;
    }

    public static void oper3(int i, int j) {
        if(pairs[i].head == null || i == j) return;
        
        if(pairs[j].head == null) {
            pairs[j].head = pairs[i].head;
            pairs[j].tail = pairs[i].tail;
        }
        else { // j번 책꽂이의 맨 앞으로 옮긴다.
            pairs[j].head.prev = pairs[i].tail;
            pairs[i].tail.next = pairs[j].head;

            pairs[j].head = pairs[i].head;
        }
        
        pairs[j].head.prev = null;

        pairs[i].head = null;
        pairs[i].tail = null;

        pairs[j].size += pairs[i].size;
        pairs[i].size = 0;
    }

    public static void oper4(int i, int j) {
        if(pairs[i].head == null || i == j) return;

        if(pairs[j].tail == null) {
            pairs[j].head = pairs[i].head;
            pairs[j].tail = pairs[i].tail;
        }
        else { // j번 책꽂이의 맨 뒤쪽으로 옮긴다.
            pairs[j].tail.next = pairs[i].head;
            pairs[i].head.prev = pairs[j].tail;

            pairs[j].tail = pairs[i].tail;
        }

        pairs[j].tail.next = null;
        
        pairs[i].head = null;
        pairs[i].tail = null;

        pairs[j].size += pairs[i].size;
        pairs[i].size = 0;
    }



    public static void connect(Node s, Node e) {
        
        if(s != null) {
            s.next = e;
        }

        if(e != null) {
            e.prev = s;
        }
        
    }
}