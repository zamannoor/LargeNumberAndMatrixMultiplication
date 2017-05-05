
public class BigIntegersHelper {
  
  
  static String add(String x, String y) {
    x = new StringBuffer(x).reverse().toString();
    y = new StringBuffer(y).reverse().toString();
    
    String z= "";
    int len = Math.min(x.length(), y.length());
    int carry = 0;
    for (int i=0 ;i < len; i++) {
      int temp = (int)(x.charAt(i)-'0') + (int)(y.charAt(i)-'0') + carry;
      carry= temp/10;
      temp = temp % 10;
      z = z + temp;
    }
    
    int l1= x.length();
    int l2 = y.length();
    if  (l1== l2) {
      if (carry == 1) {
        z+="1";
      }
    } else if (l1> l2) {
      for (int i=len ;i < l1; i++) {
        int temp = (int)(x.charAt(i)-'0') + carry;
        carry= temp/10;
        temp = temp % 10;
        z = z + temp;
      }
      if (carry == 1) {
        z+="1";
      }
    } else {
      for (int i=len ;i < l2; i++) {
        int temp = (int)(y.charAt(i)-'0') + carry;
        carry= temp/10;
        temp = temp % 10;
        z = z + temp;
      }
      if (carry == 1) {
        z+="1";
      }
    }
    z = new StringBuffer(z).reverse().toString();
    return z;
  }
  
  // Assuming that x>y
  static String subtract(String x, String y) {
    x = new StringBuffer(x).reverse().toString();
    y = new StringBuffer(y).reverse().toString();
    
    String z="";
    int l1 = x.length();
    int l2 = y.length();
    int borrow = 0;
    for (int i=0 ;i < l2; i++) {
      int temp = 0;
      int topD = (int)(x.charAt(i)-'0');
      int botD =  (int)(y.charAt(i)-'0') + borrow;
      if (topD < botD) {
        topD +=10;
        borrow =1;
      } else {
        borrow =0;
      }
      temp =topD - botD;
      z = z + temp;
    }
    
    for (int i=l2 ;i < l1; i++) {
      int topD = (int)(x.charAt(i)-'0');
      int botD =  borrow;
      if (topD < botD) {
        topD +=10;
        borrow =1;
      } else {
        borrow =0;
      }
      int temp =topD - botD;
      z = z + temp;
    }
    
    
    z = new StringBuffer(z).reverse().toString();
    z = trimZero(z);
    return z;
  }
  
  public static String trimZero(String z) {
    int i=0;
    for (;i<z.length(); i++) {
      if (z.charAt(i) != '0') {
        break;
      }
    }
    if (i == z.length())
      return "0";
    else 
      return z.substring(i);
  }
  
  public static String addZeroAtEnd(String z, int numZero) {
    int i=0;
    for (;i<numZero; i++) {
      z = z +"0";
    }
    return z;
  }
  
  public static  BigIntegers SingleDigitMultiplication(BigIntegers a, int digit) {
    if (digit==0) {
      return new BigIntegers("0");
    }
    int i=0;
    String x = a.number;
    String z="";
    int carry =0;
    for(i=x.length()-1; i >=0; i--) {
      int temp = ((int)(x.charAt(i)-'0')) * digit + carry;
      z = z+ (temp%10);
      carry = temp/10;
    }
    if (carry !=0) 
      z = z+ carry;
    
    z = new StringBuffer(z).reverse().toString();
    BigIntegers result = new BigIntegers(z);
    return  result;
  }
  // Assuming that First one has >= length 
  static BigIntegers MultiplyGaussianRecursion(String x, String y) {
    int l1 = x.length();
    int l2 = y.length();
    
    if (l2 ==0) {
      return new BigIntegers("0");
    } else if (l2 == 1) {
      return SingleDigitMultiplication(new BigIntegers(x),  ((int)(y.charAt(0)-'0')));
    }
    
    int mid = l1/2;
    BigIntegers a,b,c,d;
    String chunkx1 = x.substring(0, mid);
    String chunkx2 = x.substring(mid);
    
    int chunk2Len = chunkx2.length();
    String chunky1 = "";
    String chunky2 ="";
    BigIntegers k1,k2,k3;
    
    
    return null;
  }
}
