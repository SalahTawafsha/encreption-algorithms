package helpers;

import java.util.HashMap;

public class MyMath {
    public static String shiftBit(String b) {

        //System.out.println("Hex : " + b);
        String binary = hexToBinary(b);
        // 1B is value of p(x) - 00011011
        boolean need1B = binary.charAt(0) == '1';
        // System.out.println("Substring : " + binary.substring(1));

        binary = (binary.substring(1) + "0");

        // System.out.println("Binary : " + binary);

        return (need1B) ? xor("1B", binaryToHex(binary)) : binaryToHex(binary);
    }

    public static String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);

        StringBuilder bin = new StringBuilder(Integer.toBinaryString(i));
        System.out.println("Hex : " + hex + " Binary : " + bin);

        while (bin.length() < 8) {
            bin.insert(0, "0");
        }
        return bin.toString();
    }

    public static String xor(String a, String b) {
        long a1 = Long.parseLong(a, 16);
        long b1 = Long.parseLong(b, 16);

        long xor = a1 ^ b1;

        StringBuilder result = new StringBuilder();
        result.append(Long.toHexString(xor));
        while (result.length() < (Math.max(a.length(), b.length()))) {
            result.insert(0, "0");
        }

        return result.toString();
    }

    public static String binaryToHex(String binary) {

        HashMap<String, Character> hashMap = new HashMap<>();
        hashMap.put("0000", '0');
        hashMap.put("0001", '1');
        hashMap.put("0010", '2');
        hashMap.put("0011", '3');
        hashMap.put("0100", '4');
        hashMap.put("0101", '5');
        hashMap.put("0110", '6');
        hashMap.put("0111", '7');
        hashMap.put("1000", '8');
        hashMap.put("1001", '9');
        hashMap.put("1010", 'A');
        hashMap.put("1011", 'B');
        hashMap.put("1100", 'C');
        hashMap.put("1101", 'D');
        hashMap.put("1110", 'E');
        hashMap.put("1111", 'F');


        String bin;
        String hex = "";

        for (int i = 0; i < binary.length(); i = i + 4) {
            bin = binary.substring(i, i + 4);
            if (hashMap.containsKey(bin))
                hex += hashMap.get(bin);
            else {
                hex = "Invalid Binary String";
                return hex;
            }
        }
        return hex;
    }

    public static String getXORList(String input) {
        String[] inputTable = input.split("\\+");

        String result = inputTable[0];

        for (int i = 1; i < inputTable.length; i++) {
            result = xor(result, inputTable[i]);
        }

        return result;

    }

    public static void wordsRotate(String[] words) {
        String temp = words[0];
        for (int i = 0; i < words.length - 1; i++) {
            words[i] = words[i + 1];
        }
        words[words.length - 1] = temp;
    }


    public static String[] XOR2Arrays(String[] arr, String[] arr2) {
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            result[i] = MyMath.xor(arr[i], arr2[i]);
        }
        return result;
    }

    public static int getPositiveModulo(int number, int divisor) {
        return ((number % divisor) + divisor) % divisor;
    }


}
