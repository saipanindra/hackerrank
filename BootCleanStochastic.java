import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BootCleanStochastic {
/* Head ends here */
	
	
    static void nextMove(int posx, int posy, String[] board) throws IOException{
    	File f = new File("sample.txt");
    	f.createNewFile();
        int dx=0,dy=0;
        if(doSearchForDirt(f)){
           for(int i = 0 ; i < board.length ; i++)
           {
               for(int j = 0 ; j < board[0].length() ; j++){
                   if(board[i].charAt(j) == 'd')
                   {
                     dx = i;
                     dy = j;
                     updateDirtFile("NOTFOUND",dx,dy,f);
                   }
               }
          }
        }
        else
        {
            String coord = getDirtCoord(f);
            dx = Integer.parseInt(coord.split(" ")[0]);
            dy = Integer.parseInt(coord.split(" ")[1]);
        }
        if(dx == posx && dy == posy)
        {
            System.out.println("CLEAN");
            updateDirtFile("FOUND",dx,dy,f);
            return;
        }
        if(dx > posx)
        {
          System.out.println("DOWN");
          return;
        }
        if(dx < posx)
        {
          System.out.println("UP");
          return;
        }
        if(dy < posy)
        {
          System.out.println("LEFT");
          return;
        }
        if(dy > posy)
        {
          System.out.println("RIGHT");
          return;
        }
        
    }
        
        
        
    public static String getDirtCoord(File f) throws FileNotFoundException,IOException
    {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String status = br.readLine();
        String coord = br.readLine();
        br.close();
        return coord;
        
        
    }
    public static boolean doSearchForDirt(File f) throws FileNotFoundException,IOException
    {
    
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String status = br.readLine();
        if(status == null) return true;
        String coord = br.readLine();
        br.close();
        if(status.equals("FOUND"))
            return true;
        return false;
    }
    public static void updateDirtFile(String status, int posx, int posy, File f) throws IOException
    {
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(status);
        bw.newLine();
        bw.write(posx+" "+posy);
        bw.close();
    }
/* Tail starts here */
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int [] pos = new int[2];
        String board[] = new String[5];
        for(int i=0;i<2;i++) pos[i] = in.nextInt();
        for(int i=0;i<5;i++) board[i] = in.next();
        nextMove(pos[0], pos[1], board);
    }
}
