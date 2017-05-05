import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Controller {
  
  Map<Integer, BigIntegers> input = new HashMap<Integer, BigIntegers>();
  Map<Integer, BigIntegers> output = new HashMap<Integer, BigIntegers>();
  int row, col;

  void takeInput() {
    try {
      String line;
      BufferedReader br = new BufferedReader(new FileReader("input.txt"));
      int i =0;
     
      while ((line = br.readLine()) != null) {  
        String[] strs = line.trim().split("\\s+");
        if (i ==0){
          row=Integer.parseInt(strs[0]);
          col =Integer.parseInt(strs[1]);
        } else if (strs.length == 3){
          int x,y;
          x = Integer.parseInt(strs[0]);
          y = Integer.parseInt(strs[1]);
         input.put(x*col+y, new BigIntegers(strs[2]));
        }
        i++;
      }   
      
     
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
  
  
  
  
  public static void main(String[] args) throws IOException {
    
    
    Controller controller = new Controller();
    controller.takeInput();
    
    MatrixMultiplication mm = new MatrixMultiplication(controller, 
        controller.row, 
        controller.col, 
        controller.input,
        controller.output);

    mm.matrixMultiplyByStrassen();
    mm.traditionalMatrixMultiplication();
    

    UnitTestBigInteger unitTest = new UnitTestBigInteger();
    unitTest.unitTestAddition();
    unitTest.unitTestSubtraction();
    unitTest.unitTestMultiplication();
    
  }
}
