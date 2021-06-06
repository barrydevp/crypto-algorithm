import java.math.BigInteger;
import utils.*;

public class ElGamal {
  public static void main(String[] args) {
    /** tu chon */
    System.out.print("Xin mời nhập số bit ElGamal (cụ thể là số bit của số p): ");
    int bitLength = Scan.getScanner().nextInt();
    Scan.getScanner().nextLine();

    /** Su dung cach 1 Safe Prime */
    // BigInteger p = Modulo.genSafePrime(bitLength);
    // BigInteger alpha = Modulo.getPrimitiveRootFromSafePrime(p);
    
    /** Su dung cach 2 virtual key */
    BigInteger realP = Generator.generatePrimeNumber(bitLength);
    VirtualElGamalKey key = Modulo.genVirtualElGamalKey(realP);
    BigInteger p = key.q;
    BigInteger alpha = key.getPrimitiveRoot();
    // System.out.printf("%s, %s, %s\n", key.p, p, key.z);

    System.out.print("Mời nhập khóa bí mật (chỉ nhập chữ số): ");
    BigInteger a = new BigInteger("" + Scan.getScanner().nextInt());
    Scan.getScanner().nextLine();
    BigInteger beta = alpha.modPow(a, p);
    BigInteger k = Generator.generatePrimeNumber((int)(bitLength / 2));

    System.out.print("\n\n==========\n\n");
    System.out.println("Công khai khóa ElGamal(p, alpha, beta): (" + p + ", " + alpha + ", " + beta + ")");
    System.out.println("Khóa bí mật ElGamal(a): (" + a + ")");
    System.out.println("Số ngẫu nhiên k: " + k);
    System.out.print("\n==========\n\n");

    /** get input from stdin */
    System.out.println("Nhập xâu cần mã hóa (chỉ nhập chữ cái thường không dấu)");
    System.out.print("Nhập: ");
    String str = Scan.getScanner().nextLine();
    BigInteger x = Decoder.strToNum(str).mod(p);
    System.out.println("Số hóa (" + str + ") ta được: " + x);

    BigInteger y1 = alpha.modPow(k, p);
    BigInteger y2 = x.multiply(beta.modPow(k, p)).mod(p);

    // alpha^-(ka) = alpha^k((p - 1) - a) = alpha^k(p - (a + 1))
    BigInteger y1Inverse = y1.modPow(p.subtract(a.add(BigInteger.ONE)), p);
    BigInteger decoded = y2.multiply(y1Inverse).mod(p);

    System.out.print("\n==========\n\n");
    System.out.println("Bản tin đã được mã hóa (y1, y2): (" + y1 + ", " + y2 + ")");
    System.out.println("Bản tin được giải mã dưới dạng số: " + decoded);
    System.out.println("Chuyển về dạng text: " + Decoder.numToStr(decoded));
    System.out.print("\n==========\n");
  }
}
