package example.luodan;
/**
 * 问题描述： 有2n+1个无序整数，其中有2n个数两两配对，比如1 3 2 1 3，设计算法找那个孤立的数。
 * 笨方法有很多，例如 ：设置辅助数组，然后把数依次与辅助数组中数比较，若存在，删除这个数，若
 * 不存在，则插入；最后辅助数组剩余的数便是落单的数
 * 时间复杂度n*n，空间复杂度n
 * 若采取时间复杂度n，空间复杂度1的方法，便是利用原理：
 * 一个数异或本身等于0，异或0等于本身
 * 其他类似 一个数与上本身还是本身，一个数与上0等于0等等，一个数与上1可以判断最低位是否为1，进而可以
 * 判断是否是偶数
 * @author bike
 *
 */
public class LuoDan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] str = {1,2,3,2,1};
		int single = 0;
		for(int i=0;i<str.length;i++){
			single = single^str[i];
		}
		System.out.println(single);
		/**
		 * 若这里改为有2*n+2个数，也就是有2个数落单，如何最短时间内找到2个数
		 * 
		 * 将2n+2个数异或起来就会得到c=a xor b，并且c不等于0。因此在c的二进制位中找到一个为1的位，
		 * 可推断在这位上a和b分别为0和1，因此将2n+2个数分为该位位0的组和该位为1的组，两组中各自会
		 * 包含2n’+1个数和2n’’+1个数，用初阶的算法即可解决。
		 */
		/**
		 * 具体做法：得到所有数异或后的值，将这个值循环向有移位与1进行与，就可以判断哪个位
		 * 上，这两个单数的值不一样，然后以这个为将所有数划为2部分，再分别异或
		 */
		
		int[] strB = {1,2,3,4,2,1,6,4};
		int r = 0;
		for(int i=0;i<strB.length;i++){
			r = r^strB[i];
		}
//		判断r哪个位置的位为1
		int p = 0;
		if((r&1)==1){
			p=0;
		}
		if(((r>>1)&1)==1){
			p++;
		}
//		以p位置为1这个条件对所有数字划分分别异或
		int a=0,b=0;
		for(int i=0;i<strB.length;i++){
			if(((strB[i]>>p)&1)==0){
				a = a^strB[i];
			}
			else{
				b = b^strB[i];
			}
		}
		
		System.out.println(a+"\t"+b);
	}

}
