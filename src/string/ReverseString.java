package string;
/***
 * 算法要求：原地逆序一个字符串。
 * @author bike
 *
 */
public class ReverseString {

	/**
	 * @param args
	 */
	/***
	 * 方法1 ：设置两个指针，分别指向字符串的头部和尾部，依次交换两个指针位置所指的元素
	 * @param s
	 * @return
	 * 利用StringBuffer
	 */
	public String reverse(String s){
		int len = s.length();
		if(len==0)
			return s;
		StringBuffer bf = new StringBuffer(s);
		int left = 0;
		int right = len-1;
		while(right>left){
			char tmp = bf.charAt(left);
			bf.setCharAt(left, bf.charAt(right));
			bf.setCharAt(right, tmp);
			left++;
			right--;
		}
		return bf.toString();
		
	}
	/***
	 * 利用字符数组
	 * @param s
	 * @return
	 */
	public String reverse2(String s){
		int len = s.length();
		if(len==0)
			return s;
		int left = 0;
		int right = len-1;
		char num[] = s.toCharArray();
		while(right>left){
			char tmp = num[left];
			num[left] = num[right];
			num[right] = tmp;
			left++;
			right--;
		}
//		注意如何将char数组转换成字符串
		return String.valueOf(num);
		
	}
	/***
	 * 递归逆序
	 * @param args
	 */
	public String reverse3(String s){
		int len = s.length();
		if(len==0)
			return s;
		StringBuffer bf = new StringBuffer(s);
		return getReverse(bf,0,len-1);
	}
	private String getReverse(StringBuffer bf, int left, int right) {
		// TODO Auto-generated method stub
		if(left>=right){
			return bf.toString();
		}
		char tmp = bf.charAt(left);
		bf.setCharAt(left, bf.charAt(right));
		bf.setCharAt(right, tmp);
		return getReverse(bf, left+1, right-1);
	}
	/**
	 * 不允许使用临时变量的原地逆序，采取异或的方法,
	 * 使用异或操作可以不必借助临时变量，交换两个整型或者字符型的值
	 * 交换 int a =5,b=6;
	 * a = a^b;(两个元素一个为1一个为0的位置1了）
	 * b = a^b;
	 * a = a^b;
	 * @param args
	 */
	public String reverse4(String s){
		int len = s.length();
		if(len==0)
			return s;
		StringBuffer bf = new StringBuffer(s);
		int left = 0;
		int right = len-1;
		while(right>left){
			bf.setCharAt(left, (char) (bf.charAt(left)^bf.charAt(right)));
			bf.setCharAt(right, (char) (bf.charAt(left)^bf.charAt(right)));
			bf.setCharAt(left, (char) (bf.charAt(left)^bf.charAt(right)));
			left++;
			right--;
		}
		return bf.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReverseString r = new ReverseString();
		System.out.println(r.reverse4("you are a dog"));
		System.out.println(r.reverseWords2("you are a dog"));
	}
	/***
	 * 扩展题：按照单词逆序，比如给定"This is a sentence"，则输出是"sentence a is This"
	 * 
	 */
	/***
	 * 方法1 ：直接利用库函数直接把字符串逆序，substring(k,i),
	 * 而且这里利用了一个辅助字符串result
	 */
//	时间复杂度应该是0（n）
	public  String reverseWords(String s){
		String result = "";
		int len = s.length();
		int i=0;
		int j=len-1;
//		这样从前面找到了第一个不是空格的位置 i
		while(i<len&&s.charAt(i)==' '){
			i++;
		}
//		这样从后面找到了第一个不是空格的位置j
		while(j>=0&&s.charAt(j)==' '){
			j--;
		}
		int k;
		while(i<=j){
//			这里把不是不含空格的一个单词存储起来
//			String substring(begin,end);包含头，不包含尾
			k=i;
			while(i<=j&&s.charAt(i)!=' ')
				i++;
//			这里是保证一个单词时后面没有空格跟着
			if(result.length()==0)
			{
				result = s.substring(k,i);
			}
			else
				result = s.substring(k,i)+" "+result;
//		这里是把句子中间多余的空格屏蔽掉
			while(i<=j&&s.charAt(i)==' ')
				i++;
		}
		if(result.isEmpty()){
			s="";
			return s;
		}
		return result;
	}
	/***
	 * 方法2：利用逆序来求解：
	 * 分2步：利用 this is a dog
	 * （1）先把每一个单词逆序
	 * siht si a god
	 * （2）再把整个的句子逆序
	 * dog a is this
	 */
	public  String reverseWords2(String s){
		int len = s.length();
		
		StringBuffer bf = new StringBuffer(s);
		int i =0;
		while(i<len){
			int k = i;
			while(i<len&&bf.charAt(i)!=' '){
				i++;
			}
			int left = k,right = i-1;
			while(left<right){
				char tmp = bf.charAt(left);
				bf.setCharAt(left, bf.charAt(right));
				bf.setCharAt(right, tmp);
				left++;
				right--;
			}
			i++;
		}
//		再把整个string进行逆序
		int sleft = 0,sright = len-1;
		while(sleft<sright){
			char tmp = bf.charAt(sleft);
			bf.setCharAt(sleft, bf.charAt(sright));
			bf.setCharAt(sright, tmp);
			sleft++;
			sright--;
		}
		return bf.toString();
		
	}
}
