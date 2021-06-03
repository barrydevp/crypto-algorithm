package utils;
import java.math.BigInteger;

public class Decoder {
  static final BigInteger base = new BigInteger("26");

  public static String numToStr(BigInteger num) {
    String str = "";

    while (num != BigInteger.ZERO){
      str = (char) (num.mod(Decoder.base).intValue() + 'a') + str;
      num = num.divide(Decoder.base);
    }

    return str;
  }

  public static BigInteger strToNum(String str) {
    BigInteger num = BigInteger.ZERO;

    for (int i = 0; i < str.length(); i++) {
      num = num.multiply(base).add(BigInteger.valueOf(str.charAt(i) - 'a'));
    }

    return num;
  }
}
