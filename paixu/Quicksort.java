package paixu;

public class Quicksort {
	private Swap s = new Swap();
	public int partition(int[] a,int left,int right){
		while(true){
			while(left<right&&a[left]<a[right]){
				right--;
			}
			if(left<right) s.swap(a, left++, right);
			else return left;
			while(left<right&&a[left]<a[right]){
				left++;
			}
			if(left<right) s.swap(a, left, right--);
			else return right;
		}
	}
	public void quick(int[]a,int left,int right){
		int pivot;
		if(left>=right) return;
		pivot = partition(a,left,right);
		quick(a,left,pivot-1);
		quick(a,pivot+1,right);
  	
	}

}
