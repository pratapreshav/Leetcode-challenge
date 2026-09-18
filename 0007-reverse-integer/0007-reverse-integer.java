class Solution {
    public int reverse(int x) {
        
       
        int n=x;
        int revn=0;
        int d;
        while(n!=0){
            d=n%10;
            if (revn > (Integer.MAX_VALUE / 10) || revn < (Integer.MIN_VALUE / 10)) {
                return 0;
            }
            revn=revn*10+d;
            n=n/10;
        }
       return revn;
        
    }
}