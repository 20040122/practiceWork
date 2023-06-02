import java.util.Arrays;

public class Practice03_boatLoad {
    public static void main(String[] args) {
        int [] w={5,6,3,7,3,8,7,3,4,9};
        int c=10;
        int s=0;
        Arrays.sort(w);
        int i=0;
        int j=w.length-1;
        while(i<=j){
            if(w[i]+w[j]<=c){
                i++;
                j--;
            }else{
                j--;
            }
            s++;
        }
        System.out.println("船的数量为："+s);
    }
}
