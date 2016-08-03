package paixu;

public class Mergesort {
	public void merge(int[] a,int[] temp,int first,int last,int mid){
		int i=first,j=mid+1,k=0;
		
		while(i<=mid&&j<=last){
			if(a[i]<a[j]){
				temp[k++]=a[i++];
			}else{
				temp[k++]=a[j++];
			}
		}
		while(i<=mid){
			temp[k++]=a[i++];
		}
		while(j<=last){
			temp[k++]=a[j++];
		}
		for(i=0;i<k;i++){
			a[first+i]=temp[i];
		}
	}
	public void mergeall(int[] a,int first,int last,int[] temp){
		if(first<last){
			int mid = (first+last)/2;
			mergeall(a,first,mid,temp);
			mergeall(a,mid+1,last,temp);
			merge(a,temp,first,last,mid);
		}
	}

}
