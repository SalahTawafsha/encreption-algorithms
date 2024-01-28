package algorithms;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the message:");
//        String message = new Scanner(System.in).nextLine();
        String message = "Salah and mohammad";


        RSA rsa = new RSA();

        String coded = rsa.encrypt(message);

        System.out.println("Initial message:");
        System.out.println(message);

        System.out.println();

        System.out.println("Decrypted Message:");
        System.out.println(coded);

        System.out.println();

        System.out.println("Encrypted Message:");
        System.out.println(rsa.decrypt(coded.substring(1, coded.length() - 1)));
    }


}
