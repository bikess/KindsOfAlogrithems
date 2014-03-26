package tecent;
/**
 * 
 * @author bike
 * 判断一个数是否为质数。普通方法是用数除以2到根号n之间的数，有一个能整除则不是质数
 * 
 * 当判断1到10000之间的所有质数的时候，必须要用一种时间复杂度低的算法，本文就是这种求解方法
 * 方法是 从2 开始一直遍历到根号10000，是否画掉，没有划掉的话把其所有的倍数对应的值划掉
 */
public class ZhiShuN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int end = 100;
		
		ZhiShuSolution(end);
	}

	private static void ZhiShuSolution(int end) {
		// TODO Auto-generated method stub
//		java boolean 默认值为false
		Boolean a[] = new Boolean[end];
		for(int i =0;i<end;i++)
			a[i] = false;
		a[0]=true;
		a[1]=true;
		for(int i=2;i<Math.sqrt(end);i++){
			if(a[i]!=true){
				int beishu = i;
				int tmp = i*beishu;
				while(tmp<end){
					if(a[tmp]!=true){
						a[tmp] = true;
					}
					beishu++;
					tmp = i*beishu;
				}
			}
		}
		
//		输出所有质数
		for(int i =1;i<end;i++){
			if(a[i]!=true){
				System.out.print(i+"\t ");
			}
		}
	}

}
