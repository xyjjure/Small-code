package xyj;
import java.util.*;
public class KMP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			char[] str1 = sc.nextLine().toCharArray();
			char[] str2 = sc.nextLine().toCharArray();
			int index = maincourse(str1,str2);
			System.out.println(index);
		}

	}
	public static int maincourse (char[] str1,char[] str2){
		if(str2.length >str1.length) return -1;
		int pos1 = 0;
		int pos2 = 0;
		int[] next = getNextArray(str2);
		while(pos1<str1.length && pos2<str2.length){
			if(pos2==-1||str1[pos1]==str2[pos2]){
				pos1++;
				pos2++;
			}else{
				pos2 = next[pos2];
			}
		}
		if(pos2<str2.length) return -1;
		else return pos1-str2.length ;
		
		
	}
	public static int[] getNextArray (char[] str2){
		if(str2.length ==1) 
			return new int[]{-1};
		int[] next = new int[str2.length ];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int pm = 0;
		while(pos<next.length){
			if(str2[pos-1]==str2[pm]){
				next[pos++] = ++pm;
			}else if (pm > 0)
				pm = next[pm];
			else next[pos++] = 0;
		}
		return next;
	}

}
