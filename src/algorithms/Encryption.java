package algorithms;

public interface Encryption {
    // encrypt String message
    String encrypt(String plainText);

    // decrypt List<Integer> message
    String decrypt(String cipherText);

}
