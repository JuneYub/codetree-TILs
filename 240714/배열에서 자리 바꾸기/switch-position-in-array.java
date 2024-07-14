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
        s.next = e;
        e.prev = s;
    }

    public static void changeNodePair(Node s1, Node e1, Node s2, Node e2) {
        Node beforeS1 = s1.prev;
        Node AfterE1 = e1.next;
        Node beforeS2 = s2.prev;
        Node AfterE2 = e2.next;
        
        if(e1.next == s2) {
        	if(beforeS1 != null) {
        		beforeS1.next = s2;
        		s2.prev = beforeS1;
        		e2.next = s1;
        		s1.prev = e2;
        	}
        	
        	if(AfterE2 != null) { // 끝이 null이 아니라면
        		e1.next = AfterE2;
        		AfterE2.prev = e1;
        	}
        } else {
        
	        if(beforeS1.next != null) {
	        	beforeS1.next = s2;
	        }
            
            s2.prev = beforeS1;
            AfterE1.prev = e2;
            e2.next = AfterE1;
	        
            beforeS2.next = s1;
            s1.prev = beforeS2;
            
            if(AfterE2 != null) {
            	AfterE2.prev = e1;
            }
            
            e1.next = AfterE2;

        }
    }

}