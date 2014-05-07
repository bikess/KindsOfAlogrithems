package hashmap;

/**
 * 本方法类实现了自己实现了HashMap
 * hashMap的基本的实现原理就是这样的，一个装载键值对对象的Entry数组作为
 * 装载数组，每一个数组元素是本数组索引的头节点。
 * 当插入一个键值对对象时通过计算键的hashcode，有hashcode通过哈希函数计算
 * 此键值对在此hash数组中的索引。一旦有数据插入hashMap，就需要判断数组的
 * 装载因子，一旦装载因子大于某个值，则需要扩充数组的容量。
 * 
 * （1）若对应索引处为空，在直接插入索引处
 * （2）若对应索引处有值，而且判断此键值对在此索引处已经存在了，（判断依据要保证key中的值一样），更新value的值
 * （3）若对应索引处有值，而且判断此键值对在此索引处不存在，则将该键值对插入索引处，并让索引处新插入的键值对执行原来的键值对
 * 没当插入一个新键值对，要判断一下是否超过装载因子；
 * 可以看出hashtap解决冲突的方法是采取的链址法，通过在索引处把键值对链接成链来解决冲突
 * 
 * @author bike
 *
 */



public class HashMap {
//	定义一个节点数组，此数组中存储的类型是LinkNode，我们每一此向
//	hashmap中存放数据首先把要存放的对象封装，然后装入LinkNode节点对象里面
//	然后把LinkNode节点的首节点，放入HashMap的节点类型为LinkNode的数组中去
	private LinkNode[] hashArray;
//	数组的初始化长度
	private static int length = 20;
//	当前hashmap中存储的节点数量
	private static int count = 0;
	
//	构造函数
	public HashMap(){
		hashArray = new LinkNode[length];
	}
	
//	将一个封装好的Object对象加入到hashMap中去
	public void put(Object key,Object value){
//		获取置放数组的索引位置
		int index = HashCode(key.hashCode(),length);
		if(key == null){
//			java实际实现的HashMap是支持键为空的，这里我们自己实现的不支持
			return;
		}
//	判断此索引位置的情况是否为空，若不为空则循环遍历该索引处的所有链表中的键值对
		for(LinkNode e = hashArray[index];e!=null;e=e.next){
			
//			若已经存在相同的键值对，则更新value的值无需插入
			Object k = null;
			if(e.hash == key.hashCode()&&(key == e.key)||key.equals(k)){
				e.setObjectValue(value);
			}
//		if(onenode==null){
////			当前 为空值的时候，直接放入，此节点则为根节点；此时，数组的空间被占用了一个，数组的空间计数器则加一
//			hashArray[index] = node;
//			count++;
////			装载因子的判断
//			if(loadSize(count)){
////				若装载因子超过某个值，需要增加数组的长度来减小装载因子
//			}
		}
//		数组元素数加1
		count++;
		addLinknode(key.hashCode(),key,value,index);
	}
	private void addLinknode(int hashCode, Object key, Object value, int index) {
		// TODO Auto-generated method stub
		LinkNode e = hashArray[index];
		hashArray[index] = new LinkNode(hashCode, key, value, e);
//		判断装载因子是否超过某个值
		if(loadSize(count)){
			// 把数组对象的长度扩充到原来的2倍。
			Resize(2*length);
		}
	}

	private void Resize(int length) {
		// TODO Auto-generated method stub
		LinkNode[] newLnode = new LinkNode[2*length];
		for(int i=0;i<hashArray.length;i++){
			LinkNode node = hashArray[i];
			newLnode[i] = node;
		}
		this.length = length;
		hashArray =null;
		hashArray = newLnode;
	}

	private boolean loadSize(int count) {
		// TODO Auto-generated method stub
		boolean value =false;
		float load = count/(float)length;
		if(load>=0.75){
//			此时装载因子超过了某一个值，需要扩充容量
			value = true;
		}
		return false;
	}
	
//	定义一个哈希函数，返回该节点在哈希表中的索引位置，计算方式是对象的hashcode的值对哈希表数组长度取余
	public int HashCode(int hashcode,int length){
		int index = hashcode%length;
		return index;
	}
}
