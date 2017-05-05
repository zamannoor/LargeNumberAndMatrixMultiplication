import java.io.*;
import java.math.BigInteger;
import java.util.Map;


public class MatrixMultiplication {
  
  int row, col;
  Controller controller;
  Map<Integer, BigIntegers> input;
  Map<Integer, BigIntegers> output;
  BigIntegers zero = new BigIntegers("0");
  
  public MatrixMultiplication(Controller controller, int row, int col, Map<Integer, BigIntegers> input,
      Map<Integer, BigIntegers> output) {
    this.row = row;
    this.col = col;
    this.controller = controller;
    this.input = input;
    this.output = output;
  }

  //public MatrixMultiplication() {

  //}
  
  // ReadOnly
  BigIntegers InM(int i, int j) {
    int x=i*col+j;
    if (input.containsKey(x)) {
      return input.get(x);
    }
    
    return new BigIntegers("0");
  }

  BigIntegers InN(int i, int j) {
    int x=i*col+j;
    if (input.containsKey(x)) {
      return input.get(x);
    }

      return new BigIntegers("0");
  }
  
  // 
  BigIntegers getOutputMatrix(int i, int j) {
    int x=i*col+j;
    if (input.containsKey(x)) {
      return input.get(x);
    }

      return new BigIntegers("0");
  }
  
  void setOutputMatrix(int i, int j, BigIntegers o) {
    output.put(i*col+j,o);

  }

  void matrixAdd(){
      BigIntegers sum;
      for(int i=0; i<row; i++){
          for(int j=0; j<row; j++){
              sum = InM(i,j).add(InN(i,j));
          }
      }

  }
  
  void matrixMultiplyByStrassen() {
    // use  grade school multiplication from BigIntegers class
    // You can check the unit test part how i called big integer multiplication addition subtraction
    // write everything in gaussoutput.txt file like input file

      BigIntegers P1 = InM(0,0).MultiplyGradeSchool(InN(0,1).subtract(InN(1,1)));//P1 =a⋅(f–h)
      //System.out.println(P1);
      BigIntegers P2 = (InM(0,0).add(InM(0,1))).MultiplyGradeSchool(InN(1,1));//P2 =(a+b)⋅h
      //System.out.println(P2);
      BigIntegers P3 = (InM(1,0).add(InM(1,1))).MultiplyGradeSchool(InN(0,0));//P3 =(c+d)⋅e
      System.out.println(P3);
      BigIntegers P4 = InM(1,1).MultiplyGradeSchool(InN(1,0).subtract(InN(0,0)));// P4 =d⋅(g–e)
      System.out.println(P4);
      BigIntegers P5 = (InM(0,0).add(InM(1,1)).MultiplyGradeSchool(InN(0,0).add(InN(1,1))));//P5 =(a+d)⋅(e+h)
      System.out.println(P5);
      BigIntegers P6 = (InM(0,1).subtract(InM(1,1))).MultiplyGradeSchool((InN(1,0)).add(InN(1,1)));// P6 =(b–d)⋅(g+h)
      System.out.println(P6);
      BigIntegers P7 = (InM(0,0).subtract(InM(1,0))).MultiplyGradeSchool(InN(0,0).add(InN(0,1)));// P7 =(a–c)⋅(e+f )
      System.out.println(P7);

      BigIntegers r = P5.add(P4).subtract(P2).add(P6);//r =P5 +P4 –P2 +P6
      System.out.println(r);
      BigIntegers s = P1.add(P2);// s =P1 +P2
      System.out.println(s);
      BigIntegers t = P3.add(P4);//t =P3 +P4
      System.out.println(t);
      BigIntegers u = P5.add(P1).subtract(P3).subtract(P7);//u =P5 +P1 –P3 –P7
      System.out.println(u);


  
    
  }

    void traditionalMatrixMultiplication() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter (new FileOutputStream("traditionalMatrixMultOutput.txt"));
            for (int i=0; i<row; i++){
                for(int j=0; j<row; j++)
                {
                    BigIntegers res = new BigIntegers(zero.number);
                    for(int k=0; k<row; k++){
                        res = InM(i,k).MultiplyGradeSchool(InN(k,j)).add(res);
                    }
                    setOutputMatrix(i,j,res);
                    System.out.println(i + "," + j + "," + res);
                    printWriter.println (i+","+j+","+res);

                }
            }
        printWriter.close ();
  }
}
