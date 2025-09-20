import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static public Random random = new Random();
    static public Scanner scanner = new Scanner(System.in);
    static UserAccounts[] user = new UserAccounts[100];
    static AdminAccounts[] admin = new AdminAccounts[10];
    static public int[] allUserID = new int[100];
    static public String[] allUserPass = new String[100];
    static public int[] allAdminID = new int[100];
    static public String[] allAdminPass = new String[100];
    static int currentUser;
    static int currentAdmin;



    static {
        admin[0] = new AdminAccounts("Admin1",123,"admin123");
        AdminAccounts.adminCount++;
    }


    public static void main(String[] args){



        int choice = -1;

        do{
            try {
                System.out.println("----- ABC Bank -----");
                System.out.println("1. Admin portal");
                System.out.println("2. User portal");
                System.out.println("3. Exit");
                System.out.println("--------------------");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){

                    case 1 -> adminPortal();
                    case 2 -> userPortal();
                    case 3 -> {
                        print();
                        System.out.println("Exiting the program❌");
                    }
                    default -> System.out.println("Invalid choice!!");

                }
            }catch (InputMismatchException e){
                System.out.println("Invalid choice!");
                scanner.nextLine();
            }


        }while (choice != 3);

    }

    public static void adminPortal(){


        if (adminLogin()){
            int choice = -1;
            do {
                try {
                    System.out.println("----- Admin portal------");
                    System.out.println("1. Add new admin");
                    System.out.println("2. Add new User");
                    System.out.println("3. Exit");
                    System.out.println("------------------------");
                    System.out.print("Enter a choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {

                        case 1 -> addAdmin();

                        case 2 -> addUser();

                        case 3 -> System.out.println("Exiting");

                        default -> System.out.println("Invalid choice");


                    }
                }catch (InputMismatchException e){
                    System.out.println("Invalid Choice.!!");
                    scanner.nextLine();
                }

            }while (choice !=3);
        }

    }

    public static boolean adminLogin(){

        int id;
        int adminCount = AdminAccounts.adminCount;
        String pass;

        System.out.print("Enter id: ");
        id = scanner.nextInt();
        scanner.nextLine();
        if (adminIDFound(id,adminCount)){
            System.out.print("Enter password: ");
            pass = scanner.nextLine();
            if (pass.equals(admin[currentAdmin].adminPass)){
                return true;
            }else {
                System.out.println("Wrong password!!");
            }
        }

        return false;
    }

    static boolean adminIDFound(int id,int count){
        for (int i = 0; i <= count ; i++) {

            if (admin[i].adminID == id){
                currentAdmin = i;
                return true;
            }

        }
        System.out.println("User ID not found!!❌");
        return false;
    }

    public static void addAdmin(){
        String adminName;
        String adminPassword;
        System.out.print("Enter name: ");
        adminName = scanner.nextLine();
        System.out.print("Enter password: ");
        adminPassword = scanner.nextLine();
        allAdminPass[AdminAccounts.adminCount] = adminPassword;
        admin[AdminAccounts.adminCount] = new AdminAccounts(adminName,adminPassword);
        System.out.println("Your Admin id is: " + admin[AdminAccounts.adminCount].generateID(random,allAdminID));
        AdminAccounts.adminCount++;
        scanner.nextLine();
    }

    public static void addUser(){
        String firstName;
        String lastName;
        String password;
        double openingBalance;
        System.out.print("Enter First name: ");
        firstName = scanner.nextLine();
        System.out.print("Enter Last name: ");
        lastName = scanner.nextLine();
        System.out.print("Enter Password: ");
        password = scanner.nextLine();
        allUserPass[UserAccounts.userCount] = password;
        System.out.println("Enter the opening balance of this account: ");
        openingBalance = scanner.nextDouble();

        user[UserAccounts.userCount] = new UserAccounts(firstName,lastName,password,openingBalance);
        System.out.println("Your UserID is: " + user[UserAccounts.userCount].generateID(random,allUserID));
        UserAccounts.userCount++;

    }

    public static void userPortal(){

        int choice = -1;
        double amount;

        if (loginSuccessful()){
            do {
                try {
                    System.out.println("----- User Portal -----");
                    System.out.println("1. Deposit Money");
                    System.out.println("2. Withdraw Money");
                    System.out.println("3. Exit");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice){
                        case 1 -> {
                            System.out.print("How much money you want to deposit: ");
                            amount = scanner.nextDouble();
                            user[currentUser].addMoney(amount);
                        }
                        case 2 ->{
                            System.out.print("How much money you want to withdraw: ");
                            amount = scanner.nextDouble();
                            user[currentUser].withdrawMoney(amount);
                        }
                        case 3 ->{

                        }
                        default -> System.out.println("Invalid Choice...!!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Invalid Choice.!!");
                    scanner.nextLine();
                }


            }while (choice != 3);
        }


    }

    static boolean loginSuccessful(){

        int id;
        String password;
        int userCount = UserAccounts.userCount;
        try {
            System.out.print("Enter your user id: ");
            id = scanner.nextInt();
            scanner.nextLine();
            if (currentUserFound(id,userCount)){
                System.out.print("Hello " + user[currentUser].firstName + " Enter your password: ");
                password = scanner.nextLine();
                if (password.equals(user[currentUser].password)){
                    System.out.println("Login Successful 🌟");
                    return true;
                }
                else {
                    System.out.println("Wrong Password!!❌");
                    return false;
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Id is invalid");
            scanner.nextLine();
        }


        return false;

    }

    static boolean currentUserFound(int id, int count){

        for (int i = 0; i <= count ; i++) {

            if (user[i].id == id){
                currentUser = i;
                return true;
            }

        }
        System.out.println("User ID not found!!❌");
        return false;
    }




    public static void print(){
        for(String pass : allAdminPass){
            System.out.println(pass);
        }
        for (String userPass : allUserPass){
            System.out.println(userPass);
        }
    }
}


//Program is kind of complete. But I have to make the admin login part and also have to make that every time I open the program the old data is not lost.
//for that I have to make a file for the admin and user information. I'll try to make that in the next LIVE.
//             Bye bye


