import java.math.BigInteger;
import utils.*;

public class RSA {
  public static void main(String[] args) {
    /** get input from stdin */
    System.out.print("Xin mời nhập số bit khóa RSA (cụ thể là số bit của số p): ");
    int bitLength = Scan.getScanner().nextInt();

    BigInteger p = Generator.generatePrimeNumber(bitLength);
    BigInteger q = Generator.generatePrimeNumber((int)(bitLength / 2));
    BigInteger n = p.multiply(q);
    BigInteger e = Generator.generatePrimeNumber((int)(bitLength / 2));
    BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    BigInteger d = Modulo.calcInverseNumber(e, phiN);

    System.out.print("\n==========\n\n");
    System.out.println("Công khai khóa RSA(n, e): (" + n + ", " + e + ")");
    System.out.println("Khóa bí mật RSA(n, d): (" + n + ", " + e + ")");
    System.out.print("\n==========\n\n");

    /** get input from stdin */
    Scan.getScanner().nextLine();
    System.out.print("Nhập xâu cần mã hóa: ");
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
