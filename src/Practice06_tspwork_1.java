import java.util.Scanner;
public class Practice06_tspwork_1 {
    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     System.out.println("请输入城市的数量:");
     int N=sc.nextInt();
     System.out.println("请输入城市之间的距离矩阵:");
     int[][] dist=new int[N][N];
     for(int i=0;i<N;i++){
         for(int j=0;j<N;j++){
             dist[i][j]=sc.nextInt();
         }
     }
     int M=1<<(N-1);
     int[][]dp=new int[N][M];
     for(int i=0;i<N;i++){
         dp[i][0]=dist[i][0];
     }
     for(int j=1;j<M;j++){
         for (int i=0;i<N;i++){
             dp[i][j]=Integer.MAX_VALUE;
             if((j>>(i-1)&1)==1)
                 continue;
             for(int k=1;k<N;k++){
                 if((j>>(k-1)&1)==0)
                     continue;
                 dp[i][j]=Math.min(dp[i][j],dist[i][k]+dp[k][j^(1<<(k-1))]);
             }
         }
     }
        System.out.println("路径花费最小为："+dp[0][M-1]);
    }
}
