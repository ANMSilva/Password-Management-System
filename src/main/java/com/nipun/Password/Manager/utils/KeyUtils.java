package com.nipun.Password.Manager.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyUtils {

    /**
     * The Advanced Encryption Standard (AES) is a widely used symmetric-key encryption algorithm.
     * Symmetric-key means that the same key is used for both encrypting and decrypting data.
     * */


    /**
     * @param n stands for size of the key in bits (e.g., 128, 192, or 256 bits).
     *          The size determines the level of encryption strength.
     * default size of n is 128 and for 192 and 256 bits, requires additional configuration
     */
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DataVarList.ALGORITHM_ASE);
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();

        return key;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //bits and bytes

    //Bit(b)
    /**
     * A bit is the smallest unit of digital data.
     * It represents a binary value, either 0 or 1.
     * Example: If you flip a light switch on, it’s like a 1. If you flip it off, it’s a 0.
     * */

    //Byte(B)
    /**
     * A byte is a collection of 8 bits.
     * It represents a more complex piece of information, like a single character in text (e.g., "A").
     * Bytes are commonly used to measure file sizes or memory (e.g., kilobytes, megabytes).
     * */

    /**
     * Relationship Between Bits and Bytes:
     * 1 Byte = 8 Bits.
     *
     * A plain text file contains the word HELLO:
     * Each letter is stored as 1 byte (8 bits).
     * Total size of the file = 5 bytes = 5×8 = 40 bits.
     * */
}
