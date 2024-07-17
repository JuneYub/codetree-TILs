import java.io.*;
import java.util.*;


class Node {
	int num;
	int line;
	Node left, right;
	public Node(int num, int line) {
		this.num = num;
		this.line = line;
	}
}
public class Main {
	public static int m, n, q;
	public static Node[] nodes;
	public static HashMap<Integer, Node> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        nodes = new Node[m];
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int cnt = Integer.parseInt(st.nextToken());
        	
        	// 현재 줄의 첫번째 사람
        	int first = Integer.parseInt(st.nextToken());
        	Node curr = new Node(first, i);
        	map.put(first, curr);
        	nodes[i] = curr;
        	
        	for(int j = 1; j < cnt; j++) {
        		int num = Integer.parseInt(st.nextToken());
        		Node newNode = new Node(num, i);
        		map.put(num, newNode);
        		
        		curr.right = newNode;
        		newNode.left = curr;
        		curr = newNode;
        	}
        }
        
        for(int i = 0; i < q; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken());
        	if(num == 1) {
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		cutInLine(a,b );
        	} else if(num == 2) {
        		int a = Integer.parseInt(st.nextToken());
        		delete(a);
        	} else {
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		allLineCut(a,b,c);
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
        	Node curr = nodes[i];
        	if(curr == null) {
        		sb.append("-1");
        	}
        	else {
        		while(curr != null) {
        			sb.append(curr.num + " ");
        			curr = curr.right;
        		}
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
        
    }
    
    public static void conn(Node s, Node e) {
    	if(s != null) s.right = e;
    	if(e != null) e.left = s;
    }
    
    // 1번 a사람이 b 사람 앞으로 새치기
    public static void cutInLine(int a, int b) {
    	Node nodeA = map.get(a);
    	Node nodeB = map.get(b);

    	// a의 좌우
    	Node aLeft = nodeA.left;
    	Node aRight = nodeA.right;
    	
    	// a가 라인의 첫줄이라면
    	if(nodeA == nodes[nodeA.line]) {
    		// 비어있으면 null 할당
    		nodes[nodeA.line] = aRight;
    		// A가 사라지기 때문에 왼쪽을 끊어준다.
    		if(aRight != null) aRight.left = null;
    	} else {
    		// 첫줄이 아니라면 좌 우를 연결
    		conn(aLeft, aRight);
    	}
    	
    	Node bLeft = nodeB.left;
    	
    	// b가 라인의 첫줄이라면
    	if(nodeB == nodes[nodeB.line]) {
    		// 이제 a가 처음 노드가 된다.
    		nodes[nodeB.line] = nodeA;
    	}
    	
    	// a <==> b
    	conn(nodeA, nodeB);
    	// 기존 b왼쪽 <==> a
    	conn(bLeft, nodeA);
    	
    	nodeA.line = nodeB.line;
    }
    
    public static void delete(int num) {
    	Node curr = map.get(num);
    	
    	Node currLeft = curr.left;
    	Node currRight = curr.right;
    	
    	// 현재 노드의 좌우 연결
    	conn(currLeft, currRight);
    	
    	// 현재 노드가 줄의 맨 앞이라면
    	if(nodes[curr.line] == curr) {
    		nodes[curr.line] = curr.right;
    	}
    	
    	map.remove(num);
    }
    
    // a부터 b까지 통째로 c앞으로 새치기
    public static void allLineCut(int a, int b, int c) {
    	Node nodeA = map.get(a);
    	Node nodeB = map.get(b);
    	Node nodeC = map.get(c);
    	
    	// 노드 a가 줄 맨 앞사람이라면?
    	if(nodes[nodeA.line] == nodeA) {
    		nodes[nodeA.line] = nodeB.right;
    	}
    	
    	// 노드 C가 맨 앞사람이ㅏ면?
    	if(nodes[nodeC.line] == nodeC) {
    		nodes[nodeC.line] = nodeA;
    	}
    	
    	// Aleft <==> Bright
    	conn(nodeA.left, nodeB.right);
    	
    	int lineNum = nodeC.line;
    	Node cLeft = nodeC.left;
    	
    	conn(cLeft, nodeA);
    	conn(nodeB, nodeC);
    	
    	Node changeNode = nodeA;
    	while(true) {
    		changeNode.line = lineNum;
    		changeNode = changeNode.right;
    		
    		if(changeNode.num == nodeC.num) break;
    	}
    }
    
}