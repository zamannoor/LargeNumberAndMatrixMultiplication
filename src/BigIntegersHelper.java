
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
    String a = x.substring(0, mid);
    String b = x.substring(mid); 
    
    int chunk2Len = b.length();
    String c= "0";
    String d ="0";
    
    if (chunk2Len >= l2) {
      d=y;
    } else {
      int temp = y.length() -chunk2Len;
      c = y.substring(0, temp);
      d = y.substring(temp);
    }
    
    
    BigIntegers ac= MultiplyGaussianRecursion(a, c);
    ac.addZeroAtEnd(2*chunk2Len);
    BigIntegers bd= MultiplyGaussianRecursion(b, d);
    BigIntegers ad;
    if (a.length() > d.length()) {
      ad = MultiplyGaussianRecursion(a, d);
    } else {
      ad = MultiplyGaussianRecursion(d,a);
    }
    ad.addZeroAtEnd(chunk2Len);
    
    BigIntegers bc;
    if (b.length() > c.length()) {
      bc = MultiplyGaussianRecursion(b, c);
    } else {
      bc = MultiplyGaussianRecursion(c,b);
    }
    bc.addZeroAtEnd(chunk2Len);
    
    
    return ac.add(bc).add(ad).add(bd);
  }
  
//Assuming that First one has >= length 
 static BigIntegers MultiplyKaratsubaRecursion(String x, String y) {
   int l1 = x.length();
   int l2 = y.length();
   if (l2 ==0) {
     return new BigIntegers("0");
   } else if (l2 == 1) {
     return SingleDigitMultiplication(new BigIntegers(x),  ((int)(y.charAt(0)-'0')));
   }
   int mid = l1/2;
   String x1 = x.substring(0, mid);
   String x2 = x.substring(mid); 
   
   int chunk2Len = x2.length();
   String y1= "0";
   String y2 ="0";
   
   if (chunk2Len >= l2) {
     y2=y;
   } else {
     int temp = y.length() -chunk2Len;
     y1 = y.substring(0, temp);
     y2 = y.substring(temp);
   }

   BigIntegers A,B,C;
   if (x1.length() > y1.length()) {
     A = MultiplyKaratsubaRecursion(x1, y1);
   } else {
     A = MultiplyKaratsubaRecursion(y1, x1);
   }
   
   if (x2.length() > y2.length()) {
     B = MultiplyKaratsubaRecursion(x2, y2);
   } else {
     B = MultiplyKaratsubaRecursion(y2, x2);
   }
   
   BigIntegers x1PlusX2 = (new BigIntegers(x1)).add(new BigIntegers(x2));
   BigIntegers y1PlusY2 = (new BigIntegers(y1)).add(new BigIntegers(y2));
   
   if (x1PlusX2.number.length() > y1PlusY2.number.length()) {
     C = MultiplyKaratsubaRecursion(x1PlusX2.number, y1PlusY2.number);
   } else {
     C = MultiplyKaratsubaRecursion(y1PlusY2.number, x1PlusX2.number);
   }
   
   BigIntegers K = C.subtract(A.add(B));
   A.addZeroAtEnd(2* chunk2Len);
   K.addZeroAtEnd(chunk2Len);
   
   return A.add(K.add(B));
 }
}
