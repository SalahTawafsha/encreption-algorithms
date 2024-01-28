package math;

public class MatrixOperations {
    public static void fillStringInMatrix(String data, String[][] arr) {
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
}
