package sort;
/**
 *  这是归并排序的算法
 * @author bike
 *
 */
public class GuiBingSort {

	/**
	 * @param data 
	 * @param size 
	 * @param args
	 */
	public static void GuiBingsort(int data[],int size){
		if(size<=0)
			return;
		Guibing_sort(data,0,size-1);
	}
	private static void Guibing_sort(int[] data, int left, int right) {
		// TODO Auto-generated method stub
		if(left<right){
			int mid = (left+right)/2;
			Guibing_sort(data, left, mid);
			Guibing_sort(data, mid+1, right);
			Merge_Guibing(data,left,mid,right);
		}
	}
	private static void Merge_Guibing(int[] data, int left, int mid, int right) {
		// TODO Auto-generated method stub
//		此方法将left到mid，mid到right两部分的数据进行合并
		int indexA;//扫描数组【left，mid】
		int indexB; //扫描数组【mid，right】
		indexA = left;
		indexB = mid+1;
		int temp[] = new int[right-left+1];
		int pos = 0;
		while(indexA<=mid&&indexB<=right){
			if(data[indexA]<data[indexB]){
				temp[pos++] = data[indexA];
				indexA++;
			}else{
				temp[pos++] = data[indexB];
				indexB++;
			}
		}
//		没有复制完的元素进行复制
		while(indexA<=mid){
			temp[pos++] = data[indexA];
			indexA++;
		}
		while(indexB<=right){
			temp[pos++] = data[indexB];
			indexB++;
		}
		for(int i =0;i<pos;i++){
			data[left+i] = temp[i];
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {3,7,2,4,1,5,6,0,2};
		GuiBingsort(data, data.length);
		for(int i=0;i<data.length;i++){
			System.out.println(data[i]);
		}
		
	}

}
