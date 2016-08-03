package paixu;

public class Insert {
	private int[] a = {40,2,1,43,3,65,0,-1,58,3,42,4};
	public Insert(){
		for(int i=1;i<a.length;i++){
			int temp=a[i];
			int j;
			for(j=i;j>0;j--){
				if(temp<a[j-1]) {
					a[j]=a[j-1];
				}else break;
			}
			a[j]=temp;
		}
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	
	}
}
