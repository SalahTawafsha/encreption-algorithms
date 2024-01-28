package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RSA implements Encryption {
    private static final HashSet<Integer> prime = new HashSet<>();
    private static final Random random = new Random();
    private Long publicKey = null;
    private Long privateKey = null;
    private Long n = null;

    public RSA() {
        // will fill private_key, public_key, n
        setKeys();

    }

    public RSA(Long publicKey, Long privateKey, Long n) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.n = n;
    }

    @Override
    public String encrypt(String plainText) {
        // encode each letter in plainText

        List<String> encoded = new ArrayList<>();
        for (char letter : plainText.toCharArray()) {
            encoded.add(Long.toHexString(encode(letter)));
        }

        System.out.println("Encryption keys:");
        System.out.println("{public_key, private_key, n} = {" + publicKey + ", " + privateKey + ", " + n + "}");

        return encoded.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        // decode each number in cipherText
        String[] cipherTextTokens = cipherText.split(",");

        StringBuilder s = new StringBuilder();
        for (String num : cipherTextTokens) {
            s.append((char) (decode(Long.parseLong(num.trim(), 16))));
        }
        return s.toString();
    }

    private int pickRandomPrime() {
        int k = random.nextInt(2, 1000);
        while (!isPrime(k) && !prime.contains(k)) {
            k++;
        }
        prime.add(k);

        return k;
    }

    private boolean isPrime(int i) {
        int sqrt = (int) Math.sqrt(i);
        for (long j = 2; j <= sqrt; j++)
            if (i % j == 0) return false;

        return true;
    }

    private void setKeys() {
        long p = pickRandomPrime();
        long q = pickRandomPrime();

        n = p * q;
        long x = (p - 1) * (q - 1);

        long k1 = 2;
        while (gcd(k1, x) != 1) {
            k1 += 1;
        }

        publicKey = k1;

        long k2 = 2; // k2 is k1^-1
        while ((k2 * k1) % x != 1) {
            k2 += 1;
        }

        privateKey = k2;
    }

    private long encode(long message) {
        long e = publicKey;
        long encrypted_text = 1;
        while (e > 0) {
            encrypted_text *= message;
            encrypted_text %= n;
            e -= 1;
        }
        return encrypted_text;
    }

    private long decode(long encrypted_text) {
        long d = privateKey;
        long decrypted = 1;
        while (d > 0) {
            decrypted *= encrypted_text;
            decrypted %= n;
            d -= 1;
        }
        return decrypted;
    }

    private long gcd(long a, long b) {
        if (b == 0)
            return a;

        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        String coded = rsa.encrypt("Salah and mohammad");
        System.out.println("Initial message:");
        System.out.println("Salah and mohammad");
        System.out.println();
        System.out.println("Decrypted Message:");
        System.out.println(coded);
        System.out.println();
        System.out.println("Encrypted Message:");
        System.out.println(rsa.decrypt(coded.substring(1, coded.length() - 1)));
    }
}
