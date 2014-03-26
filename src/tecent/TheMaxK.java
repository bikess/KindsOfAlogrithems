package tecent;
/**
 * 
 * @author bike
 * 线性时间内求一个很大的无序数组的第k大值，要求时间复杂度为0（n）
 * 方法采用类似快速排序的分治的思想，但是只需在一侧递归即可，随机取一个数把数组分为2部分，
 * 若此数位置=k，则即为概数
 * 若次数位置<k,则在此数的右侧数组递归
 * 若次数位置>k,则在次数的左侧数组递归
 */
public class TheMaxK {

	/**
	 * @param args
	 */
	public static int TheMaxKSolution(int a[],int k,int begin,int end){
		int cur = partiton(a,begin,end);
		if(cur==k){
			return a[cur];
		}else if(cur<k){
//			此时在右半部分
			return TheMaxKSolution(a, k, cur+1, end);
		}else{
//			此时在左半部分
			return TheMaxKSolution(a, k, begin, cur-1);
		}
	}
//	此方法把数组分割成两部分，返回分割元素的位置
	private static int partiton(int[] a, int begin, int end) {
		// TODO Auto-generated method stub
		int ran = (int) (Math.random()*(end-begin)+begin);
//		与最后一个交换
		int temp;
		temp = a[ran];
		a[ran] = a[end];
		a[end] = temp;
		
		int cur = begin-1;
		for(int i =begin;i<end;i++){
			if(a[i]<=a[end]){
				cur++;
				int temp1;
				temp1 = a[i];
				a[i] = a[cur];
				a[cur] = temp1;
			}
		}
		cur++;
		temp = a[end];
		a[end] = a[cur];
		a[cur] = temp;
		
		return cur;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[]={3,2,4,7,1,8,5};
		int k =7;
		System.out.println(TheMaxKSolution(a,k-1,0,a.length-1));
	}

}
