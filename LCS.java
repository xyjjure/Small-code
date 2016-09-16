package xyj;

public class LCS {

	public static void main(String[] args) {
		System.out.println(findLongest("1AB2345CD",9,"12345EF",7));

	}
	public static int findLongest(String A, int n, String B, int m) {
        int[] dp = new int[n>m?n:m];
        int max = 0;
        for(int i = 0;i<n;i++){
            for(int j= m-1;j>=0;j--){
                if(A.charAt(i) == B.charAt(j)){
                    if(j == 0||i == 0)
                        dp[j] = 1;
                    else
                        dp[j] = dp[j-1] + 1;
                }else
                	dp[j] = 0;
                if(max < dp[j])
                    max = dp[j];
         
            } 
        }
    
        return max;
    }

}
