package paixu;

public class Main {

	public static void main(String[] args) {
		int[] a = {40,2,1,43,3,65,0,-1,58,3,42,4,22,321,347,-22,54,5345,334,237,777};
//		Maopao m = new Maopao();
//		Xuanze x = new Xuanze();
//		Insert i = new Insert();
/*		Mergesort m = new Mergesort();
		int[] temp = new int[a.length];
		m.mergeall(a,0,11,temp);
		for(int i = 0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}*/
/*		Quicksort q = new Quicksort();
		q.quick(a, 0, 11);*/
		Shell s = new Shell(a,21);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}

	}

}
