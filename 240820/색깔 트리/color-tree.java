import java.io.*;
import java.util.*;

class Node {
	
	int mid;
	int pid;
	int color;
	int maxdepth;
	List<Node> children;
	Map<Integer, Integer> colorCount;
	int lastUpdate;
	
	public Node(int mid, int pid, int color, int maxdepth, int lastUpdate) {
		this.mid = mid;
		this.pid = pid;
		this.color = color;
		this.maxdepth = maxdepth;
		this.children = new ArrayList<>();
		this.lastUpdate = lastUpdate;
	}
}

class ColorTree {
	Map<Integer, Node> nodes;
	Set<Integer> roots;
	
	public ColorTree() {
		nodes = new HashMap<>();
		roots = new HashSet<>();
	}
	
	public boolean addNode(int mid, int pid, int color, int maxdepth, int lastUpdate) {
		
		if(nodes.containsKey(mid)) {
			return false;
		}
		
		if(pid != -1 && !nodes.containsKey(pid)) {
			return false;
		}
		
		if(pid != -1) {
			Node parent = nodes.get(pid);
			if(parent.maxdepth == 1) {
				return false;
			}
		}
		
		
		Node newNode = new Node(mid, pid, color, maxdepth, lastUpdate);
		nodes.put(mid, newNode);
		
		if(pid == -1) {
			roots.add(mid);
		} else {
			nodes.get(pid).children.add(newNode);
			newNode.maxdepth = Math.min(nodes.get(pid).maxdepth-1, newNode.maxdepth);
		}
		
		return true;
	}
	
	public void changeColor(int mid, int newColor, int lastUpdate) {
		if(!nodes.containsKey(mid)) {
			return;
		}
		Node node = nodes.get(mid);
		node.color = newColor;
		node.lastUpdate = lastUpdate;
	}
	
	public Integer getColor(int mid) {
		if(!nodes.containsKey(mid)) {
			return null;
		}
		Node orgNode = nodes.get(mid);
		Node currNode = orgNode;
		int color = currNode.color;
		
		while(currNode.pid != -1) {
			Node parent = nodes.get(currNode.pid);
			if(parent.lastUpdate > orgNode.lastUpdate) {
				color = parent.color;
			}
			currNode = parent;
		}
		
		return color;
		
	}
	
	public long calculateScore() {
		long totalScore = 0;
		for(int rootId : roots) {
			Node root = nodes.get(rootId);
			totalScore += (long)calculateScoreDFS(root, root.color, root.lastUpdate)[0];
		}
		return totalScore;
	}
	
	private Object[] calculateScoreDFS(Node curr, int color, int lastUpdate) {
		
		if(curr.lastUpdate > lastUpdate) {
			color = curr.color;
			lastUpdate = curr.lastUpdate;
		}
		
		long score = 0;
		
		Set<Integer> set = new HashSet<>();
		set.add(color);
		
		for(Node child: curr.children) {
			Object[] subResult = calculateScoreDFS(child, color, lastUpdate);
			set.addAll((Set<Integer>)subResult[1]);
			score += (long) subResult[0];
		}
		
		score += (long) set.size() * set.size();
		
		return new Object[] {score, set};
	}
	
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int q = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		ColorTree colorTree = new ColorTree();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int command =  Integer.parseInt(st.nextToken());
			
			switch(command) {
			
				// 새로운 노드추가
				case 100:
					int mid = Integer.parseInt(st.nextToken());
					int pid = Integer.parseInt(st.nextToken());
					int color = Integer.parseInt(st.nextToken());
					int maxdepth = Integer.parseInt(st.nextToken());
					
					colorTree.addNode(mid, pid, color, maxdepth, i);
					break;
					
				// 색깔 변경
				case 200:
					int mid2 = Integer.parseInt(st.nextToken());
					int color2 = Integer.parseInt(st.nextToken());
					
					colorTree.changeColor(mid2, color2, i);
					break;
				
				// 색깔 조회
				case 300:
					int mid3 = Integer.parseInt(st.nextToken());
					long colorResult = colorTree.getColor(mid3);
					sb.append(colorResult + "\n");
					//System.out.println(colorResult);
					break;
					
				// 점수 조회
				case 400:
					long result = colorTree.calculateScore();
					sb.append(result+ "\n");
					//System.out.println(result);
					break;
			}
			
			
		}
		System.out.print(sb);
	}
}