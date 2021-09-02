package laben;

public class Backtracing {
    public static void main(String[] args) {
        String str = "ABC";
        int n =  str.length();
        Backtracing backtracing =  new Backtracing();
        backtracing.premute(str,0,n-1);

        // backtracking uses dfs
        // dfs  start with a vertex explore the vertex nodes
        // it uses stack datastructure
        //practicing

    }
    private void premute (String str,int l,int r){
        if(l==r){
            System.out.println(str);
        }
        else {
            for(int i=l;i<=r;i++){
                str =swap(str,l,i);
                premute(str, l+1, r);
                str=swap(str,l,i);
            }
        }
    }

    private static String swap(String s ,int i,int j){
        char temp;
        char [] charArr =  s.toCharArray();
        temp =charArr[i];
        charArr[i]=charArr[j];
        charArr[j]=temp;
        return String.valueOf(charArr);
    }
}
