import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Driver {

    public static Bank bank = new Bank(1000.0);
    public static void main(String Args[]) {

        Scanner sc = new Scanner(System.in);

        int menuOption = 0;
        do {
            System.out.println("1.Open Account\n2.Close Account\n3.Withdraw\n4.Deposit\n5.Tramsfer\n6.Print Last 10 Transactions\n7.Exit");
            boolean b = true;
            do {
                try {
                    menuOption = sc.nextInt();
                    b = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input try again");
                    sc.next();
                }
            } while (b);
            sc.nextLine();

            switch (menuOption) {
                case 1:
                    bank.addCustomer();
                    break;
                case 2:
                    closeAccount();
                    break;
                case 3:
                    addTransaction(1);
                    break;
                case 4:
                    addTransaction(2);
                    break;
                case 5:
                    addTransaction(3);
                    break;
                case 6:
                    bank.printTransactions();
                    break;
            }

        } while (menuOption != 7);

    }


    public static void closeAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("We regret saying bye to you! Are you sure ?\n Enter Y to close, Enter N to cancel");
        char userInput = sc.next().charAt(0);
        sc.nextLine();
        if (userInput == 'Y' || userInput == 'y') {
            System.out.println("Enter Your Name");
            String toBeDeleted = sc.nextLine();
            if(bank.closeAccount(toBeDeleted))
            System.out.println("Account Closed Successfully");
        }
    }
    public static boolean addTransaction(int transactionType) {
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        if (transactionType == 1) {
            System.out.println("Enter Your Name");
            String Name = sc.nextLine();
            Customer c = bank.findAccount(Name);
            if (c == null || c.accountStatus==0) {
                System.out.println("Not Found");
                return true;

            } else {
                double withdrawAmt = 0;
                System.out.println("Enter amount to be withdrawn");
                do {
                    try {
                        withdrawAmt = sc.nextDouble();
                        if (c.customerBalance < withdrawAmt) {
                            throw new InsufficientBalanceException();
                        }
                        if ((c.customerBalance - withdrawAmt) <= bank.minimumBalance) {
                            throw new MinimumBalanceException(bank.minimumBalance);
                        }
                        b = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input try again");
                        sc.next();
                    } catch (InsufficientBalanceException e) {
                        System.out.println("Insufficient Balance Please Try With Different Amount");
                    } catch (MinimumBalanceException m) {
                        System.out.println("Minimum Balance Wont Be Maintained Please Try With Different Amount");
                    }
                } while (b);
                sc.nextLine();
                c.customerBalance -= withdrawAmt;
                bank.updateTransaction(c.customerName, "Self Withdraw", withdrawAmt, c.customerBalance, "debit"); //
                System.out.println("Withdrawal Successfully Completed");

            }
        }
        if (transactionType == 2) {
            System.out.println("Enter Your Name");
            String Name = sc.nextLine();
            Customer c = bank.findAccount(Name);
            if (c == null || c.accountStatus==0) {
                System.out.println("Not Found or The Account is Closed");
                return true;

            } else {
							System.out.println("Enter amount to be Deposited");

							double deptAmt =0;
							do {
									try {
											deptAmt = sc.nextDouble();
											b = false;
									} catch (InputMismatchException e) {
											System.out.println("Invalid input try again");
											sc.next();
									}
							} while (b);
								sc.nextLine();

                c.customerBalance += deptAmt;
                bank.updateTransaction(c.customerName, "Self Deposit", deptAmt, c.customerBalance, "credit");
                System.out.println("Deposit Successfully Completed");

            }
        }
        if (transactionType == 3) {
            System.out.println("Enter Sender's Name");
            String senderName = sc.nextLine();
            System.out.println("Enter Reciever's Name");
            String recieverName = sc.nextLine();
            System.out.println("Enter Amount");
            double amount =0;
            Customer sender = bank.findAccount(senderName);
            Customer reciever = bank.findAccount(recieverName);
            if (sender == null || reciever == null || sender.accountStatus==0||reciever.accountStatus==0) {
                System.out.println("Incorrect reciever or sender. \n Or \n Either of the accounts must have been closed");
                return true;
            } else {
								do {
                    try {
                        amount = sc.nextDouble();
                        if (sender.customerBalance < amount) {
                            throw new InsufficientBalanceException();
                        }
												if(sender.customerBalance-amount<=bank.minimumBalance)
 												{
                            throw new MinimumBalanceException(bank.minimumBalance);
                        }
                        b = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input try again");
                        sc.next();
                    } catch (InsufficientBalanceException e) {
                        System.out.println("Insufficient Balance Please Try With Different Amount");
                    } catch (MinimumBalanceException m) {
                        System.out.println("Minimum Balance Wont Be Maintained Please Try With Different Amount");
                    }
                } while (b);
                sc.nextLine();
                sender.customerBalance -= amount;
                reciever.customerBalance += amount;
                bank.updateTransaction(sender.customerName, " Amount Sent To: "+ reciever.customerName, amount, sender.customerBalance, "debit");
                bank.updateTransaction(reciever.customerName, " Amount Recieved From: "+ sender.customerName, amount, reciever.customerBalance, "credit");
								System.out.println("Transfer Successfully Completed");
            }
        }

        return true;


    }


}
