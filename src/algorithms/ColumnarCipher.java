package algorithms;

import java.util.ArrayList;
import java.util.List;

public class ColumnarCipher implements Encryption {
    private final List<Integer> keyOrder;

    public ColumnarCipher(String key) {
        // check if key is valid by check if each letter is unique
        for (int i = 0; i < key.length(); i++) {
            if (key.indexOf(key.charAt(i)) != key.lastIndexOf(key.charAt(i))) {
                throw new IllegalArgumentException("Each letter in key must be unique");
            }
        }

        keyOrder = new ArrayList<>(key.length());
        StringBuilder keyBuilder = new StringBuilder(key);

        for (int i = 0; i < key.length(); i++) {
            char min = Character.MAX_VALUE;
            int minKeyIndex = -1;
            int minBuilderIndex = -1;
            for (int j = 0; j < keyBuilder.length(); j++) {
                if (keyBuilder.charAt(j) < min) {
                    min = keyBuilder.charAt(j);
                    minBuilderIndex = j;
                    minKeyIndex = key.indexOf(min);
                }
            }
            keyBuilder.deleteCharAt(minBuilderIndex);
            keyOrder.add(minKeyIndex);
        }
    }

    @Override
    public String encrypt(String plainText) {
        char[][] matrix = new char[Math.ceilDiv(plainText.length(), keyOrder.size())][keyOrder.size()];
        int messageIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < keyOrder.size(); j++) {
                if (messageIndex < plainText.length()) {
                    matrix[i][j] = plainText.charAt(messageIndex++);
                } else {
                    matrix[i][j] = 'X';
                }
            }
        }


        StringBuilder cipherText = new StringBuilder();
        for (Integer columnIndex : keyOrder) {
            for (char[] chars : matrix) {
                cipherText.append(chars[columnIndex]);
            }
        }

        return cipherText.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        char[][] matrix = new char[Math.ceilDiv(cipherText.length(), keyOrder.size())][keyOrder.size()];
        int messageIndex = 0;

        for (Integer columnIndex : keyOrder) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][columnIndex] = cipherText.charAt(messageIndex++);
            }
        }

        StringBuilder plainText = new StringBuilder();
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar != 'X') {
                    plainText.append(aChar);
                }
            }
        }

        return plainText.toString();
    }

}
