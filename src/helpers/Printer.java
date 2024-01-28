package helpers;

public class Printer {
    public static void printKeys(String[][][] keys, String message) {
        System.out.println(message);
        for (int i = 0; i < keys.length; i++) {
            printArray(keys[i], "Key " + (i + 1) + " Round " + i);
        }
    }

    public static void printArray(String[][] arr, String message) {
        System.out.println(message);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
