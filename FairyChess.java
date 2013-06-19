import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class PosXY
{
	int x, y;
	PosXY(int _x, int _y){
		
		x = _x;
		y = _y;
	}
}

public class FairyChess {

	public static void main(String[] args) {

		Scanner sc  = new Scanner(System.in);
		int times,N,M,S;
		int[] input = new int[3];
		times = sc.nextInt();
		for(int t = 0 ; t < times ; t++){
			for(int i = 0 ; i < 3 ; i++)
			{
				input[i] = sc.nextInt();
			}

			N = input[0];
			M = input[1];
			S = input[2];

			String[] board = new String[N];
			for(int i = 0 ; i < N ; i++)
			{
				board[i] = sc.next();
			}
		
			long[][] m0 = new long[N][N];
			int posx=-1 , posy=-1;
			for(int i = 0 ; i < N ; i++)
				for(int j = 0 ; j < N ; j++)
				{
					m0[i][j] = 0;
					if(board[i].charAt(j) == 'L')
					{
						m0[i][j] = 1;
						posx = i;
						posy = j;
					}
					if(board[i].charAt(j) == 'P')
					{
						m0[i][j] = -1;
					}
						
						
				}
		
			long[][] m = m0;
			for(int i = 1 ; i <= M ; i++)
			{
				m = getNextMoveMatrix(m,S);
				//printMatrix(m,i);
			}
			long sum = 0;
			for(int i = 0 ; i < N ; i++)
			{
				for(int j = 0 ; j < N ;j++)
				{
					if(m[i][j] > 0)
					{
						sum += m[i][j];
					}
				}
			}
			System.out.println(sum%1000000007);
		}
					
			
			
		}
	
	public static void printMatrix(int[][] m, int i )
	{
		System.out.println("m"+i);
		for(int k = 0 ; k < m.length ;k++)
		{
			for(int j =0 ; j < m[k].length ; j++)
				
			{
				System.out.print(m[k][j] + " ");
			}
			
			System.out.println();
		}
	}
		
	public static long[][] getNextMoveMatrix(long[][] prevMat, int S)
	{

		ArrayList<PosXY> posList = new ArrayList<PosXY>();
		long[][] prevMatCopy = new long[prevMat.length][prevMat.length];
		for(int i = 0 ; i < prevMat.length ; i++)
		{
		   prevMatCopy[i] = Arrays.copyOf(prevMat[i],prevMat[i].length);
		   
		}
		for(int i = 0 ; i < prevMat.length ; i++)
		{
			for(int j = 0 ; j < prevMat[i].length ; j++)
			{
				
				if(prevMat[i][j] > 0)
				{
					posList.add(new PosXY(i,j));
				}
			}
		}
		
		for(int i = 0 ; i < posList.size() ; i++)
		{
			for(int m = 0 ; m < prevMat.length ; m++)
			{
				for(int n = 0 ; n < prevMat.length ; n++)
					
				{
					if( !(m == posList.get(i).x && n == posList.get(i).y))
					{
						
						if(prevMat[m][n] != -1)
						{
							
							int dist = Math.abs(m - posList.get(i).x) + Math.abs(n - posList.get(i).y);
							if(dist  <= S)
							{
								long a = prevMat[posList.get(i).x][posList.get(i).y];
								prevMatCopy[m][n] = (prevMatCopy[m][n] +  a)%1000000007;
								
							}
								
								
							
						}
							
					}
					
				}
			}
		}
		
		return prevMatCopy;
		
		
	}


		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	}
