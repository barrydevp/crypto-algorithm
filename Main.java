import utils.*;
import java.math.BigInteger;

public class Main {
  
  public static void main(String[] args) {
    // System.out.println(Generator.getInputBignumberByBitLength().toString());
    // System.out.println(Generator.getAndGeneratePrimeNumber().toString());
    //
    // System.out.println(Modulo.calcInverseNumber(Generator.getAndGeneratePrimeNumber(), Generator.getAndGeneratePrimeNumber()).toString());
    
    // Modulo.Test_calcInverseNumber();

    // RSA.run();
    // ElGamal.run();
    //
//     BigInteger a = BigInteger.probablePrime(512, Generator.ran).subtract(BigInteger.ONE);
// 
//     int times = 0;
//     while(a.remainder(BigInteger.TWO).equals(BigInteger.ZERO)) {
//       a = a.divide(BigInteger.TWO);
//       times++;
//     }
// 
//     System.out.println(times + ", " + a);
//
    // System.out.println((new BigInteger("13")).modInverse(new BigInteger("5")));
    // System.out.println(Modulo.computeBigQFromP(Generator.getAndGeneratePrimeNumber()));
    BigInteger k = Modulo.genSafePrime(512);

    System.out.println(Modulo.isPrime(k));
    System.out.println(k.bitLength());
    System.out.println(k);
    System.out.println(Modulo.getPrimitiveRootFromSafePrime(k));
  }

}
