import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class MatrixMultiplication {
  
  int row, col;
  Controller controller;
  HashMap<Integer, BigIntegers> input1;
  HashMap<Integer, BigIntegers> input2;
  Map<Integer, BigIntegers> output = new HashMap<Integer, BigIntegers>();
  
  public MatrixMultiplication(Controller controller, int row, HashMap<Integer, BigIntegers> input1,
      HashMap<Integer, BigIntegers> input2) {
    this.row = row;
    this.col = row;
    this.controller = controller;
    this.input1 = input1;
    this.input2 = input2;
  }
  
  
  BigIntegers InM(int i, int j) {
    int x=i*col+j;
    if (input1.containsKey(x)) {
      return input1.get(x);
    }
    
    return new BigIntegers("0");
  }
  BigIntegers InN(int i, int j) {
    int x=i*col+j;
    if (input2.containsKey(x)) {
      return input2.get(x);
    }
    
    return new BigIntegers("0");
  }
  
  void setOutputMatrix(int i, int j, BigIntegers o) {
    output.put(i*col+j,o);
  }
  
  
  void naiveMatrixMultiplicationWithSchool() throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter (new FileOutputStream("naiveMatrixMultiplicationWithSchool.txt"));
    for (int i=0; i<row; i++){
        for(int j=0; j<row; j++)
        {
            BigIntegers res = new BigIntegers("0");
            for(int k=0; k<row; k++){
              res = (InM(i,k).MultiplyGradeSchool(InN(k,j))).add(res);
            }
            printWriter.println (i+","+j+","+res);

        }
    }
    printWriter.close ();
  }
  
  void naiveMatrixMultiplicationWithGauss() throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter (new FileOutputStream("naiveMatrixMultiplicationWithGauss.txt"));
    for (int i=0; i<row; i++){
        for(int j=0; j<row; j++)
        {
            BigIntegers res = new BigIntegers("0");
            for(int k=0; k<row; k++){
                res = (InM(i,k).MultiplyByGaussian(InN(k,j))).add(res);
            }
            printWriter.println (i+","+j+","+res);

        }
    }
    printWriter.close ();
  }
  
  void naiveMatrixMultiplicationWithKaratsuba() throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter (new FileOutputStream("naiveMatrixMultiplicationWithKaratsuba.txt"));
    for (int i=0; i<row; i++){
        for(int j=0; j<row; j++)
        {
            BigIntegers res = new BigIntegers("0");
            for(int k=0; k<row; k++){
                res = (InM(i,k).MultiplyByKaratsuba(InN(k,j))).add(res);
            }
            printWriter.println (i+","+j+","+res);

        }
    }
    printWriter.close ();
  }
  
  
  
  void matrixMultStrassen (int type) {
    HashMap<Integer, BigIntegers> result;
    result = matrixMultStrassenRecur(input1, input2, row, type);
    for (int i=0 ;i<row; i++) {
      for (int j=0; j<row; j++) {
        if (result.containsKey(i*row+j))
          System.out.print(result.get(i*row+j)+" ");
        else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }
  }
  
  //starting index of two matrix, size
  HashMap<Integer, BigIntegers>  matrixMultStrassenRecur(HashMap<Integer, BigIntegers> m1,
      HashMap<Integer, BigIntegers> m2,
      int sz, int type) {
   
    HashMap<Integer, BigIntegers> result = new HashMap<Integer, BigIntegers>();
    if (sz ==1) {
      if (m1.containsKey(0) && m2.containsKey(0)) {
   
        BigIntegers sizeOneMatrix;
        if (type ==1) {
          sizeOneMatrix = m1.get(0).MultiplyGradeSchool(m2.get(0));
        } else if (type ==2) {
          sizeOneMatrix = m1.get(0).MultiplyByGaussian(m2.get(0));
        } else {
          sizeOneMatrix = m1.get(0).MultiplyByKaratsuba(m2.get(0));
        }
        result.put(0, sizeOneMatrix);
      }
      return result;
    }
    
    int newSize = sz/2;
    int ex1 = sz/2;
    int ey1=sz/2;
    HashMap<Integer, BigIntegers> a = createMatrix(0, 0, newSize, m1);
    HashMap<Integer, BigIntegers> b = createMatrix(0, ey1, newSize, m1);
    HashMap<Integer, BigIntegers> c = createMatrix(ex1, 0, newSize, m1);
    HashMap<Integer, BigIntegers> d = createMatrix(ex1, ey1, newSize, m1);
    
    HashMap<Integer, BigIntegers> e = createMatrix(0, 0, newSize, m2);
    HashMap<Integer, BigIntegers> f = createMatrix(0, ey1, newSize, m2);
    HashMap<Integer, BigIntegers> g = createMatrix(ex1, 0, newSize, m2);
    HashMap<Integer, BigIntegers> h = createMatrix(ex1, ey1, newSize, m2);
    
    HashMap<Integer, BigIntegers> P1 = matrixMultStrassenRecur(a, subtractMatrix(f, h, newSize), newSize, type);
    HashMap<Integer, BigIntegers> P2 = matrixMultStrassenRecur(addMatrix(a, b, newSize), h, newSize, type);
    HashMap<Integer, BigIntegers> P3 = matrixMultStrassenRecur(addMatrix(c, d, newSize), e, newSize, type);
    HashMap<Integer, BigIntegers> P4 = matrixMultStrassenRecur(d, subtractMatrix(g, e, newSize), newSize, type);
    
    HashMap<Integer, BigIntegers> P5 = matrixMultStrassenRecur(addMatrix(a, d, newSize), 
        addMatrix(e, h, newSize), newSize, type);
    
    HashMap<Integer, BigIntegers> P6 = matrixMultStrassenRecur(subtractMatrix(b, d, newSize), 
        addMatrix(g, h, newSize), newSize,type);
    
    HashMap<Integer, BigIntegers> P7 = matrixMultStrassenRecur(subtractMatrix(a, c, newSize), 
        addMatrix(e, f, newSize), newSize,type);
 
    HashMap<Integer, BigIntegers> r= subtractMatrix(addMatrix(addMatrix(P5, P4, newSize), P6, newSize), P2, newSize);
    HashMap<Integer, BigIntegers> s = addMatrix(P1, P2, newSize);
    HashMap<Integer, BigIntegers> t =  addMatrix(P3, P4, newSize);
    HashMap<Integer, BigIntegers> u = subtractMatrix(addMatrix(P5, P1, newSize),
        addMatrix(P3, P7, newSize), newSize);
    
    
    for (int i=0 ;i<newSize; i++) 
      for (int j=0; j<newSize; j++) {
        int in = i *sz +j;
        int  io = i*newSize +j;  
         if (r.containsKey(io)) {
           result.put(in, r.get(io));
         } 
      }
    
    for (int i=0 ;i<newSize; i++) 
      for (int j=0; j<newSize; j++) {
        int in = i *sz +j+ey1;
        int  io = i*newSize +j;  
         if (s.containsKey(io)) {
           result.put(in, s.get(io));
         } 
      }
    
    for (int i=0 ;i<newSize; i++) 
      for (int j=0; j<newSize; j++) {
        int in = (i+ex1) *sz +j;
        int  io = i*newSize +j;  
         if (t.containsKey(io)) {
           result.put(in, t.get(io));
         } 
      }
    for (int i=0 ;i<newSize; i++) 
      for (int j=0; j<newSize; j++) {
        int in = (i+ex1) *sz +j+ ey1;
        int  io = i*newSize +j;  
         if (u.containsKey(io)) {
           result.put(in, u.get(io));
         } 
      }
    
    
    return result;
  }
  
  HashMap<Integer, BigIntegers> createMatrix(int x, int y, int sz, Map<Integer, BigIntegers> mp) {
    HashMap<Integer, BigIntegers> matrixMap = new HashMap<Integer, BigIntegers>();
    for (int i=0; i<sz; i++){
      for(int j=0; j<sz; j++) {
        int a = i *sz+ j;
        int b = (x+i)*2*sz+ y+j;
        if (mp.containsKey(b))
          matrixMap.put(a, mp.get(b));
      }
    }
    
    return matrixMap;
  }
  
  HashMap<Integer, BigIntegers> multiplyMatrix(Map<Integer, BigIntegers> a,
      Map<Integer, BigIntegers>  b, int sz, int type) {
    HashMap<Integer, BigIntegers> matrixMap = new HashMap<Integer, BigIntegers>();
    for (int i=0; i<sz; i++){
      for(int j=0; j<sz; j++) {
          BigIntegers res = new BigIntegers("0");
          for(int k=0; k<sz; k++){
             int ik = i*sz +k;
             int kj = k*sz+j;
             if (a.containsKey(ik) && b.containsKey(kj)) {
               if (type==1) {
                 res = (a.get(ik).MultiplyGradeSchool(b.get(kj))).add(res);
               } else if (type==2) {
                 res = (a.get(ik).MultiplyByGaussian(b.get(kj))).add(res);
               } else {
                 res = (a.get(ik).MultiplyByKaratsuba(b.get(kj))).add(res);
               }
               
             }
               
          }
          
          if (!res.isZero()) {
            matrixMap.put(i*sz+j, res);
          }
      }
    }
    
    return matrixMap;
  }
  
  
  
  
  HashMap<Integer, BigIntegers> addMatrix(Map<Integer, BigIntegers> a, 
      Map<Integer, BigIntegers>  b, int sz) {
    HashMap<Integer, BigIntegers> matrixMap = new HashMap<Integer, BigIntegers>();
    for (int i=0; i<sz; i++){
      for(int j=0; j<sz; j++) {
           int f = i*sz +j;
           BigIntegers aa = new BigIntegers("0");
           if (a.containsKey(f))
             aa = aa.add(a.get(f));
           if (b.containsKey(f))
             aa = aa.add(b.get(f));
           
           if (!aa.isZero()) {
             matrixMap.put(f, aa);
           }
      }
    }
    
    return matrixMap;
  }
  
  HashMap<Integer, BigIntegers> subtractMatrix(Map<Integer, BigIntegers> a, 
      Map<Integer, BigIntegers>  b, int sz) {
    HashMap<Integer, BigIntegers> matrixMap = new HashMap<Integer, BigIntegers>();
    for (int i=0; i<sz; i++){
      for(int j=0; j<sz; j++) {
           int f = i*sz +j;
           BigIntegers aa = new BigIntegers("0");
           if (a.containsKey(f))
             aa = aa.add(a.get(f));
           if (b.containsKey(f))
             aa = aa.subtract(b.get(f));
         
           if (!aa.isZero()) {
             matrixMap.put(f, aa);
           }
      }
    }
    
    return matrixMap;
  }

}
