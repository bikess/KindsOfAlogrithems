package example.modelmatch;

public class KmpAlgorithm {

	static int[] nextval;
	/**
	 * @param args
	 */
//	求解匹配串的nextval数组的各个元素值
//	实际上nextval数组值求解的过程，就是对匹配串进行求解模式的过程。如abcabc则数组值应为-1,0,1，-1,0,1
//	
	public static void get_nextval(String t){
		int i =0;
		char[] match = t.toCharArray();
		nextval = new int[t.length()];
		nextval[i] = -1;
		int j = -1;
		while(i<t.length()-1){
			if(j==-1||match[i]==match[j]){
				++i;
				++j;
				if(match[i]!=match[j]){
					nextval[i]=j;
				}else{
					nextval[i]=nextval[j];
				}
			}else{
				j = nextval[j];
			}
		}
	}
//	kmp算法，用户求解匹配字符串的起始位置,kmp算法时间负责度为o（m+n），因为匹配算法是，主串与匹配串一同右移进行匹配，而当匹配不成功的时候，主串不回溯，只有
//	匹配串进行回溯，而每次回溯的步数由next[j]来确定，至少为1步，最多为j-1步，j为匹配失败的位置
	public static int kmp_search(String s ,String t, int pos){
		char[] origin = s.toCharArray();
		char[] match = t.toCharArray();
		int i = pos;
		int j=0;
		while(i<s.length()&&j<t.length()){
			if(j==-1||origin[i]==match[j]){
//				当某个字符匹配成功，或者j=-1的时候i++，j++
				i++;
				j++;
			}else{//当匹配失败的时候，i保持不变，也就是主串的位置不变，而匹配串右移1<=右移位数<=j-1个位置，也就是右移next[j]个位置，右移的位数由
//				匹配串t所决定，而与主串s无关。
				j = nextval[j];
			}
		}
		if(j>=t.length())
			return i-t.length();
		else
			return -1;	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aabcabcebafabcabceabcaefabcacdabcab";
		String t = "abac";
		get_nextval(t);
		for(int i =0;i<t.length();i++){
			System.out.print(nextval[i]+"\t");
		}
		System.out.println("\n");
		int result = kmp_search(s, t, 0);
		System.out.println("匹配开始位置为"+result);
	}

}
