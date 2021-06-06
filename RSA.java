import java.math.BigInteger;
import utils.*;

public class RSA {
  public static void main(String[] args) {
    /** get input from stdin */
    System.out.print("Xin mời nhập số bit RSA (cụ thể là số bit của số n): ");
    int bitLength = Scan.getScanner().nextInt();

    /** Generate p, q has half the number of bits as n */
    int halfOfBit = (int)(bitLength / 2);
    BigInteger p = Generator.generatePrimeNumber(halfOfBit);
    BigInteger q = Generator.generatePrimeNumber(halfOfBit);
    BigInteger n = p.multiply(q);

    System.out.println("Số bit thực tế thu được của n sau khi sinh số: " + n.bitLength());

    /** generate e and calculate d */
    BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    int bitLengthOfPhiNMinusOne = phiN.bitLength() - 1;
    BigInteger e = Generator.generatePrimeNumber(bitLengthOfPhiNMinusOne);
    while(phiN.mod(e).equals(BigInteger.ZERO)) {
      e = Generator.generatePrimeNumber(bitLengthOfPhiNMinusOne);
    }
    BigInteger d = Modulo.calcInverseNumber(e, phiN);

    System.out.print("\n==========\n\n");
    System.out.println("Công khai khóa RSA(n, e): (" + n + ", " + e + ")");
    System.out.println("Khóa bí mật RSA(n, d): (" + n + ", " + e + ")");
    System.out.print("\n==========\n\n");

    /** get input from stdin */
    Scan.getScanner().nextLine();
    System.out.println("Nhập xâu cần mã hóa (chỉ nhập chữ cái thường không dấu)");
    System.out.print("Nhập: ");
    String str = Scan.getScanner().nextLine();
    BigInteger x = Decoder.strToNum(str).mod(n);
    System.out.println("Số hóa (" + str + ") ta được: " + x);

    BigInteger encoded = x.modPow(e, n);
    BigInteger decoded = encoded.modPow(d, n);

    System.out.print("\n==========\n\n");
    System.out.println("Bản tin đã được mã hóa: " + encoded);
    System.out.println("Bản tin được giải mã dưới dạng số: " + decoded);
    System.out.println("Chuyển về dạng text: " + Decoder.numToStr(decoded));
    System.out.print("\n==========\n");
  }
}
