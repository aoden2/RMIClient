package com.tdt.logic;

import java.math.BigInteger;

public class PrimeUtils {

    public static boolean isPrimeNumber(BigInteger n) {

        if (n.mod(BigInteger.valueOf(2)) == BigInteger.ZERO) return false;
        for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(n) < 0 || i.multiply(i).compareTo(n) == 0;
             i = i.add(BigInteger.valueOf(2))) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }
}
