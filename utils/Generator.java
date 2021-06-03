package utils;
import java.math.BigInteger;
import java.util.*;

public class Generator {
  static Random ran = new Random();

  static final String PROMPT_INPUT_MESSAGE = "Enter number of bit: ";

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

    System.out.print(PROMPT_INPUT_MESSAGE);

    Scanner scanner = Scan.getScanner();
    int bitLength = scanner.nextInt();

    return Generator.genNum(bitLength);
  }

  public static BigInteger getInputBignumberByBitLength2() {

    System.out.print(PROMPT_INPUT_MESSAGE);

    Scanner scanner = Scan.getScanner();
    int bitLength = scanner.nextInt();

    return Generator.genNumPure(bitLength);
  }

  public static BigInteger generatePrimeNumber(int bitLength) {

    return BigInteger.probablePrime(bitLength, Generator.ran);
  }

  public static BigInteger getAndGeneratePrimeNumber() {

    System.out.print(PROMPT_INPUT_MESSAGE);

    Scanner scanner = Scan.getScanner();
    int bitLength = scanner.nextInt();

    return Generator.generatePrimeNumber(bitLength);
  }
}
