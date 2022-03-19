import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(String.format("%064x", new BigInteger(1, MessageDigest.getInstance("SHA-256").digest((new Scanner(System.in)).next().getBytes(StandardCharsets.UTF_8)))));
    }
    
}
