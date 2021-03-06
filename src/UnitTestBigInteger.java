import java.math.BigInteger;
import java.util.Random;


public class UnitTestBigInteger {
  final int TEST_ITERATION = 100000;
  final int INPUT_LENGTH = 50;
  final BigInteger MinBigNumber;
  
  public UnitTestBigInteger() {
    String s ="";
    for (int i=0; i<INPUT_LENGTH-2; i++)
      s = s+"1";
     MinBigNumber = (new BigInteger(s,2)).negate();
     //System.out.println(MinBigNumber);
  }
  
  
  public void unitTestAddition() {
    Random rand = new Random();
    int i;
    for (i=0; i<TEST_ITERATION; i++) {
      BigInteger a =MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      BigInteger b = MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      String c = a.add(b).toString();
      BigIntegers aa = new BigIntegers(a+"");
      BigIntegers bb = new BigIntegers(b+"");
      
      BigIntegers result = aa.add(bb);
      if (!result.toString().equals(c)) {
        System.out.println("Addition Test Failed: \n"+a+"\n"+b+"\nActual: "+ c+"\n Manual: "+result.toString());
        break;
      } else {
        //System.out.println(a+" "+b);
      }  
      
    }
    if (i == TEST_ITERATION) {
      System.out.println(i+" Addition Test Passed");
    } else {
      System.out.println("Addition Test Failed");
    }
  }
  
  public void unitTestSubtraction() {
    Random rand = new Random();
    int i;
    for (i=0; i<TEST_ITERATION; i++) {
      BigInteger a =MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      BigInteger b = MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      String c = a.subtract(b).toString();
      BigIntegers aa = new BigIntegers(a+"");
      BigIntegers bb = new BigIntegers(b+"");
      
      BigIntegers result = aa.subtract(bb);
      if (!result.toString().equals(c)) {
        System.out.println("Subtraction Test Failed: \n"+a+"\n"+b+"\nActual: "+ c+"\n Manual: "+result.toString());
        break;
      }
    }
    if (i == TEST_ITERATION) {
      System.out.println(i+" Subtraction Test Passed");
    } else {
      System.out.println(" Subtraction Test Failed");
    }
  }
  
  public void unitTestMultiplicationSchool() {
    Random rand = new Random();
    int i;
    for (i=0; i<TEST_ITERATION; i++) {
      BigInteger a =MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      BigInteger b = MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      String c = a.multiply(b).toString();
      BigIntegers aa = new BigIntegers(a+"");
      BigIntegers bb = new BigIntegers(b+"");
      
      BigIntegers result = aa.MultiplyGradeSchool(bb);
      if (!result.toString().equals(c)) {
        System.out.println("Multiplication Test Failed: \n"+a+"\n"+b+"\nActual: "+ c+"\n Manual: "+result.toString());
        break;
      }
      
      
      
    }
    if (i == TEST_ITERATION) {
      System.out.println(i+" Grade School Multiplication Test Passed");
    } else {
      System.out.println("Grade School Multiplication Test Failed");
    }
  }
  
  public void unitTestMultiplicationKaratsuba() {
    Random rand = new Random();
    int i;
    for (i=0; i<TEST_ITERATION; i++) {
      BigInteger a =MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      BigInteger b = MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      String c = a.multiply(b).toString();
      BigIntegers aa = new BigIntegers(a+"");
      BigIntegers bb = new BigIntegers(b+"");
      
      BigIntegers result = aa.MultiplyByKaratsuba(bb);
      if (!result.toString().equals(c)) {
        System.out.println("Multiplication(karatsuba) Test Failed: \n"+a+"\n"+b+"\nActual: "+ c+"\n Manual: "+result.toString());
        break;
      }
      
      
      
    }
    if (i == TEST_ITERATION) {
      System.out.println(i+" Karatsuba Multiplication Test Passed");
    } else {
      System.out.println("Karatsuba Multiplication Test Failed");
    }
  }
  
  public void unitTestMultiplicationGaussian() {
    Random rand = new Random();
    int i;
    for (i=0; i<TEST_ITERATION; i++) {
      BigInteger a =MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      BigInteger b = MinBigNumber.add(new BigInteger(INPUT_LENGTH, rand));
      String c = a.multiply(b).toString();
      BigIntegers aa = new BigIntegers(a+"");
      BigIntegers bb = new BigIntegers(b+"");
      
      BigIntegers result = aa.MultiplyByGaussian(bb);
      if (!result.toString().equals(c)) {
        System.out.println("Multiplication(Gaussian) Test Failed: \n"+a+"\n"+b+"\nActual: "+ c+"\n Manual: "+result.toString());
        break;
      }
      
      
      
    }
    if (i == TEST_ITERATION) {
      System.out.println(i+" Gaussian Multiplication Test Passed");
    } else {
      System.out.println("Gaussian Multiplication Test Failed");
    }
  }
  
  public static void main(String[] args) {
 // Sami Arshad's Department
    UnitTestBigInteger unitTest = new UnitTestBigInteger();
    unitTest.unitTestAddition();
    unitTest.unitTestSubtraction();
    unitTest.unitTestMultiplicationSchool();
    unitTest.unitTestMultiplicationGaussian();
    unitTest.unitTestMultiplicationKaratsuba();
    // creation of sparse matrix
    
    // try the matrix multiplication, creation of matrix you can see in Controller
    // It's not actually a matrix, it's a hash map.
    
  }

}
