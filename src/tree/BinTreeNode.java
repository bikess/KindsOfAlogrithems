package tree;

public class BinTreeNode {
	int data;
	BinTreeNode left;
	BinTreeNode right;
	
	public BinTreeNode(){
		left = new BinTreeNode();
		right = new BinTreeNode();
	}
	public BinTreeNode(int data){
		this.data = data;
		left = new BinTreeNode();
		right = new BinTreeNode();
	}
}
