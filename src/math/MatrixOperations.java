package math;

public class MatrixOperations {
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
    private static final String[][] MIX_MATRICES = {
            {"02", "03", "01", "01"},
            {"01", "02", "03", "01"},
            {"01", "01", "02", "03"},
            {"03", "01", "01", "02"}
    };

    private static final String[][] INV_MIX_MATRICES = {{"0E", "0B", "0D", "09"}, {"09", "0E", "0B", "0D"}, {"0D", "09", "0E", "0B"}, {"0B", "0D", "09", "0E"}};

    public static void fillStringInMatrix(String data, String[][] arr) {
        if (data.length() > 32) {
            data = data.substring(0, 32);
        } else if (data.length() < 32) {
            int diff = 32 - data.length();
            data = data + "0".repeat(diff);
        }
        for (int i = 0; i < data.length(); ) {
            for (int j = 0; j < 4; j++) { // for cl
                for (int k = 0; k < 4; k++) { // for row
                    arr[k][j] = data.substring(i, i + 2); // arr[0][0],arr[1][0].....
                    i = i + 2;
                }
            }
        }

    }

    public static String extractMatrix(String[][] plainTextMatrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(plainTextMatrix[j][i]);
            }
        }
        return sb.toString();
    }

    public static void shiftRowInverse(String[][] input) {
        String buffer;

        // for loop for each row (we don't need 0 row)
        for (int i = 1; i < 4; i++) {

            for (int repeat = 0; repeat < i; repeat++) { // we represent the repeats

                buffer = input[i][3]; // buffer is the element I need to move
                for (int j = 2; j >= 0; j--) {
                    input[i][j + 1] = input[i][j]; // in the same row I move between columns
                }

                input[i][0] = buffer;
            }

        }

    }

    public static String[][] mixColumnInverse(String[][] input) {
        String[][] result = new String[4][4];
        StringBuilder c;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                c = new StringBuilder();
                for (int k = 0; k < 4; k++) {
                    c.append(getDecryptMulTable(INV_MIX_MATRICES[i][k], input[k][j])).append("+");
                }
                result[i][j] = MyMath.getXORList(c.toString());
            }
        }

        return result;
    }


    public static String getDecryptMulTable(String a, String b) {
        if (a.equals("0E")) {
            // x3+x2+x
            return MyMath.shiftBit(b) + "+" + MyMath.shiftBit(MyMath.shiftBit(b)) + "+" + MyMath.shiftBit(MyMath.shiftBit(MyMath.shiftBit(b)));
        }

        if (a.equals("0B")) {
            // x3+x+1
            return MyMath.shiftBit(MyMath.shiftBit(MyMath.shiftBit(b))) + "+" + MyMath.shiftBit(b) + "+" + b;
        }

        if (a.equals("0D")) {
            // x3+x2+1
            return MyMath.shiftBit(MyMath.shiftBit(MyMath.shiftBit(b))) + "+" + MyMath.shiftBit(MyMath.shiftBit(b)) + "+" + b;
        }

        if (a.equals("09")) {
            // x3+1
            return MyMath.shiftBit(MyMath.shiftBit(MyMath.shiftBit(b))) + "+" + b;
        }

        return null;
    }

    public static String[][] mixColumn(String[][] input) {
        String[][] result = new String[4][4];
        StringBuilder c;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                c = new StringBuilder();
                for (int k = 0; k < 4; k++) {
                    c.append(getEncryptMulTable(MIX_MATRICES[i][k], input[k][j])).append("+");
                }

                result[i][j] = MyMath.getXORList(c.toString());
            }
        }


        return result;
    }

    private static String getEncryptMulTable(String a, String b) {
        if (a.equals("01")) {
            // System.out.println("Hex : " + b);
            return b;
        }

        // 02 is x
        if (a.equals("02")) {
            //System.out.println("Hex : " + b);
            String binary = MyMath.hexToBinary(b);
            // 1B is value of p(x) - 00011011
            boolean need1B = binary.charAt(0) == '1';
            // System.out.println("Substring : " + binary.substring(1));

            binary = (binary.substring(1) + "0");

            // System.out.println("Binary : " + binary);

            return (need1B) ? MyMath.xor("1B", MyMath.binaryToHex(binary)) : MyMath.binaryToHex(binary);
        }

        // 03 is x+1
        if (a.equals("03")) {

            // b * 03 = b * 02 + b * 01
            //System.out.println("Hex : " + b);
            String binary = MyMath.hexToBinary(b);
            // 1B is value of p(x) - 00011011
            boolean need1B = binary.charAt(0) == '1';
            // System.out.println("Substring : " + binary.substring(1));
            binary = (binary.substring(1) + "0");
            // System.out.println("Binary : " + binary);

            String result = (need1B) ? MyMath.xor("1B", MyMath.binaryToHex(binary)) : MyMath.binaryToHex(binary);

            return MyMath.xor(result, b);
        }

        return null;
    }

    public static void subByte(String[][] input, String[][] sBox) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                int row = Integer.parseInt(input[i][j].substring(0, 1), 16);
                int column = Integer.parseInt(input[i][j].substring(1, 2), 16);

                input[i][j] = sBox[row][column];

            }
    }

    public static void shiftRow(String[][] input) {
        String e;

        // for loop for each row (we don't need 0 row)
        for (int i = 1; i < 4; i++) {

            for (int repeat = 0; repeat < i; repeat++) {

                e = input[i][0]; // e is the element I need to move
                for (int j = 1; j < 4; j++) {
                    input[i][j - 1] = input[i][j]; // in the same row I move between columns
                }

                input[i][3] = e;
            }

        }

    }


    public static String[][] encryptAddRoundKey(String[][] key, String[][] message) {
        String[][] result = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = MyMath.xor(key[i][j], message[i][j]);
                System.out.println(key[i][j] + " XOR " + message[i][j] + " = " + result[i][j]);
            }
        }

        return result;
    }

    public static String[][] decryptAddRoundKey(String[][] key, String[][] message) {
        String[][] result = new String[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = MyMath.xor(key[i][j], message[i][j]);
            }
        }
        return result;
    }

    public static void subByteArray(String[] input) {


        for (int i = 0; i < 4; i++) {
            int row = -1;
            int column = -1;

            switch (input[i].charAt(0)) {
                case '0' -> row = 0;
                case '1' -> row = 1;
                case '2' -> row = 2;
                case '3' -> row = 3;
                case '4' -> row = 4;
                case '5' -> row = 5;
                case '6' -> row = 6;
                case '7' -> row = 7;
                case '8' -> row = 8;
                case '9' -> row = 9;
                case 'A', 'a' -> row = 10;
                case 'B', 'b' -> row = 11;
                case 'C', 'c' -> row = 12;
                case 'D', 'd' -> row = 13;
                case 'E', 'e' -> row = 14;
                case 'F', 'f' -> row = 15;
            }

            switch (input[i].charAt(1)) {
                case '0' -> column = 0;
                case '1' -> column = 1;
                case '2' -> column = 2;
                case '3' -> column = 3;
                case '4' -> column = 4;
                case '5' -> column = 5;
                case '6' -> column = 6;
                case '7' -> column = 7;
                case '8' -> column = 8;
                case '9' -> column = 9;
                case 'A', 'a' -> column = 10;
                case 'B', 'b' -> column = 11;
                case 'C', 'c' -> column = 12;
                case 'D', 'd' -> column = 13;
                case 'E', 'e' -> column = 14;
                case 'F', 'f' -> column = 15;
            }
            input[i] = S_BOX[row][column];

        }

    }


}
