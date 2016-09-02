package xyj;
import java.util.*;
public class Huawei {

	public static void main(String[] args) {
	// 第二题
		Scanner sc = new Scanner(System.in);
		String ss = sc.nextLine();
		String s = sc.nextLine();
		char a = s.charAt(0);
		int count = 0;
		char[] com = ss.toCharArray();
		for(int i = 0;i<com.length;i++){
			if(a>='A'&&a<='z') {
				if(com[i]==a||com[i]==a-32||com[i]==a+32) count++;
				continue;
			}
			if(com[i]==a) count++;
		}
		System.out.println(count);
		//第三题
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			Set<Integer> sum = new TreeSet<>();
			for(int i = 0;i<n;i++){
				sum.add(sc.nextInt());
			}
			Iterator iterator = sum.iterator();
			for(Iterator i=iterator;i.hasNext();){
				System.out.println(iterator.next());
			}
			for(Integer i:sum){
				System.out.println(i);
			}
		}   //方法2
		Scanner sc= new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			int[] sum = new int[1001];
			for(int i=0;i<n;i++){
				int j = sc.nextInt();
				sum[j] ++;
			}
			for(int i = 0;i<=1000;i++){
				if(sum[i]!=0)
					System.out.println(i);
			}
		}
		
		//第四题
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine();
		String s2 = sc.nextLine();
		if(s1!=null&&s1.length()%8==0){
			for(int i = 0;i<s1.length();i+=8){
				System.out.println(s1.substring(i, i+8));
			}
		}else if (s1 ==null||s1==""){
			System.out.println();
		}else{   
			String result =s1;
			for(int i = 0;i<8-s1.length()%8;i++)
				result += "0";
			for(int i = 0;i<result.length();i+=8){
				System.out.println(result.substring(i, i+8));
			}
		}
		if(s2!=null&&s2.length()%8==0){
			for(int i = 0;i<s2.length();i+=8){
				System.out.println(s2.substring(i, i+8));
			}
		}else if (s2 ==null||s2==""){
			System.out.println();
		}else{   
			String result =s2;
			for(int i = 0;i<8-s2.length()%8;i++)
				result += "0";
			for(int i = 0;i<result.length();i+=8){
				System.out.println(result.substring(i, i+8));
			}
		}
			
		//第五题
		Scanner sc = new Scanner (System.in);
		while (sc.hasNext()){
			String s = sc.nextLine();
			int num = Integer.parseInt(s.substring(2),16);
			System.out.println(num);
		}
		//第六题
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			long num = sc.nextLong();
			if(num == 1) System.out.println(num+" ");
			while(num!=1){
				for(int i = 2;i<=num;i++){
					if(num%i==0){
						System.out.print(i+" ");
						num/=i;
						break;
					}
				}
			}
		}
		//第七题
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			float f = sc.nextFloat();
			int a = (int)(f+0.5);
			System.out.println(a);
		}
		//第八题
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Map<Integer,Integer> m = new TreeMap<>();
		for(int i =0;i<n;i++){
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			if(m.containsKey(n1))
				m.put(n1, n2+m.get(n1));
			else m.put(n1, n2);
		}
		for(Integer i:m.keySet()){
			System.out.println(i+" "+m.get(i));
		}
		//第九题
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String s = String.valueOf(num);
		char[] c = s.toCharArray();
		for(int i=0;i<c.length;i++){
			for(int j=i+1;j<c.length;j++){
				if(c[i]==c[j]&&c[i]!=' '&&c[j]!=' ') c[i]=' ';
			}
		}
		String ss ="";
		for(int i=0;i<c.length;i++){
			if(c[c.length-1-i]!=' ')
			ss=ss+c[c.length-i-1];
		}
		System.out.println(ss.trim());
		//第十题
		Scanner sc = new Scanner(System.in);
		int sum = sc.nextInt();
		int[] num = new int[10];
	//	int l =String.valueOf(sum).length();
		for(int y =sum%10;y>=0;y=sum%10){
			if(num[y]==0){
				System.out.print(y);
				num[y]++;
				sum = (sum-y)/10;
			}
			
		}
		//第十一题
		Scanner sc = new Scanner(System.in);
		char[] c = sc.nextLine().toCharArray();
		int[] num = new int[128];
		int total =0;
		for(int i = 0;i<c.length;i++){
		     num[c[i]]++;
		}
		for(int i = 0;i<num.length;i++){
			if(num[i]!=0) total++;
		}
		System.out.println(total);
		//第十二题
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		char[] c = Integer.toString(n).toCharArray();
		String reverse = "";
		for(int i=c.length -1;i>=0;i--){
			reverse+=c[i];
		}
		System.out.println(reverse);
		//第十三题
		Scanner sc = new Scanner(System.in);
	    String s = sc.nextLine();
	    StringBuilder sb = new StringBuilder(s);
	    sb = sb.reverse();
	    System.out.println(sb);
		//第十四题
		Scanner sc = new Scanner(System.in);
		String[] word = sc.nextLine().split(" ");
		String s = "";
		for(int i = word.length-1;i>=0;i--){
			s += word[i]+(i==0?"":" ");
		}
		System.out.println(s);
		
		
		//第十五题
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[] word = new String[n];
		for(int i = 0;i<n;i++){
			word[i] = sc.next();
		}
		String temp = null;
		for(int i = 0; i<n;i++){
			for(int j=i+1;j<n;j++){
				if(word[i].compareTo(word[j])>0){
					temp = word[i];
					word[i] = word[j];
					word[j] = temp;
				}
			}
		}
		for(int i = 0 ;i<word.length;i++)
			System.out.println(word[i]);
		//第十六题
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num = 0;
		while(n!=0){
			n = (n-1) & n;
			num++;
		}
		System.out.println(num);
		
	}

}
