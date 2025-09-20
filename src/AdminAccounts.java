import java.util.Random;

public class AdminAccounts {

    String adminName;
    int adminID;
    String adminPass;

    public static int adminCount = 0;

    public AdminAccounts(String adminName, int adminID, String adminPass) {
        this.adminName = adminName;
        this.adminID = adminID;
        this.adminPass = adminPass;
    }

    public AdminAccounts(String adminName, String adminPass) {
        this.adminName = adminName;
        this.adminPass = adminPass;
        System.out.println("Admin added 🌟");
    }
    public int generateID(Random random,int[] allAdminID){

        int id;
        boolean unique;

        do {
            id = random.nextInt(10000,99999);
            unique = true;

            for (int i = 0; i < adminCount; i++) {
                if (allAdminID[i] == id) {
                    unique = false;
                    break;
                }
            }

        }while (!unique);

        allAdminID[adminCount] = id;
        this.adminID = id;
        return id;

    }
}
