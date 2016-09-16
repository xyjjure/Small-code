package xyj;
import java.util.*;
public class MaxSubArray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] num = new int[n];
		for(int i =0;i<n;i++)
			num[i] = sc.nextInt();
		System.out.println(maxsubarray(num));
		System.out.println(maxtwosubarray(num));

	}
	public static int maxsubarray(int[] array){
		int current = array[0];
		int max = array[0];
		for(int i = 1;i<array.length;i++){
			current = (current>=0)?current+array[i] : array[i];
			max = (max>current)?max : current;
		}
		return max;
	}
	public static int maxtwosubarray(int[] array){
		int[] right = new int[array.length];
		right[0] = array[array.length-1];
		int cur = array[array.length-1];
		for(int i = array.length-2;i>=0;i--){
			cur = (cur>=0)?cur+array[i] : array[i];
			right[i] = (right[i]>cur)?right[i] : cur;
		}
		int left = array[0];
		int result = left + right[1];
		cur = array[0];
		for(int i = 1;i<array.length-1;i++){
			cur = (cur>=0)?cur+array[i] : array[i];
			left = (left>cur)?left : cur;
			result = (result>left+right[i+1])? result : left+right[i+1];
		}
		return result ;
	}

}
