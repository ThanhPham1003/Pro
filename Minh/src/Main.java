public class Main {
    public static void main(String[] args) {
        Operations operations = new Operations();
        accountManager accountManager = new accountManager(operations);
        accountManager.interactiveOperations();
    }
}
