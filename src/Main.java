import algorithms.AES;
import algorithms.ColumnarCipher;
import algorithms.Encryption;
import algorithms.RSA;

import java.util.Scanner;

public class Main {
    private static final String CHOOSE_ALGORITHM = "1. AES\n2. RSA\n3. ColumnarCipher\n4. PlayFair\n5. Exit\nEnter your choice: ";
    private static final String ENCRYPT_OR_DECRYPTION = "1. Encrypt\n2. Decrypt\nEnter your choice: ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;
        Encryption encryptionAlgorithm;
        String key;
        int encryptOrDecrypt;

        do {
            try {
                System.out.printf(CHOOSE_ALGORITHM);
                choice = scanner.next().charAt(0);
                switch (choice) {
                    case '1':
                        encryptOrDecrypt = getEncryptOrDecrypt(scanner);
                        System.out.println("Enter a 32 char hex key:");
                        key = scanner.next();

                        if (key.length() != 32) throw new Exception("Key must be 32 chars long");
                        if (!key.matches("[0-9a-fA-F]+")) throw new Exception("Key must be a hex number");

                        if (encryptOrDecrypt == 1) {

                            System.out.println("Enter the plaintext:");
                            String plainText = scanner.next();

                            encryptionAlgorithm = new AES(key);
                            System.out.println("Encrypted message: " + encryptionAlgorithm.encrypt(plainText));
                        } else {

                            System.out.println("Enter the ciphertext:");
                            String cipherText = scanner.next();

                            encryptionAlgorithm = new AES(key);
                            System.out.println("Decrypted message: " + encryptionAlgorithm.decrypt(cipherText));
                        }
                        break;
                    case '2':
                        encryptOrDecrypt = getEncryptOrDecrypt(scanner);
                        if (encryptOrDecrypt == 1) {
                            System.out.println("Enter the plaintext:");
                            String plainText = scanner.next();
                            encryptionAlgorithm = new RSA();
                            System.out.println("Encrypted message: " + encryptionAlgorithm.encrypt(plainText));
                        } else {
                            System.out.println("Enter the key as \"public_key, private_key, n\":");
                            scanner.nextLine();
                            String line = scanner.nextLine();
                            System.out.println(line);
                            String[] keys = line.split(",");
                            encryptionAlgorithm = new RSA(Long.parseLong(keys[0].trim()), Long.parseLong(keys[1].trim()), Long.parseLong(keys[2].trim()));

                            System.out.println("Enter the ciphertext:");
                            String cipherText = scanner.nextLine();

                            System.out.println("Decrypted message: " + encryptionAlgorithm.decrypt(cipherText));
                        }
                        break;
                    case '3':
                        encryptOrDecrypt = getEncryptOrDecrypt(scanner);
                        System.out.println("Enter the key:");
                        key = scanner.next();
                        if (encryptOrDecrypt == 1) {
                            System.out.println("Enter the plaintext:");
                            String plainText = scanner.next();

                            encryptionAlgorithm = new ColumnarCipher(key);
                            System.out.println("Encrypted message: " + encryptionAlgorithm.encrypt(plainText));
                        } else {
                            System.out.println("Enter the ciphertext:");
                            String cipherText = scanner.next();

                            encryptionAlgorithm = new ColumnarCipher(key);
                            String decrypted = encryptionAlgorithm.decrypt(cipherText);
                            System.out.println("Decrypted message: " + decrypted);
                        }
                        break;
                    case '4':
                        break;
                    case '5':
                        scanner.close();
                        System.out.println("Thank you for using our program");
                    default:
                        System.out.println("Invalid choice, please enter a number between 1 and 5");

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }

    private static int getEncryptOrDecrypt(Scanner scanner) {
        System.out.printf(ENCRYPT_OR_DECRYPTION);
        int encryptOrDecrypt = scanner.nextInt();
        while (encryptOrDecrypt != 1 && encryptOrDecrypt != 2) {
            System.out.println("Invalid choice, please enter 1 or 2");
            System.out.printf(ENCRYPT_OR_DECRYPTION);
            encryptOrDecrypt = scanner.nextInt();
        }

        return encryptOrDecrypt;
    }
}