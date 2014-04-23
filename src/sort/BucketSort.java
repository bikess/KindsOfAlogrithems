package sort;

import java.util.Collections;

class Bucket{
	int count = 0;;
	int node[] = new int[10];
}
public class BucketSort {

	/**
	 * @param args
	 */
	public void quickSort(int data[],int size){
		if(size<=0){
			return;
		}
		quick_Sort(data, 0,size-1);
	}
	private void quick_Sort(int[] data, int left, int right) {
		// TODO Auto-generated method stub
		if(left<right){
			int i = left,j = right;
			int temp = data[left];
			while(i<j){
				while(data[j]>=temp&&i<j){
					j--;
				}
				if(i<j){
					data[i] = data[j];
					i++;
				}
				while(data[i]<=temp&&i<j){
					i++;
				}
				if(i<j){
					data[j] = data[i];
					j--;
				}
			}
			data[i] = temp;
			quick_Sort(data, left, i-1);
			quick_Sort(data, i+1, right);
		}
	}
	public void bucket_sort(int data[],int size){
		int max ,min,num;
		
		max = min = data[0];
		for(int i=1;i<size;i++){
			if(data[i]>max){
				max = data[i];
			}else if(data[i]<min){
				min = data[i];
			}
		}
//		需要建立桶的数量
		num = (max - min+1)/10+1;
		Bucket bucket[] = new Bucket[num];
		
//		将数据映射到每一个桶中
		for(int i=0;i<size;i++){
			int k = (data[i]-min+1)/10;
			bucket[k].node[bucket[k].count] = data[i];
			bucket[k].count++;
		}
//		对每个桶里的数据利用快速排序进行排序
		int pos = 0;
		for(int i =0;i<num;i++){
			quickSort(bucket[i].node, bucket[i].count);
			
			for(int j=0;j<bucket[i].count;j++){
				data[pos] = bucket[i].node[j];
				pos++;
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
