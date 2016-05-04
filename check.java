package xyj;
import java.util.*;
public class acm {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		String[] s=new String[n];
		for(int i=0;i<n;i++){
			s[i]=sc.next();
		}
		for(int i=0;i<n;i++){
			if(isMatch(s[i])){
				System.out.println("Yes");
		}else {
			System.out.println("No");
		}
	  }

	}
	public static boolean isMatch(String s){
		Stack<Character> st = new Stack<Character>();
		for(int j=0;j<s.length();j++){
			if(s.charAt(j)=='['){
				st.push(s.charAt(j));
			}
			if(s.charAt(j)==']'){
				if(!st.isEmpty()&&st.pop()=='[')
				continue;
				else return false;
			}
			if(s.charAt(j)=='('){
				st.push(s.charAt(j));
			}
			if(s.charAt(j)==')'){
				if(!st.isEmpty()&&st.pop()=='(')
				continue;
				else return false;
			}
			
		}
		if(st.isEmpty()){
			return true;
		}else {
			return false;
		}
	}

}
