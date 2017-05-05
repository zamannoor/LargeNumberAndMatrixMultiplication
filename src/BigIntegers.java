
public class BigIntegers {
  String number="0";
  int sign =0;
  
  public BigIntegers(String a) {
    if (a.charAt(0) =='-') {
      this.number = a.substring(1);
      this.sign = 1;
    }
    else if (a.charAt(0) =='+') {
      this.number = a.substring(1);
      this.sign = 0;
    } else {
      this.number = a;
      
    }
    this.number = BigIntegersHelper.trimZero(this.number);
  }
  
  BigIntegers add(BigIntegers b) {
    if (this.sign != b.sign) {
      if (this.sign == 0) 
        return this.subtract(new BigIntegers(b.number));
      else 
        return b.subtract(new BigIntegers(this.number));
    }
    
    String z = BigIntegersHelper.add(this.number, b.number);
    BigIntegers result = new BigIntegers(z);
    result.sign = this.sign;
    return result;
  }
 
  
  BigIntegers subtract(BigIntegers b) {
    if (this.sign != b.sign) {
      String z = BigIntegersHelper.add(this.number, b.number);
      if (this.sign == 1) {
        z = "-"+z;
      }
      
      BigIntegers result = new BigIntegers(z);
      return result;
    }
    
    String z = "";
    String subSign = "";   
    if (this.compareValue(b) > 0) {
      z = BigIntegersHelper.subtract(this.number, b.number);
      if (this.sign == 1)
        subSign = "-";
    } else if (this.compareValue(b) < 0) {
      z = BigIntegersHelper.subtract(b.number,this.number);
      if (b.sign ==0)
        subSign = "-";
    } else {
      return  new BigIntegers("0");
    }
    
    z = subSign + z;
    BigIntegers result = new BigIntegers(z);
    return  result;
  }
  

  
  
  public BigIntegers MultiplyGradeSchool(BigIntegers b) {

    BigIntegers result = new BigIntegers("0");
    for (int i =b.number.length()-1; i>=0; i--) {
      BigIntegers temp = BigIntegersHelper.SingleDigitMultiplication( this,
          ((int)(b.number.charAt(i)-'0')));
      int j = b.number.length()-i-1;
      while (j > 0 ) {
        temp.number = temp.number +"0";
        j--;
      }
      
      result = result.add(temp);
    }
    
    if (this.sign != b.sign) {
      result.sign = 1;
    }
    return result;
  }
  
  
  public static BigIntegers MultiplyGaussian(BigIntegers a, BigIntegers b) {
    
    String x= a.number;
    String y = b.number;
    
    int l1 = x.length();
    int l2 = y.length();
    
    if (l1 >l2) {
      return BigIntegersHelper.MultiplyGaussianRecursion(x, y);
    } else {
      return BigIntegersHelper.MultiplyGaussianRecursion(x, y);
    }
    
  }
  
  public static void main(String[] args) {
   UnitTestBigInteger unitTest = new UnitTestBigInteger();
   unitTest.unitTestAddition();
   unitTest.unitTestSubtraction();
   unitTest.unitTestMultiplication();
  }
  
  
  int compareValue(BigIntegers b) {
    int l1 = this.number.length();
    int l2 = b.number.length();
    if (l1 > l2) {
      return 1;
    } else if (l1 <l2) {
      return -1;
    } else {
      return this.number.compareTo(b.number);
    }  
  }
  @Override
  public String toString() {
    if (sign == 1) 
      return "-"+number;
    return number;
  }

}
