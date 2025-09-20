import java.util.Random;

public class UserAccounts {

    String firstName;
    String lastName;
    int id;
    String password;
    double openingBalance;

    public static int userCount = 0;

    public UserAccounts(String firstName, String lastName, String password,double openingBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.openingBalance = openingBalance;
        System.out.println("Account Created ✨");
    }

    void addMoney(double amount){
        if (amount<0){
            System.out.println("Amount can not be negative!!");
        }else {
            this.openingBalance += amount;
            System.out.println("Deposit Successful");
            System.out.println("New balance: " + this.openingBalance);
        }
    }
    void withdrawMoney(double amount){

        if (amount > this.openingBalance){
            System.out.println("insufficient Balance...!! ");
        } else if (amount<0) {
            System.out.println("Amount can not be negative!!");
        }else {
            this.openingBalance -= amount;
            System.out.println("Withdraw Successful");
            System.out.println("New balance: " + this.openingBalance);
        }
    }

    public int generateID(Random random, int[] allUserID){

        int id;
        boolean unique;

        do {
            id = random.nextInt(10000,99999);
            unique = true;

            for (int i = 0; i < userCount; i++) {
                if (allUserID[i] == id) {
                    unique = false;
                    break;
                }
            }

        }while (!unique);

        allUserID[userCount] = id;
        this.id = id;
        return id;

    }


}
