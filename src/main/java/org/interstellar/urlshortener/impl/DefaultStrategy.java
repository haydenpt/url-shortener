package org.interstellar.urlshortener.impl;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;

public class DefaultStrategy implements UrlShortenerStrategy {

    // Ignore I, O, l, 0 to avoid confusion in the URL
    private static final String ENCODING_CHARS = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final int length = 7;

    @Override
    public String shorten(String url) {
        String hash = hash(url);
        BigInteger decimal = getDecimal(hash);
        String encoded = encode(decimal);
        return getShortenId(encoded);
    }

    private String hash(String url) {
        return DigestUtils.sha256Hex(url);
    }

    private String encode(BigInteger decimal) {
        StringBuilder encoded = new StringBuilder();
        int base = ENCODING_CHARS.length();

        while (decimal.compareTo(BigInteger.ZERO) > 0) {
            encoded.insert(0, ENCODING_CHARS.charAt(decimal.mod(BigInteger.valueOf(base)).intValue()));
            decimal = decimal.divide(BigInteger.valueOf(base));
        }
        return encoded.toString();
    }

    private BigInteger getDecimal(String hash) {
        return new BigInteger(hash, 16);
    }

    private String getShortenId(String encoded) {
        return encoded.substring(encoded.length() - length);
    }
}
