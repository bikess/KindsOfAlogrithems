package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class BinTree {
	//二叉树
	private BinTreeNode root;
	public BinTree(){
		this.root = null;
	}
	public BinTree(BinTreeNode root){
		this.root = root;
	}
	public void set_root(BinTreeNode t)
	{
	    root = t;
	}
	public BinTreeNode get_root()
	{
	        return root;
	}
//1.创建二叉树,输入的是一个字符数组（6 4 2 3 # # # #）
//	 采用先序创建二叉树，遇到‘#’表示节点为空
	public BinTreeNode create_tree(char num[],int begin){
		if(begin==num.length)
			return null;
		BinTreeNode root = new BinTreeNode();
		BinTreeNode lchlid,rchlid;
		if(num[begin]!='#'){
			BinTreeNode data = new BinTreeNode((int)num[begin]-48);
			root = data;
//			递归构造左孩子
			lchlid = create_tree(num,begin++);
			data.left = lchlid;
//			递归构造右孩子
			rchlid = create_tree(num,begin+2);
			root.right = rchlid;
			return root;
		}
		else{
			root = null;
			return root;
		}
		
	}
	    //2.前序遍历
    public void pre_order1(BinTreeNode root){
    	BinTreeNode p = root;
//    	这里我们不用栈，而是这直接把树递归输出前序遍历
    	if(p!=null){
    		System.out.println(p.data);
    		pre_order1(root.left);
    		pre_order1(root.right);
    	}
    }
    public void pre_order2(BinTreeNode root){
    	Stack<BinTreeNode> stack = new Stack<>();
    	stack.push(root);
    	while(root!=null||!stack.isEmpty()){
    		BinTreeNode p = stack.pop();
    		System.out.println(p.data);
    		if(p.right!=null){
    			stack.push(p.right);
    		}
    		if(p.left!=null){
    			stack.push(p.left);
    		}
    	}
    }
	    //3.中序遍历
	public void in_order1(BinTreeNode root){
		BinTreeNode p = root;
		if(p!=null){
			in_order1(p.left);
			System.out.println(p.data);
			in_order1(p.right);
		}
	}
	public void in_order2(BinTreeNode root){
		Stack<BinTreeNode> stack = new Stack<>();
		BinTreeNode p = root;
		stack.push(p);
		while(!stack.isEmpty()){
			BinTreeNode t = stack.pop();
//			若当前的输出节点是当前节点的左孩子，或者此节点的左孩子为空
			if(t.left==p||t.left==null)
			{
				System.out.println(t.data);
				if(t.right!=null){
					stack.push(t.right);
				}
			}
			else{
				if(t.right!=null)
					stack.push(t.right);
				stack.push(t);
				stack.push(t.left);
			}
			
		}
	}
	//4.后序遍历
	public void post_order1(BinTreeNode root){
		BinTreeNode p = root;
		if(p!=null){
			post_order1(p.left);
			post_order1(p.right);
			System.out.println(p.data);
		}
	}
	public void post_order2(BinTreeNode root){
		Stack<BinTreeNode> stack = new Stack<>();
		BinTreeNode p = root;
		stack.push(root);
		while(!stack.isEmpty()){
			BinTreeNode cur = stack.peek();
//			若刚刚输出的是当前栈弹出的节点的左孩子或者是右孩子
			if(cur.left==p||cur.right==p||(cur.left==null)&&(cur.right==null)){
				BinTreeNode node = stack.pop();
				System.out.println(node.data);
				p = cur;
			}else{
				if(cur.right!=null){
					stack.push(cur.right);
				}if(cur.left!=null){
					stack.push(cur.left);
				}
			}
		}
	}
	    //5.层次遍历,利用队列来完成树的层次遍历
	public void level_order(BinTreeNode root){
		if(root == null)
			return;
		Queue<BinTreeNode> q = new ArrayDeque();
		q.add(root);
		while(!q.isEmpty()){
			BinTreeNode p  = q.poll();
			System.out.println(p.data);
			if(p.left!=null){
				q.add(p.left);
			}
			if(p.right!=null){
				q.add(p.right);
			}
		}
		
	}
	    //6.获得叶子节点的个数
	public int get_leaf_num(BinTreeNode root){
		if(root==null){
			return 0;
		}
		if(root.left==null&&root.right==null){
			return 1;
		}
		else{
			return get_leaf_num(root.left)+get_leaf_num(root.right);
		}
	
	}
	    //7.获得二叉树的高度
	int get_tree_height(BinTreeNode root){
		if(root==null){
			return 0;
		}
		if(root.left==null&&root.right==null)
			return 1;
		int leftheight = get_tree_height(root.left);
		int rightheight = get_tree_height(root.right);
		return leftheight>=rightheight ? leftheight+1:rightheight+1;
	}
	// 获得二叉树的高度，利用上面的递归方法是将复杂度太高
	    //8.交换二叉树的左右儿子
	public void swap_left_right(BinTreeNode root){
		if(root==null){
			return ;
		}
		else{
			BinTreeNode left = root.left;
			BinTreeNode right = root.right;
			root.left = right;
			root.right = left;
			swap_left_right(left);
			swap_left_right(right);
		}
	}
	    //9.求两个节点pNode1和pNode2在以r为树根的树中的最近公共祖先
//	返回找到某个节点的二叉树中的路径
	public ArrayList<BinTreeNode> returnPathToNode(BinTreeNode root,BinTreeNode node){
		ArrayList<BinTreeNode> list = new ArrayList<>();
		boolean is = false;
		if(root == null){
			return null;
		}
		if(root == node){
			list.add(root);
			return list;
		}else{
			if(root.left!=null){
				is = returnPath(root.left,node,list);
			}if(root.right!=null){
				is = returnPath(root.right,node,list);
			}
			if(is == true)
				return list;
		}
		return null;
	}
	private boolean returnPath(BinTreeNode root, BinTreeNode node,
			ArrayList<BinTreeNode> list) {
		// TODO Auto-generated method stub
		boolean ispath = false;
		if(root==node){
			list.add(root);
			return true;
		}else{
			if(root.left!=null)
			{
				ispath = returnPath(root.left, node, list);
				if(ispath == true){
					list.add(root.left);
					return true;
				}
			}
			if(root.right!=null)
			{
				ispath = returnPath(root.right, node, list);
				if(ispath==true){
					list.add(root.right);
					return true;
				}
			}
			/**
			 *  这部分代码可以改成
			 *  if(returnPath(root.left,node,list)||returnPath(root.right,node,list)){
			 *  list.add(root);
			 *  return true;
			 *  }
			 */
		}
		return false;
	}
	/***
	 * 方法1 递归解法
	 * @param root
	 * @param pNode1
	 * @param pNode2
	 * @return
	 */
	/**
	 * 求两个节点的公共祖先可以用到上面的：判断一个节点是否在一颗子树中。
	 * （1）如果两个节点同时在根节点的右子树中，则最近公共祖先一定在根节点的右子树中。
	 * （2）如果两个节点同时在根节点的左子树中，则最近公共祖先一定在根节点的左子树中。
	 * （3）如果两个节点一个在根节点的右子树中，一个在根节点的左子树中，则最近公共祖先一定是根节点。
	 * （4）可能一个节点pNode1在以另一个节点pNode2为根的子树中，这时pNode2就是这
	 * 两个节点的最近公共祖先了。显然这也是一个递归的过程啦
	 * @param root
	 * @param t
	 * @return
	 */
	public  BinTreeNode get_nearest_common_father(BinTreeNode root,BinTreeNode pNode1,BinTreeNode pNode2){
//		//判断pNode2是否在以PNode1为根的子树中
//		if(is_in_tree(pNode1, pNode2)){
//			return pNode1;
//		}
////		判断pNode1是否在以PNode2为根的子树中
//		if(is_in_tree(pNode2, pNode1)){
//			return pNode2;
//		}
		boolean one_in_left,one_in_right,another_in_left,another_in_right;
		one_in_left = is_in_tree(root.left, pNode1);
		another_in_right = is_in_tree(root.right, pNode2);
		another_in_left = is_in_tree(root.left,pNode2);
		one_in_right = is_in_tree(root.right,pNode1);
		if(one_in_left&&another_in_right||one_in_right&&another_in_left){
			return root;
		}else if(one_in_left&&another_in_left){
			return get_nearest_common_father(root.left, pNode1, pNode2);
		}else if(one_in_right&&another_in_right){
			return get_nearest_common_father(root.right, pNode1, pNode2);
		}else{
			return null;
		}

	}
	/****
	 * 方法2 首先先得到找到两个节点的路径序列，然后对这两个路径序列，从根节点开始对这两个路径序列的节点进行比较，当第一次
	 * 发现路径序列的节点数量不一致是，则之前的那个节点就是所要求解的节点
	 */
	public  BinTreeNode get_nearest_common_father2(BinTreeNode root,BinTreeNode pNode1,BinTreeNode pNode2){
//		首先得到两个节点在树中的路径
		ArrayList<BinTreeNode> list1 = returnPathToNode(root, pNode1);
		ArrayList<BinTreeNode> list2 = returnPathToNode(root, pNode2);
		BinTreeNode nearFarther = root;
		int i = list1.size()-1;
		int j = list2.size()-1;
		while(i>=0&&j>=0&&list1.get(i)==list2.get(j)){
			nearFarther = list1.get(i);
			i--;
			j--;
		}
		return nearFarther;
		
	}
	
	    //10.打印和为某一值的所有路径,从根节点开始找到所有路径，
//	使得路径上的节点值和为某一数值（路径不一定以叶子节点结束）
	Stack<BinTreeNode> dfs_s = new Stack<>();
	Stack<BinTreeNode> print_s = new Stack<>();
	void print_rout(BinTreeNode root,int sum){	
//		打印出从r开始的和为sum的所有路径
		if(root ==null){
			return;
		}
//		入栈
		sum = sum - root.data;
		dfs_s.push(root);
		if(sum<=0){
			if(sum == 0){
				while(!dfs_s.isEmpty()){
					print_s.push(dfs_s.pop());
				}
				while(!print_s.isEmpty()){
					System.out.print(print_s.peek().data+" ");
					dfs_s.push(print_s.peek());
					print_s.pop();
				}
				System.out.println();
			}
			sum += root.data;
			dfs_s.pop();
			return;
		}
//		递归进入左子树
		print_rout(root.left, sum);
//		递归进入左子树
		print_rout(root.right, sum);
		sum+= root.data;
		dfs_s.pop();
	}
	    //11.判断一个节点t是否在以r为根的子树中
	
	public boolean is_in_tree(BinTreeNode root,BinTreeNode t){
		if(root ==null )
			return false;
		else if(t==root)
			return true;
		else{
			boolean has = false;
			if(root.left!=null){
				has = is_in_tree(root.left, t);
			}
			if(!has&&root.right!=null){
				has = is_in_tree(root.right,t);
			}
			return has;
		}
	};
//	12 判断二叉树是不是平衡
	/***
	 * 思路1：求得左右子树的深度，然后递归判断，使得每一个节点的左右子树差值的绝对
	 * 值都小于等于1
	 */
	boolean isBalanced(BinTreeNode root){
		if(root == null)
			return true;
		int leftHeight = get_tree_height(root.left);
		int rightHeight = get_tree_height(root.right);
		int diff = rightHeight - leftHeight;
		if(diff>1||diff<-1)
			return false;
		return isBalanced(root.left)&&isBalanced(root.right);
	}
	/***
	 * 思路2如果我们用后序遍历的方式遍历二叉树的每一个结点，
	 * 在遍历到一个结点之前我们已经遍历了它的左右子树。
	 * 只要在遍历每个结点的时候记录它的深度（某一结点的深度等于它到
	 * 叶节点的路径的长度），我们就可以一边遍历一边判断每个结点是不是平衡的
	 */
	boolean isBalanced2(BinTreeNode root,int[] pDepth){
		if(root==null)
		{
			pDepth[0] = 0;
			return true;
		}
		int []left=new int[1];
		int	[]right = new int[1];
		if(isBalanced2(root.left, left)&&isBalanced2(root.right, right)){
			pDepth[0]= 1+(left[0]>right[0] ? left[0] : right[0]);
			if(pDepth[0]>=-1&&pDepth[0]<=1)
				return true;
			else
				return false;
		}
		
		return false;
		
	}
	
//	 13 判断2个树是否是相同的树 
    public boolean isSameTree(BinTreeNode p, BinTreeNode q) {
    	if(p==null&& q==null)
    		return true;
    	else if(p!=null&&q!=null){
    		boolean lSame = false,rSame = false;
    		lSame = isSameTree(p.left, q.left);
    		rSame = isSameTree(p.right, q.right);
    		
    		if(p.data==q.data&&lSame&&rSame){
    			return true;
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
    }
}
