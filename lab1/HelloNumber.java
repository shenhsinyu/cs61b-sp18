public class HelloNumber {
    public static void main(String[] args) {
        int i = 0;
        int x = 0;
        while (x < 45) {
            x = x + i;
            System.out.print(x + " ");
            i++;
        }
    }
}
// 0 1 3 6 10... and should end with 45.