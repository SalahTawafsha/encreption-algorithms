package algorithms;

public interface EncryptionAlgorithm {
    // encrypt String message
    String encrypt(String plainText);

    // decrypt List<Integer> message
    String decrypt(String cipherText);

}
