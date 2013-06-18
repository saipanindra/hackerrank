import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class DirtLoc
{
  int dx,dy;
    DirtLoc(int _dx, int _dy){
      dx = _dx;
      dy = _dy;
    }
	
}
public class BotCleanLarge {
/* Head ends here */

    public static ArrayList<DirtLoc> dList = new ArrayList<DirtLoc>();
    static void next_move(int posx, int posy, int dimx, int dimy, String[] board){
    
    	for(int i = 0 ;  i < dimx ; i++){
    		
    		for(int j  = 0 ; j < dimy ; j++){
    			
    			if(board[i].charAt(j)=='d'){
    			
    				DirtLoc dLoc = new DirtLoc(i,j);
    				
    				dList.add(dLoc);
    				
    			}
    			
    		}
    	}
    	
    	int minDist = Integer.MAX_VALUE;
    	int minIndex = -1;
    	
    	for(int k = 0 ; k < dList.size() ; k++)
    	{
    		int distx = Math.abs(dList.get(k).dx - posx);
    		int disty = Math.abs(dList.get(k).dy - posy);
    		int dist = distx + disty; 
    		
    		if(minDist > dist)
    		{
    			minDist = dist;
    			minIndex = k;
    		}
    		
    	}
    
        if(minIndex != -1)
        {
        	int dirtLocX = dList.get(minIndex).dx;
        	int dirtLocY = dList.get(minIndex).dy;
        	
        	if(posx  == dirtLocX && posy == dirtLocY)
        	{
        		System.out.println("CLEAN");
        		dList.remove(minIndex);
        		return;
        	}
        	if(posx > dirtLocX)
        	{
        		System.out.println("UP");
        		return;
        	}
        	if(posx < dirtLocX)
        	{
        		System.out.println("DOWN");
        		return;
        	}
        	if(posy > dirtLocY)
        	{
        		System.out.println("LEFT");
        		return;
        	}
        	if(posy < dirtLocY)
        	{
        		System.out.println("RIGHT");
        		return;
        	}
        		
        	
        }
    
    
    }
/* Tail starts here */
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int [] pos = new int[2];
        int [] dim = new int[2];
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        for(int i=0;i<2;i++) dim[i] = in.nextInt();
        String board[] = new String[dim[0]];
        for(int i=0;i<dim[0];i++) board[i] = in.next();
        next_move(pos[0], pos[1], dim[0], dim[1], board);
    }
}
