import java.math.BigInteger;
import utils.*;

public class EllipticCurve {

  public static void main(String[] args) {

    /** y^2 = x^3 - 3x + b (mod p) */
    System.out.println("EllipticCurve 224 bit. Dao Minh Hai - UET - 18020445");
    BigInteger n = new BigInteger("26959946667150639794667015087019630673557916260026308143510066298881");
    BigInteger a = new BigInteger("-3");
    BigInteger s = new BigInteger("18020445");
    BigInteger b = new BigInteger("b4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4", 16);
    BigInteger[] P = {new BigInteger("b70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21", 16), new BigInteger("bd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34", 16)};
    BigInteger[] B = Modulo.ellipticMul(P, s, n, a);

    BigInteger[] M = Modulo.ellipticMul(P, new BigInteger("29012000"), n, a);
    BigInteger k = Generator.generatePrimeNumber(20);

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
