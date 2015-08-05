package com.shu_mj.ds;

/**
 * Created by Jun on 6/7/2014.
 */

import java.math.BigInteger;

public class Rational implements Comparable<Rational> {
    public static final Rational ZERO  = new Rational(BigInteger.ZERO, BigInteger.ONE);
    public static final Rational ONE   = new Rational(BigInteger.ONE, BigInteger.ONE);
    public static final Rational TWO   = new Rational(BigInteger.valueOf(2), BigInteger.ONE);
    public static final Rational THREE = new Rational(BigInteger.valueOf(3), BigInteger.ONE);
    public static final Rational FOUR  = new Rational(BigInteger.valueOf(4), BigInteger.ONE);
    public static final Rational FIVE  = new Rational(BigInteger.valueOf(5), BigInteger.ONE);
    public static final Rational SIX   = new Rational(BigInteger.valueOf(6), BigInteger.ONE);
    public static final Rational SEVEN = new Rational(BigInteger.valueOf(7), BigInteger.ONE);
    public static final Rational EIGHT = new Rational(BigInteger.valueOf(8), BigInteger.ONE);
    public static final Rational NINE  = new Rational(BigInteger.valueOf(9), BigInteger.ONE);
    public static final Rational TEN   = new Rational(BigInteger.valueOf(10), BigInteger.ONE);

    public BigInteger num;
    public BigInteger den;

    public Rational(long num, long den) {
        this(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    public Rational(BigInteger num, BigInteger den) {
        this.num = num;
        this.den = den;
        red();
    }

    void red() {
        BigInteger gcd = num.gcd(den);
        if (gcd.signum() != 0) {
            num = num.divide(gcd);
            den = den.divide(gcd);
        }
        if (den.signum() < 0) {
            num = num.negate();
            den = den.negate();
        }
    }

    public Rational add(Rational r) {
        return new Rational(num.multiply(r.den).add(r.num.multiply(den)),
                den.multiply(r.den));
    }

    public Rational sub(Rational r) {
        return new Rational(num.multiply(r.den).subtract(r.num.multiply(den)),
                den.multiply(r.den));
    }

    public Rational mul(Rational r) {
        return new Rational(num.multiply(r.num), den.multiply(r.den));
    }

    public Rational div(Rational r) {
        return new Rational(num.multiply(r.den), den.multiply(r.num));
    }

    public int signum() {
        return num.signum();
    }

    public Rational pow(int b) {
        BigInteger n = BigInteger.ONE, d = BigInteger.ONE, an = num, ad = den;
        while (b > 0) {
            if ((b & 1) == 1) {
                n = n.multiply(an);
                d = d.multiply(ad);
            }
            an = an.multiply(an);
            ad = ad.multiply(ad);
            b >>>= 1;
        }
        return new Rational(n, d);
    }

    @Override
    public int compareTo(Rational o) {
        return num.multiply(o.den).compareTo(o.num.multiply(den));
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }
}