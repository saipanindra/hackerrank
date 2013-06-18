import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
class DirtyLoc
{
	int x,y;
	DirtyLoc(int _x, int _y){
		x = _x;
		y = _y;
	}
}

class VisitedNode
{
	int x,y;
	VisitedNode(int _x,int _y){
		x = _x;
		y = _y;
		
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof VisitedNode)
			return ((VisitedNode) obj).x == x && ((VisitedNode) obj).y == y;
		return false;
	}
	
}

public class BotCleanPartial {
	
	public static ArrayList<VisitedNode> vList  = new ArrayList<VisitedNode>();
/* Head ends here */
    static void next_move(int posx, int posy, String[] board) throws IOException{
		ArrayList<DirtyLoc> dList = new ArrayList<DirtyLoc>();
        VisitedNode vnode = new VisitedNode(posx,posy);
        vList.add(vnode);  
        File f = new File("sample.txt");
        writeToFile(posx,posy,f);
    	if(board[posx].charAt(posy) == 'd') dList.add(new DirtyLoc(posx,posy));
    	if(posy + 1 < 5){
    		if(board[posx].charAt(posy+1) == 'd') dList.add(new DirtyLoc(posx,posy+1));
    		if(posx + 1 < 5){
    			if(board[posx+1].charAt(posy+1) == 'd') dList.add(new DirtyLoc(posx+1,posy+1));
    			if(board[posx+1].charAt(posy) == 'd') dList.add(new DirtyLoc(posx+1,posy));
    		}
    		if(posx - 1 >= 0){
    			if(board[posx-1].charAt(posy+1) == 'd') dList.add(new DirtyLoc(posx-1,posy+1));
    			if(board[posx-1].charAt(posy) == 'd') dList.add(new DirtyLoc(posx-1,posy));
    		}
    	}
    	if(posy - 1 >= 0){
    		if(board[posx].charAt(posy-1) == 'd') dList.add(new DirtyLoc(posx,posy-1));
    		if(posx + 1 < 5){
    			if(board[posx+1].charAt(posy-1) == 'd') dList.add(new DirtyLoc(posx+1,posy-1));
    			if(board[posx+1].charAt(posy) == 'd') dList.add(new DirtyLoc(posx+1,posy));
    		}
    		if(posx - 1 > 0){
    			if(board[posx-1].charAt(posy-1) == 'd') dList.add(new DirtyLoc(posx-1,posy-1));
    			if(board[posx-1].charAt(posy) == 'd') dList.add(new DirtyLoc(posx-1,posy));
    		}
    	}
    	
      
      
      int minIndex = -1;
      int minDistance = Integer.MAX_VALUE;
      for(int i = 0 ; i < dList.size() ; i++)
      {
    	  int xdist = Math.abs(dList.get(i).x - posx);
    	  int ydist = Math.abs(dList.get(i).y - posy);
    	  int dist = xdist+ydist;
    	  if(dist < minDistance)
    	  {
    		  minDistance = dist;
    		  minIndex = i;
    	  }
      }
      
      
      if(minIndex == -1)
      {
    	  String[] moveList = {"LEFT","RIGHT","UP","DOWN"};
    	  
    	  for(String move:moveList)
    	  {
    		  switch(move)
    		  {
    		  case "LEFT" :
    			vnode = new VisitedNode(posx,posy-1);
    			  if(posy-1 >= 0 && !isVisited(posx,posy-1,f))
    			  {
    				  System.out.println("LEFT");
    				  return;
    			  }
    		  
    	      case "RIGHT" : 
    	      vnode = new VisitedNode(posx,posy+1);
    	      if(posy+1 < board.length && !isVisited(posx,posy+1,f))
			  {
				  System.out.println("RIGHT");
				  return;
			  }
    	      case "TOP" : 
    	      vnode = new VisitedNode(posx-1,posy);	  
    		  if(posx-1 >= 0 && !isVisited(posx-1,posy,f))
    		  {
    				  System.out.println("TOP");
    				  return;
    		  }
    	      case "DOWN" : 
    	      vnode = new VisitedNode(posx+1,posy);
        	  if(posx+1 < board.length && !isVisited(posx+1,posy,f))
        	  {
        			  System.out.println("DOWN");
        			  return;
        	  }
		  }
    	  }
    			  
      }
      else
      {
      DirtyLoc minDirtyLoc = dList.get(minIndex);
      if(minDirtyLoc.y > posy && !isVisited(posx,posy+1,f))
      {
    	  System.out.println("RIGHT");
    	  
      }
      else if(minDirtyLoc.y < posy && !isVisited(posx,posy-1,f))
      {
    	  System.out.println("LEFT");
    	  
      }
      else if(minDirtyLoc.x > posx && !isVisited(posx+1,posy,f))
      {
    	  System.out.println("DOWN");
    	  
      }
      else if(minDirtyLoc.x < posx && !isVisited(posx-1,posy,f))
      {
    	  System.out.println("UP");
    	  
      }
      else if(minDirtyLoc.x == posx && minDirtyLoc.y == posy)
      {
    	  System.out.println("CLEAN");
    	  dList.remove(minIndex);
      }
    	
      }
        
    }
    
    public static void writeToFile(int posx,int posy,File f) throws IOException{
    
      FileWriter fw = new FileWriter(f,true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(posx+" "+posy);
      bw.newLine();
      bw.close();
       
    }
    
    public static boolean isVisited(int posx,int posy,File f) throws FileNotFoundException,IOException{
    
        String s = posx+" "+posy;
        BufferedReader br = new BufferedReader(new FileReader(f));
        String currentLine;
        while((currentLine = br.readLine()) != null)
        {
            if(currentLine.equals(s))
                return true;
        
        
        }
        return false;
    
    }

 
/* Tail starts here */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int [] pos = new int[2];
        String board[] = new String[5];
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        for(int i=0;i<5;i++) board[i] = in.next();
        next_move(pos[0], pos[1], board);
    }
}

