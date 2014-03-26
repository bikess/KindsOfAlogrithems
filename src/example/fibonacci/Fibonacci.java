package example.fibonacci;
/**
 * 这里介绍斐波那契数列：1,1,2,3,5....f(n)=f(n-1)+f(n-2)
 * 的几种解法
 * 解法1：采用递归的方式，时间复杂度为指数阶0（3/2^n) ，由于在递归的过程中存在大量的重复计算
 * 解法2：采用自底向上的计算方式，暂存已经求得的结果，时间复杂度为0（n）
 * 解法3：时间复杂度为o（logn）
 * 思路：
 * {f(n+1), f(n), f(n), f(n-1)}表示一个矩阵。在矩阵中第一行第一列是f(n+1)，
 * 第一行第二列是f(n)，第二行第一列是f(n)，第二行第二列是f(n-1)。
 * 有了这个公式，要求得f(n)，我们只需要求得矩阵{1, 1, 1,0}的n-1次方，
 * 因为矩阵{1, 1, 1,0}的n-1次方的结果的第一行第一列就是f(n)。
 * 这个数学公式用数学归纳法不难证明。然后采取分治的方法，a^n= a^n/2*a^n/2(n为偶数）
 * a^n=a*a^n-1/2*a^n-1/2
 * @author bike
 *
 */
// 定义一个2行2列的矩阵的数据结构
class MatrixBy{
	long m00 =0;
	long m01 =0;
	long m10 =0;
	long m11 =0;
	public MatrixBy(long m00, long m01, long m10, long m11) {
		this.m00 = m00;
		this.m01 = m01;
		this.m10 = m10;
		this.m11 = m11;
	}
	public MatrixBy() {
		// TODO Auto-generated constructor stub
	}
	
}
public class Fibonacci {

	/**
	 * @param n 
	 * @param args
	 * @return 
	 */

	public static long Fibonacci_Solution1(int n){
		int result[] = {0,1};
		if(n<2){
			return result[n];
		}
		else{
			return Fibonacci_Solution1(n-1)+Fibonacci_Solution1(n-2);
		}
		
	}
	public static long Fibonacci_Solution2(int n){
		long result = 0;
		long temp[] = {0,1}; 
		if(n<2){
			return temp[n];
		}
		for(int i=2;i<=n;i++){
			result = temp[0]+temp[1];
			temp[0] = temp[1];
			temp[1] = result;
		}
		return result;
	}
//	递归求解矩阵的乘积
	private static MatrixBy MatrixPower(int n) {
		// TODO Auto-generated method stub
		assert(n>0);
		
		MatrixBy m = null;
		if(n==1){
			m = new MatrixBy(1, 1, 1, 0);
		}else if(n%2==0){
			m = MatrixPower(n/2);
			m = MatrixMultiply(m,m);
		}else if(n%2==1){
			m = MatrixPower((n-1)/2);
			m = MatrixMultiply(m, m);
			m = MatrixMultiply(m, new MatrixBy(1, 1, 1, 0));
		}
		return m;
	}
	private static MatrixBy MatrixMultiply(MatrixBy m1, MatrixBy m2) {
		// TODO Auto-generated method stub
		MatrixBy m = new MatrixBy();
		m.m00 = m1.m00*m2.m00+m1.m01*m2.m10;
		m.m01 = m1.m00*m2.m01+m1.m01*m2.m11;
		m.m10 = m1.m10*m2.m00+m1.m11*m2.m10;
		m.m11 = m1.m10*m2.m01+m1.m11*m2.m11;
		return m;
	}
	public static long Fibonacci_Solution3(int n){
		int result[] = {0,1};
		if(n<2){
			return result[n];
		}
		MatrixBy p = MatrixPower(n-1);
		return p.m00;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 5;
		System.out.println(Fibonacci_Solution1(num));
		System.out.println(Fibonacci_Solution2(num));
		System.out.println(Fibonacci_Solution3(num));
	}

}
