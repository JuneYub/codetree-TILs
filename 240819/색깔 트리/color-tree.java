import java.io.*;
import java.util.*;

class Node {
	
	int mid;
	int pid;
	int color;
	int maxdepth;
	List<Node> children;
	Map<Integer, Integer> colorCount;
	
	public Node(int mid, int pid, int color, int maxdepth) {
		this.mid = mid;
		this.pid = pid;
		this.color = color;
		this.maxdepth = maxdepth;
		this.children = new ArrayList<>();
		this.colorCount = new HashMap<>();
		this.colorCount.put(color, 1);
	}
	
	public void updateColorCount(int oldColor, int newColor) {
		colorCount.put(oldColor, colorCount.getOrDefault(oldColor, 0) -1);
		colorCount.put(newColor, colorCount.getOrDefault(newColor, 0) + 1);
	}
	
}

class ColorTree {
	Map<Integer, Node> nodes;
	Set<Integer> roots;
	
	public ColorTree() {
		nodes = new HashMap<>();
		roots = new HashSet<>();
	}
	
	public boolean addNode(int mid, int pid, int color, int maxdepth) {
		
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
		
		
		Node newNode = new Node(mid, pid, color, maxdepth);
		nodes.put(mid, newNode);
		
		if(pid == -1) {
			roots.add(mid);
		} else {
			nodes.get(pid).children.add(newNode);
			newNode.maxdepth = Math.min(nodes.get(pid).maxdepth-1, newNode.maxdepth);
			updateAncestors(nodes.get(pid), color);
		}
		
		return true;
	}
	
	private void updateAncestors(Node parent, int color) {
		while(parent != null) {
			parent.colorCount.put(color, parent.colorCount.getOrDefault(color, 0) + 1);
			parent = parent.pid != -1 ? nodes.get(parent.pid) : null;
		}
	}
	
	public void changeColor(int mid, int newColor) {
		if(!nodes.containsKey(mid)) {
			return;
		}
		Node node = nodes.get(mid);
		
		int oldColor = node.color;
		
		node.colorCount.put(oldColor, node.colorCount.get(oldColor)-1);
		if(node.colorCount.get(oldColor) == 0) {
			node.colorCount.remove(oldColor);
		}
		node.color = newColor;
		node.colorCount.put(newColor, node.colorCount.getOrDefault(newColor, 0) + 1);
		
		
		changeColorDFS(node, oldColor, newColor);
	}
	
	private void changeColorDFS(Node node, int oldColor, int newColor) {
		Node parent = node.pid != -1 ? nodes.get(node.pid) : null;
		while(parent != null) {
			parent.colorCount.put(oldColor, parent.colorCount.get(oldColor) - 1);
			if(parent.colorCount.get(oldColor) == 0) {
				parent.colorCount.remove(oldColor);
			}
			parent.colorCount.put(newColor, parent.colorCount.getOrDefault(newColor, 0) + 1);
			parent = parent.pid != -1 ? nodes.get(parent.pid) : null;
		}
		
		for(Node child:node.children) {
			changeColor(child.mid, newColor);
		}
	}
	
	public Integer getColor(int mid) {
		if(!nodes.containsKey(mid)) {
			return null;
		}
		return nodes.get(mid).color;
	}
	
	public long calculateScore() {
		long totalScore = 0;
		for(int rootId : roots) {
			totalScore += calculateScoreDFS(nodes.get(rootId));
		}
		return totalScore;
	}
	
	private long calculateScoreDFS(Node node) {
		long score = node.colorCount.size() * node.colorCount.size();
		for(Node child: node.children) {
			score += calculateScoreDFS(child);
		}
		return score;
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
					
					colorTree.addNode(mid, pid, color, maxdepth);
					break;
					
				// 색깔 변경
				case 200:
					int mid2 = Integer.parseInt(st.nextToken());
					int color2 = Integer.parseInt(st.nextToken());
					
					colorTree.changeColor(mid2, color2);
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