import java.util.*;
public class TestingShit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String targetRowStr, targetColStr;
        try {
            System.out.print("[+] Enter row: ");
            targetRowStr = input.next();
            Integer.parseInt(targetRowStr);
        } catch (NumberFormatException e) {
            System.out.println("Number only");
        };
    }
}
