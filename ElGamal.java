import java.math.BigInteger;
import utils.*;

public class ElGamal {
  public static void main(String[] args) {
    /** tu chon */
    BigInteger p = new BigInteger("65515656870434019085035943759967997782687974007949301157313946132162615676587");
    BigInteger alpha = new BigInteger("1061");

    System.out.print("Mời nhập khóa bí mật: ");
    BigInteger a = new BigInteger("" + Scan.getScanner().nextInt());
    BigInteger beta = alpha.modPow(a, p);
    BigInteger k = Generator.generatePrimeNumber(10);

    System.out.print("\n\n==========\n\n");
    System.out.println("Công khai khóa ElGamal(p, alpha, beta): (" + alpha + ", " + beta + ")");
    System.out.println("Khóa bí mật ElGamal(a): (" + a + ")");
    System.out.println("Số ngẫu nhiên k: " + k);
    System.out.print("\n==========\n\n");

    /** get input from stdin */
    Scan.getScanner().nextLine();
    System.out.print("Nhập xâu cần mã hóa: ");
    String str = Scan.getScanner().nextLine();
    BigInteger x = Decoder.strToNum(str).mod(p);
    System.out.println("Số hóa (" + str + ") ta được: " + x);

    BigInteger y1 = alpha.modPow(k, p);
    BigInteger y2 = x.multiply(beta.modPow(k, p)).mod(p);

    BigInteger y1Inverse = y1.modPow(p.subtract(a.add(BigInteger.ONE)), p);
    BigInteger decoded = y2.multiply(y1Inverse).mod(p);

    System.out.print("\n==========\n\n");
    System.out.println("Bản tin đã được mã hóa (y1, y2): (" + y1 + ", " + y2 + ")");
    System.out.println("Bản tin được giải mã dưới dạng số: " + decoded);
    System.out.println("Chuyển về dạng text: " + Decoder.numToStr(decoded));
    System.out.print("\n==========\n");
  }
}
