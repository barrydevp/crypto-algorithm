package utils;
import java.math.BigInteger;
import java.util.*;

public class Generator {
  static Random ran = new Random();

  private static int _getRandomBit() {
    return ran.nextInt(2);
  }

  public static BigInteger genNumPure(int bitLength) {
    int curBitLength = 1;
    String textNum = "1";

    while(curBitLength < bitLength) {
      curBitLength++;
      textNum += Generator._getRandomBit();
    }

    return new BigInteger(textNum, 2);
  }

  public static BigInteger genNum(int bitLength) {
    return new BigInteger(bitLength, ran);
  }

  public static BigInteger getInputBignumberByBitLength() {

    System.out.print("enter number of bit: ");

    Scanner scanner = Scan.getScanner();
    int bitLength = scanner.nextInt();

    return Generator.genNum(bitLength);
  }

  public static BigInteger getInputBignumberByBitLength2() {

    System.out.print("enter number of bit: ");

    Scanner scanner = Scan.getScanner();
    int bitLength = scanner.nextInt();

    return Generator.genNumPure(bitLength);
  }
}
