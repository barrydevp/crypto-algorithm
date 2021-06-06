import java.math.BigInteger;
import utils.*;

public class EllipticCurve {

  public static BigInteger[] bruteForceCountPoint(BigInteger a, BigInteger b, BigInteger n) {
    // y^2 = x^3 + ax + b (mod n)
    BigInteger x = BigInteger.ZERO;
    BigInteger count = BigInteger.ONE;
    BigInteger result[] = {count, x, x};

    while(x.compareTo(n) < 0) {
      BigInteger z = x.pow(3).add(x.multiply(a)).add(b).mod(n);
      
      BigInteger y = BigInteger.ZERO;
      while(y.compareTo(n) < 0) {
        if(z.equals(y.modPow(BigInteger.TWO, n))) {
          result[1] = x;
          result[2] = y;
          count = count.add(BigInteger.ONE);
        }

        y = y.add(BigInteger.ONE);
      }

      x = x.add(BigInteger.ONE);
    }
    result[0] = count;

    return result;
  }

  public static void main(String[] args) {

    System.out.println("Xin mời nhập số bit đường cong EC");
    System.out.println("- lưu ý thuật toán chạy rất chậm khi số càng lớn tầm 16 bit là chậm");
    System.out.println("- lưu ý số bit phải lớn hơn 4");
    System.out.print("Nhập: ");
    int bitLength = Scan.getScanner().nextInt();
    Scan.getScanner().nextLine();
    BigInteger n= Generator.generatePrimeNumber(bitLength);
    BigInteger a = new BigInteger("1");
    BigInteger b = new BigInteger("3");
    BigInteger result[] = bruteForceCountPoint(a, b, n);

    while(!Modulo.isPrime(result[0])) {
      n = Generator.generatePrimeNumber(bitLength);
      result = bruteForceCountPoint(a, b, n);
    }
    BigInteger[] P = {result[1], result[2]};
    BigInteger s = Generator.generatePrimeNumber(bitLength - 1);

    System.out.printf("E: y^2 = x^3 + x + 3 (mod %s), số điểm: %s, điểm sinh P(x, y)=(%s, %s)\n", n, result[0], result[1], result[2]);

    /** y^2 = x^3 - 3x + b (mod p) */
    // System.out.println("EllipticCurve 224 bit. Dao Minh Hai - UET - 18020445");
    // BigInteger n = new BigInteger("26959946667150639794667015087019630673557916260026308143510066298881");
    // BigInteger a = new BigInteger("-3");
    // BigInteger b = new BigInteger("b4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4", 16);
    // BigInteger[] P = {new BigInteger("b70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21", 16), new BigInteger("bd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34", 16)};
    // BigInteger s = new BigInteger("18020445");
    BigInteger[] B = Modulo.ellipticMul(P, s, n, a);

    System.out.print("Mời nhập bản tin bí mật x để thực hiện tính xP = M: ");
    BigInteger x = new BigInteger("" + Scan.getScanner().nextInt());
    BigInteger[] M = Modulo.ellipticMul(P, x, n, a);
    BigInteger k = Generator.generatePrimeNumber(bitLength - 1);

    System.out.print("\n\n==========\n\n");
    System.out.println("Công khai EllipticCurve(n, a, b): (" + n + ", " + a + ", " + b + ")");
    System.out.println("Công khai P = (" + P[0] + ", " + P[1] + ")");
    System.out.println("Công khai B = (" + B[0] + ", " + B[1] + ")");
    System.out.println("Số ngẫu nhiên k: " + k);
    System.out.println("Điểm cần mã hóa M(x, y): (" + M[0] + ", " + M[1] + ")");
    System.out.print("\n==========\n\n");

    BigInteger[] M1 = Modulo.ellipticMul(P, k, n, a);
    BigInteger[] M2 = Modulo.ellipticAdd(M, Modulo.ellipticMul(B, k, n, a), n, a);
    BigInteger[] sub = Modulo.ellipticMul(M1, s, n, a);
    sub[1] = BigInteger.ZERO.subtract(sub[1]);
    BigInteger[] decoded = Modulo.ellipticAdd(M2, sub, n, a);

    System.out.print("\n==========\n\n");
    System.out.println("Bản tin đã được mã hóa (M1, M2)");
    System.out.println("M1(" + M1[0] + ", " + M1[1] + ")");
    System.out.println("M2(" + M2[0] + ", " + M2[1] + ")");
    System.out.println("Điểm giải mã được M(x, y): (" + decoded[0] + ", " + decoded[1] + ")");
    System.out.print("\n==========\n");
  }
}
