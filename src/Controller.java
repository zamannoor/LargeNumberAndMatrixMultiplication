import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Controller {
  
  HashMap<Integer, BigIntegers> input1 = new HashMap<Integer, BigIntegers>();
  HashMap<Integer, BigIntegers> input2 = new HashMap<Integer, BigIntegers>();
  int row;

  void takeInput() {
    try {
      String line;
      BufferedReader br = new BufferedReader(new FileReader("input.txt"));
      int i =0;
      int input1Len =0;
      int input2Len =0;
      
      while ((line = br.readLine()) != null) {  
        String[] strs = line.trim().split("\\s+");
        if (i ==0){
          row=Integer.parseInt(strs[0]);
        } else if (i ==1 ) {
          input1Len = Integer.parseInt(strs[0]);
          
          for (int j=0 ;j<input1Len; j++) {
            line = br.readLine();
            if (line != null) {
              String[] strs2 = line.trim().split("\\s+");
              int x,y;
              x = Integer.parseInt(strs2[0]);
              y = Integer.parseInt(strs2[1]);
              input1.put(x*row+y, new BigIntegers(strs2[2]));
            }
          }
          
        } else{
          input2Len = Integer.parseInt(strs[0]);
          for (int j=0 ;j<input2Len; j++) {
            line = br.readLine();
            if (line != null) {
              String[] strs2 = line.trim().split("\\s+");
              int x,y;
              x = Integer.parseInt(strs2[0]);
              y = Integer.parseInt(strs2[1]);
              input2.put(x*row+y, new BigIntegers(strs2[2]));
            }
          }
          break;
        }
        i++;
      }   
      
     
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  
  
  
  public static void main(String[] args) throws FileNotFoundException {
    Controller controller = new Controller();
    controller.takeInput();
    MatrixMultiplication mm = new MatrixMultiplication(controller, 
        controller.row, 
        controller.input1,
        controller.input2);
    
    //mm.naiveMatrixMultiplicationWithSchool();
    
    // 1 means naive
    // 2 means gauss
    // 3 means karatsuba
    
    mm.matrixMultStrassen(1);
    mm.matrixMultStrassen(2);
    mm.matrixMultStrassen(3);
    System.out.println("Done!");
  }
}
