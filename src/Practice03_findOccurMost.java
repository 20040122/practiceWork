import  java.util.Scanner;
public class Practice03_findOccurMost {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入整数的个数：");
        int n=sc.nextInt();
        int[] count=new int[1001];
        System.out.println("请输入整数：");
        for(int i=0;i<n;i++){
            ++count[sc.nextInt()];
        }
        int max=-1;
        int result=0;
        for(int i=0;i<1001;i++){
            if(count[i]>max){
                max=count[i];
                result=i;
            }
        }
        System.out.println("出现次数最多的数为："+result);
    }
}
