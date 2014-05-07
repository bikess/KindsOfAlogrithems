package problems;
/**
 * //计算连续和为n的所有子序列
 * 对于一个32位整数，输出它所有可能的连续自然数之和的算式，
 * 要求是这些连续自然数之和要等于原数。例如3 = 2+1; 9 = 4+5，9 = 2+3+4等
 * @author bike
 *
 */
public class ZuiDaLianXuZiRanShu {

	/**
	 * 思路：采取双指针的方法，两个指针都指向头部，开始i=j=1
	 * 
	 * 判断i+j与sum比较
	 * （1)若sum=i+...j,则i++，j++
	 * (2)若sum<i+...j,则i++；
	 * （3）若sum>i+....j,则j++；
	 */
	/**
	 * @param args
	 */
	public void ZuiDaLinXu(int n){
//		设置两个指针
		int i=1;
		int j=1;
		int sum;
		while(i<=n/2&&j<=n){
			if(i==j)
				sum = 2*i;
			else
				sum = (i+j)*(j-i+1)/2;
			if(sum==n){
				for(int k=i;k<=j;k++){
					System.out.print(k+"\t");
				}
				System.out.println("");
				i++;
				j++;
			}
			if(sum<n){
				j++;
			}else{
				i++;
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZuiDaLianXuZiRanShu m = new ZuiDaLianXuZiRanShu();
		m.ZuiDaLinXu(5);
	}

}
