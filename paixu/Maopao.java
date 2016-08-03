package paixu;

public class Maopao {
     private int[] a ={40,2,1,43,3,65,0,-1,58,3,42,4};
     private Swap s = new Swap();
     public Maopao(){
    	 for(int i=0;i<a.length;i++){
    		 for(int j=1;j<a.length-i;j++){
    			 if(a[j]<a[j-1])
    				 s.swap(a, j, j-1);
    				 
    		 }
    	 }
    	 for(int i=0;i<a.length;i++)
    		 System.out.print(a[i]+" ");
     }
		

}
