package utils;
import java.math.BigInteger;
import java.util.*;

public class Modulo {
  /* *
   * Using Extend Euclid algorithm to calculate inverse number of a
   *
   * Seft implement by Dao Minh Hai - UET
   *
   * */
  public static BigInteger calcInverseNumber(BigInteger a, BigInteger b) {
    BigInteger originalB = new BigInteger(b.toString());

    BigInteger xa = new BigInteger("1"); 
    // BigInteger ya = new BigInteger("0");
    BigInteger xb = new BigInteger("0"); 
    // BigInteger yb = new BigInteger("1");

    while (!b.equals(BigInteger.ZERO)) {
      BigInteger q = a.divide(b);
      BigInteger r = a.subtract(q.multiply(b));
      a = b;
      b = r;

      BigInteger xr = xa.subtract(q.multiply(xb));
      // BigInteger yr = ya.subtract(q.multiply(yb));

      xa = xb;
      // ya = yb;

      xb = xr;
      // yb = yr;
    }

    return xa.compareTo(BigInteger.ZERO) < 0 ? originalB.add(xa): xa;
  }

  public static void Test_calcInverseNumber() {
    BigInteger a = new BigInteger("13");
    BigInteger b = new BigInteger("5");

    // expected number: 2
    System.out.println(Modulo.calcInverseNumber(a, b).toString());
    
  }

  public static BigInteger primaryRoot(BigInteger p) {
    List<BigInteger> divisors = new ArrayList<>();

    BigInteger phi = p.subtract(BigInteger.ONE); 
    BigInteger n = phi;
    BigInteger seed = BigInteger.TWO;

    while(seed.multiply(seed).compareTo(n) <= 0) {
      if(n.mod(seed).equals(BigInteger.ZERO)) {
        divisors.add(seed);
        while(n.mod(seed).equals(BigInteger.ZERO))
          n = n.divide(seed);
      }
      seed = seed.add(BigInteger.ONE);
    }

    if(n.compareTo(BigInteger.ONE) > 0) {
      divisors.add(n);
    }

    BigInteger left = BigInteger.TWO;
    BigInteger cloneP = p;
    while(left.compareTo(p) <= 0) {
      boolean check = true;
      BigInteger result = left;

      for(int index=0; index < divisors.size() && check; ++index) {
        BigInteger exp = phi.divide(divisors.get(index));
        check &= (!result.modPow(exp, cloneP).equals(BigInteger.ONE));
      }

      if(check) {
        return result;
      }
    }

    return BigInteger.ZERO;
  }

  public static BigInteger[] ellipticAdd(BigInteger[] arr1, BigInteger[] arr2, BigInteger n, BigInteger a) {
    BigInteger x = arr2[0].subtract(arr1[0]);
    if(x.compareTo(BigInteger.ZERO) == -1) x = x.add(n);

    BigInteger lam;
    if(arr1[0].equals(arr2[0]) && arr1[1].equals(arr2[1])) {
      if (arr1[1].multiply(BigInteger.TWO).mod(n).equals(BigInteger.ZERO))
        return new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
      BigInteger y = Modulo.calcInverseNumber(arr1[1].multiply(BigInteger.TWO), n);

      lam = arr1[0].multiply(arr1[0]).multiply(BigInteger.valueOf(3)).add(a).multiply(y);
    } else {
      if(x.equals(BigInteger.ZERO))
        return new BigInteger[]{BigInteger.ZERO, BigInteger.ZERO};
      BigInteger _x = Modulo.calcInverseNumber(x, n);
      lam = arr2[1].subtract(arr1[1]).multiply(_x);
    }

    if(lam.compareTo(BigInteger.ZERO) == -1) lam = lam.add(n);
    lam = lam.mod(n);
    BigInteger res_x = lam.multiply(lam).subtract(arr1[0]).subtract(arr2[0]).mod(n).add(n).mod(n);
    BigInteger[] res = {res_x, lam.multiply(arr1[0].subtract(res_x)).subtract(arr1[1]).mod(n).add(n).mod(n)};

    return res;
  }

  /** Double and add */
  public static BigInteger[] ellipticMul(BigInteger[] arr1, BigInteger s, BigInteger n, BigInteger a) {
    BigInteger[] res = {BigInteger.ZERO, BigInteger.ZERO};
    String mul = s.toString(2);
    for (int i = 0; i < mul.length(); ++i) {
      if (mul.charAt(i) == '1') {
        if (i != 0) {
          res = Modulo.ellipticAdd(res, res, n, a);
          res = Modulo.ellipticAdd(res, arr1, n, a);
        } else {
          res = arr1;
        }
      } else {
        res = Modulo.ellipticAdd(res, res, n, a);
      }
    }

    return res;
  }
}
