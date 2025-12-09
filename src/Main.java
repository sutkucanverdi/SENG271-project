public class Main {
    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Lütfen tüm dosya isimlerini giriniz!");
            return;
        }

        String userFile = args[0];
        String itemFile = args[1];
        String commandFile = args[2];

        // verielri al
        MyBazaar.initializeData(userFile, itemFile);
        
        // işle
        MyBazaar.processCommands(commandFile);
    }
}