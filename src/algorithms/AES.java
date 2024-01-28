package algorithms;

import math.MatrixOperations;
import math.MyMath;
import math.Printer;

public class AES implements Encryption {
    private final String[][][] keys;
    private static final String[][] S_BOX = {
            {"63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
            {"CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0"},
            {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
            {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
            {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
            {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
            {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
            {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
            {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
            {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
            {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
            {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
            {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
            {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
            {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
            {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}
    };
    private static final String[] rcon = {
            "01", "02", "04", "08", "10", "20", "40", "80", "1b", "36", "6c", "d8", "ab", "4d"
    };
    private static final String[][] inv_S_Box = {{"52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"}, {"7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb"}, {"54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e"}, {"08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25"}, {"72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92"}, {"6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84"}, {"90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06"}, {"d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b"}, {"3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73"}, {"96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e"}, {"47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b"}, {"fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4"}, {"1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f"}, {"60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef"}, {"a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61"}, {"17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"}};

    public AES(String hexInitialKey) {
        if (hexInitialKey.length() != 32) {
            throw new IllegalArgumentException("Key length must be 32 hex characters");
        }
        if (!hexInitialKey.matches("[0-9A-Fa-f]+")) {
            throw new IllegalArgumentException("Key must be in hex");
        }

        // 11 to store initial key and 10 for the 10 rounds
        keys = new String[11][4][4];

        // fill the first key
        MatrixOperations.fillStringInMatrix(hexInitialKey, keys[0]);

        // generate the keys
        keyGeneration();
        Printer.printKeys(keys, "Keys");

    }

    public static void main(String[] args) {
        String hexInitialKey = "2b7e151628aed2a6abf7158809cf4f3c"; // note that key is in hex
        String plainText = "3243f6a8885a308d313198a2e0370734";

        AES aes = new AES(hexInitialKey);


        String cipherText = aes.encrypt(plainText);
        System.out.println("Cipher text: " + cipherText);
        System.out.println("3925841D02DC09FBDC118597196A0B32".equals(cipherText));


        String decryptedText = aes.decrypt(cipherText);
        System.out.println("Decrypted text: " + decryptedText);
        System.out.println("3243f6a8885a308d313198a2e0370734".equals(decryptedText));
    }


    private void keyGeneration() {
        String[] word = new String[4];
        String[] firstColumn = new String[4]; // use it in xor to find the first word for the key


        // the bigger step for generate keys
        for (int i = 1; i < keys.length; i++) { // step for each key
            // step for each word in the key
            for (int row = 0; row < 4; row++) {
                word[row] = keys[i - 1][row][(keys.length - 8)]; // get last column of previous key - for key length 11 column is 3
            }
            MyMath.wordsRotate(word);
            MatrixOperations.subByteArray(word);
            for (int row = 0; row < 4; row++) {
                firstColumn[row] = keys[i - 1][row][0]; // get first column of previous key
            }

            word = MyMath.XOR2Arrays(word, firstColumn);
            word[0] = MyMath.xor(word[0], rcon[i - 1]); // XOR the first value only

            for (int row = 0; row < 4; row++) {
                keys[i][row][0] = word[row];
            }

            for (int c = 1; c < (keys.length - 7); c++) { // for key length 11 column is 4 and 13 is 6 and 15 is 8
                for (int row = 0; row < 4; row++) {
                    keys[i][row][c] = MyMath.xor(keys[i - 1][row][c], keys[i][row][c - 1]);
                }
            }


        }

    }

    public String encrypt(String plainText) {
        String[][] plainTextMatrix = new String[4][4];

        // fill the plain text in matrix
        MatrixOperations.fillStringInMatrix(plainText, plainTextMatrix);

        // add round for the plain text
        plainTextMatrix = MatrixOperations.encryptAddRoundKey(keys[0], plainTextMatrix);

        for (int r = 0; r < 9; r++) {
            // 1 - SubBytes
            MatrixOperations.subByte(plainTextMatrix, S_BOX);
            Printer.printArray(plainTextMatrix, "After subBytes");

            // 2 - ShiftRows
            MatrixOperations.shiftRow(plainTextMatrix);
            Printer.printArray(plainTextMatrix, "After shiftRow");

            // 3 - MixColumns
            plainTextMatrix = MatrixOperations.mixColumn(plainTextMatrix);
            Printer.printArray(plainTextMatrix, "After mixColumns");

            // 4 - AddRoundKey
            plainTextMatrix = MatrixOperations.encryptAddRoundKey(keys[r + 1], plainTextMatrix);
            Printer.printArray(plainTextMatrix, "After AddRoundKey");

        }
        // 1 - SubBytes
        MatrixOperations.subByte(plainTextMatrix, S_BOX);
        Printer.printArray(plainTextMatrix, "After subBytes");

        // 2 - ShiftRows
        MatrixOperations.shiftRow(plainTextMatrix);
        Printer.printArray(plainTextMatrix, "After shiftRow");

        // 4 - AddRoundKey
        plainTextMatrix = MatrixOperations.encryptAddRoundKey(keys[10], plainTextMatrix);
        Printer.printArray(plainTextMatrix, "After AddRoundKey");


        return MatrixOperations.extractMatrix(plainTextMatrix);
    }

    public String decrypt(String cipherText) {
        String[][] cipherTextMatrix = new String[4][4];
        MatrixOperations.fillStringInMatrix(cipherText, cipherTextMatrix);
        Printer.printArray(cipherTextMatrix, "Cipher");

        // Add Round Key
        // cipher xor Key with the last key

        System.out.println("Round " + 10);
        cipherTextMatrix = MatrixOperations.decryptAddRoundKey(keys[10], cipherTextMatrix);
        Printer.printArray(cipherTextMatrix, "Key XOR cipher");


        for (int round = 9; round > 0; round--) {
            System.out.println("Round " + round);
            // invShiftRows
            MatrixOperations.shiftRowInverse(cipherTextMatrix);
            Printer.printArray(cipherTextMatrix, "After invShiftRow");

            // invSubBytes
            MatrixOperations.subByte(cipherTextMatrix, inv_S_Box);
            Printer.printArray(cipherTextMatrix, "After invSubBytes");

            // AddRoundKey
            cipherTextMatrix = MatrixOperations.decryptAddRoundKey(keys[round], cipherTextMatrix);
            Printer.printArray(cipherTextMatrix, "After AddRoundKey");

            // invMixColumns
            cipherTextMatrix = MatrixOperations.mixColumnInverse(cipherTextMatrix);
            Printer.printArray(cipherTextMatrix, "After invMixColumns");


        }

        System.out.println("Round " + 0);
        // InvShiftRows
        MatrixOperations.shiftRowInverse(cipherTextMatrix);
        Printer.printArray(cipherTextMatrix, "After invShiftRow");

        // invSubBytes
        MatrixOperations.subByte(cipherTextMatrix, inv_S_Box);
        Printer.printArray(cipherTextMatrix, "After invSubBytes");

        // AddRoundKey
        cipherTextMatrix = MatrixOperations.decryptAddRoundKey(keys[0], cipherTextMatrix);
        Printer.printArray(cipherTextMatrix, "After AddRoundKey");

        return MatrixOperations.extractMatrix(cipherTextMatrix);
    }


}