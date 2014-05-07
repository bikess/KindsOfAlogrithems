package hashmap;
/**
 * 此类相当于Entry<key,value>的类
 * @author bike
 *
 */
import java.util.HashMap;
import java.util.Hashtable;
public class LinkNode {
//	private Object ob;
	public Object key;
	public Object value;
	public LinkNode next;
	public int hash ;
	public void setObjectKey(Object ob){
		this.key = ob;
	}
	public Object getObjectKey(){
		return this.key;
	}
	public void setObjectValue(Object ob){
		this.value = ob;
	}
	public Object getObjectValue(){
		return this.value;
	}
	public LinkNode(int hash,Object key,Object value,LinkNode e){
		this.hash = hash;
		this.key = key;
		this.value = value;
		this.next = e;
		
	}

//	将当前的node值赋给下一个节点，让去引用
	public void setNextNode(LinkNode node){
		next = node;
	}
//	返回下一个节点
	public LinkNode getNextNode(){
		return this.next;
	}
}
