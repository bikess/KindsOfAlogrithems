package example.modelmatch;

/*
 * 模式匹配，若某个字符串S从第pos个位置起存在和串T相同的子串，则称字符串模式匹配成功
 */
public class NormalAlgorithm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "ABCDABCDE";
		String t = "ABCDE";
//		char[] s = {'A','B','C','D','A','B','C','D','E'};
//		char[] t = {'A','B','C','D','E'};
		int pos = 0;
		int result = Index_BF(s,t,pos);
		System.out.println("能够开始匹配的位置是"+result);
	}
// 利用普通的算法进行模式匹配的, 注意java字符串不是以'\0‘结尾，是以个数判断结尾的
	private static int Index_BF(String s, String t, int pos) {
		// TODO Auto-generated method stub
		char [] match_A = s.toCharArray();
		char [] match_B = t.toCharArray();
		int i =pos,j=0;
		while(i+j!=9&&j!=5){
			if(match_A[i+j]==match_B[j]){
				j++;//当前为的字符匹配成功，继续下一个
			}
			else{
				i++;j=0;//从当前的i位置匹配失败，重新开始下一轮
			}
		}
		if(j == 5)
			return i;
		else{
			return -1;
		}
	}
}
//	// 利用普通的算法进行模式匹配的
//		private static int Index_BF(char[] s, char[] t, int pos) {
//			// TODO Auto-generated method stub
//			int i =pos,j=0;
//			while(s[i+j]!='\0'&&t[j]!='\0'){
//				if(s[i+j]==t[j]){
//					j++;//当前为的字符匹配成功，继续下一个
//				}
//				else{
//					i++;j=0;//从当前的i位置匹配失败，重新开始下一轮
//				}
//			}
//			if(t[j] =='\0')
//				return i;
//			else{
//				return -1;
//			}
//		}
//}
