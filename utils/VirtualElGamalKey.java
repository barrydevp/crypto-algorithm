package utils;

import java.math.BigInteger;

public class VirtualElGamalKey {
  public BigInteger p;
  public BigInteger q;
  public BigInteger z;

  public VirtualElGamalKey(BigInteger p, BigInteger q, BigInteger z) {
    this.p = p;
    this.q = q;
    this.z = z;
  }

  public BigInteger getPrimitiveRoot() {
    return BigInteger.TWO.modPow(this.z, this.q);
  }
}

