import java.io.*;
import java.util.*;

class Node {
    int data;
    Node prev, next;

    public Node(int data) {
        this.data = data;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int q = Integer.parseInt(br.readLine());

        HashMap<Integer, Node> map = new HashMap<>();

        StringTokenizer st;

        Node curr = new Node(1);
        map.put(1, curr);

        for(int i = 2; i <= n; i++) {
            Node newNode = new Node(i);
            
            newNode.prev = curr;
            curr.next = newNode;
            curr = newNode;

            map.put(i, curr);
            
        }

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int e1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());

            changeNodePair(map.get(s1), map.get(e1), map.get(s2), map.get(e2));
        }

        Node startNode = map.get(1);

        while(startNode.prev != null) {
            startNode = startNode.prev;
        }

        StringBuilder sb = new StringBuilder();
        while(startNode.next != null) {
            sb.append(startNode.data + " ");
            startNode = startNode.next;
        }
        
        sb.append(startNode.data);

        System.out.print(sb);

    }

    public static void connect(Node s, Node e) {
        if(s != null) {
        	s.next = e;
        }
    	if(e != null) {
    		e.prev = s;
    	}
        
    }

    public static void changeNodePair(Node a, Node b, Node c, Node d) {
        // c의 이전 노드는 a의 이전 노드가 된다.
    	Node after_prevA = c.prev;
    	Node after_nextB = d.next;
    	
    	Node after_prevC = a.prev;
    	Node after_nextD = b.next;
        
    	// b 다음 c
        if(b.next == c) {
        	after_prevA = d;
        	after_nextD = a;
        }
        
        // d 다음 a
        if(d.next == a) {
        	after_nextB = c;
        	after_prevC = b;
        }
        
        connect(after_prevA, a);
        connect(b, after_nextB);
        
        connect(after_prevC,  c);
        connect(d, after_nextD);

    }

}