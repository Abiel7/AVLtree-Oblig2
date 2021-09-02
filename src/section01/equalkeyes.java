package section01;

import java.util.*;

public class equalkeyes {
    public static void main(String[] args) {
int [] a =  {1,2,3,4};  // 10
        System.out.println(sum(a,0,a.length-1));


    }
    private static int sum( int [] array, int lo,int hi){
        if(lo<hi){
            int mid = (lo+hi) /2;
            int h=  sum(array,mid+1,hi);
            int v= sum(array,lo,mid);
              return v+h;
        } else  return array[lo] ;
    }


    /*
    private static int greaterThanKey(int key, int [] a,int lo,int hi){
        if(lo<=hi){
            int mid  =  lo +(hi-lo)/2;

            if(key<a[mid]){
                return lessThanKey(key,a,lo,mid-1);
            }
            else {
                return lessThanKey(key,a,mid+1,hi);
            }
        }
        return a.length +hi -1;
    }

     */

    private static  void  reverse(int [] a){
        int n = a.length;
        for(int i = 0; i<n/2;i++){
             double temp =  a[i];
            a[i] = a[n-1-i];
            a[n-i-1] = (int) temp;
        }

    }




    // swap In premutation
    private String bytte (String s, int i, int j){
        char temp;
        char[] chars =  s.toCharArray();
        temp = chars[i];
        chars[i]=chars[j];
        chars[j]= temp;
        return String.valueOf(chars);

    }





}
