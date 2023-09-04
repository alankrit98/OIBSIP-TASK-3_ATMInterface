import java.util.*;

class Account {
    String CustomerName;
    String AccountNumber;
    String username;
    String password;
    String TransactionHistory = "";
    int transaction = 0;
    float Balance = 1000000f;


    public void registration() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please Enter Your Name: \n");
        this.CustomerName = sc.nextLine();
        System.out.print("Please Enter Your Account Number: \n");
        this.AccountNumber = sc.nextLine();
        System.out.print("Please Enter Your UserName: \n");
        this.username = sc.nextLine();
        System.out.print("Please Enter Your Password: \n");
        this.password = sc.nextLine();
        System.out.println("\nYour Registration is Completed!!!!");
        System.out.println("Please Login to your Account\n");
    }


    public boolean login() {
        boolean newlogin = false;
        Scanner sc = new Scanner(System.in);
        while(!newlogin) {
            System.out.println("Please Enter your UserName: \n");
            String UserName = sc.nextLine();
            if(UserName.equals(username)) {
                while(!newlogin) {
                    System.out.println("Please Enter your Password: \n");
                    String PassWord = sc.nextLine();
                    if(PassWord.equals(password)) {
                        System.out.println("Login Successful!!!!\n");
                        newlogin = true;
                    } else {
                        System.out.println("Incorrect Password\n");
                    }
                }
            } else {
                System.out.println("Username not found\n");
                System.out.println("Kindly Register First\n");
            }
        }
        return newlogin;
    }


    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Amount to WithDraw: \n");
        float amount = sc.nextFloat();
        try {
            if(Balance >= amount) {
                transaction++;
                Balance += amount;
                System.out.println("WithDraw Successfully!!!!");
                String str = amount + " Rs WithDrawn by You\n";
                System.out.println("\n");
                TransactionHistory = TransactionHistory.concat(str);
            } else {
                System.out.println("Insufficient Balance for Withdrawnment\n");
            }
        } catch(Exception e) {
        }
    }


    public void Deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Amount you wan to Deposit: \n");
        float Amount = sc.nextFloat();
        try {
            if(Amount <= 1000000f) {
                transaction++;
                Balance += Amount;
                System.out.println("Your Amount is Successfully Deposited\n");
                String str = Amount + " Rs Deposited By You";
                TransactionHistory = TransactionHistory.concat(str);
            } else {
                System.out.println("Sorry, The Limit is 100000000.00");
            }
        } catch(Exception e) {
        }
    }


    public void Transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter the Name of the Receipent: \n");
        String receipent = sc.nextLine();
        System.out.println("Please Enter the Amount to Transfer: \n");
        float Amount = sc.nextFloat();
        try {
            if(Balance >= Amount) {
                if(Amount <= 500000f) {
                    transaction++;
                    Balance -= Amount;
                    System.out.println("Amount is Successfully Transferred to " + receipent + " Account");
                    System.out.println("\n");
                    String str = Amount + " Rs Transferred to " + receipent + "\n";
                    TransactionHistory = TransactionHistory.concat(str);
                } else {
                    System.out.println("Sorry... Limit is 500000.00");
                }
            } else {
                System.out.println("Balance is Insufficent\n");
            }
        } catch(Exception e) {
        }
    }


    public void checkBalance() {
        System.out.println(Balance + " Rs.");
    }


    public void TransHistory() {
        if(transaction == 0) {
            System.out.println("Your Transaction History is Empty\n");
        } else {
            System.out.println(TransactionHistory + "\n");
        }
    }
}
public class AtmInterface {
    public static int Input(int limit) {
        int input = 0;
        boolean key = false;
        while(!key) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                key = true;
                if((key && input > limit) || (input < 1)) {
                    System.out.println("Choose the Number between 1 to " + limit);
                    key = false;
                }
            } catch(Exception e) {
                System.out.println("Enter Only Integer Value");
                key = false;
            }
        }
        return input;
    }
    public static void main(String[] args) {
        System.out.println("\n******Welcome to NBI ATM******\n");
        System.out.println("1.Register");
        System.out.println("2.Exit");
        System.out.println("Please Enter your Choice: \n");
        int choiceuser = Input(2);
        if(choiceuser == 1) {
            Account user = new Account();
            user.registration();
            while(true) {
                System.out.println("Please Enter your Choice: \n");
                System.out.println("1.Login");
                System.out.println("2.Exit");
                int ch = Input(2);
                if(ch == 1) {
                    if(user.login()) {
                        System.out.println("\nWelcome!!!\n");
                        System.out.println(user.CustomerName);
                        boolean iscompleted = false;
                        while(!iscompleted) {
                            System.out.println("1.Transaction History");
                            System.out.println("2.Withdraw");
                            System.out.println("3.Deposit");
                            System.out.println("4.Transfer");
                            System.out.println("5.Check Bank Balance");
                            System.out.println("6.Exit");
                            System.out.println("\nPlease Enter your Choice: \n");
                            int c = Input(6);
                            switch(c) {
                                case 1:
                                user.TransHistory();
                                break;
                                case 2:
                                user.withdraw();
                                break;
                                case 3:
                                user.Deposit();
                                break;
                                case 4:
                                user.Transfer();
                                break;
                                case 5:
                                user.checkBalance();
                                break;
                                case 6:
                                iscompleted = true;
                                break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}