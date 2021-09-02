package laben;

public class EksmaensOppgaven {
    public static void main(String[] args) {

    }

//
    private static int sum (int key, int [] array, int lo,int hi){
        if(lo<=hi){
            int mid =  lo +(hi-lo) /2;
            if(key>array[mid]){
                return sum(key,array,mid+1,hi);
            }
            else{
                return sum(key,array,lo,mid-1);
            }
        }
        return lo;
    }
}
