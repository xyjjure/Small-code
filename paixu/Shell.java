package paixu;

public class Shell {
	private Swap s = new Swap();
	public Shell(int[] a,int n){
		int d=n/2;
		while(true){
			for(int i=0;i<d;i++){
				for(int j=i;j+d<a.length;j+=d){
					if(a[j]>a[j+d]){
						s.swap(a, j, j+d);
					}
				}
			}
			if(d==1) break;
			d--;
		}
	}

}
