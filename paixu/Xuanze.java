package paixu;

public class Xuanze {
	private int[] a ={40,2,1,43,3,65,0,-1,58,3,42,4};
    private Swap s = new Swap();
    public Xuanze(){
    	int max;
    	for(int i=0;i<a.length;i++){
    		max=0;
    		for(int j =1;j<a.length-i;j++){
    			if(a[max]<a[j]) max=j;
    		}
    		s.swap(a, max, a.length-1-i);
    	}
    	for(int i=0;i<a.length;i++){
    		System.out.print(a[i]+" ");
    	}
   
    }

}
