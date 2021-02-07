import java.util.ArrayList;
import java.util.*;
import java.io.*;


public class Bank {
    Scanner sc = new Scanner(System.in);

    public ArrayList < Customer > customers;
    public ArrayList < Transaction > transactions;
    public double minimumBalance;
    public Bank(double minimumBalance) {
        this.minimumBalance = minimumBalance;
        this.customers = new ArrayList < Customer > ();
        this.transactions = new ArrayList < Transaction > ();
    }

    public void addCustomer() {
			// this.customers.add(new Customer("ABC", "Nashik", 10000));
			// this.customers.add(new Customer("QRT", "Pune", 12000));
			// this.customers.add(new Customer("XYZ", "Mumbai", 13000));
        System.out.println("Thank you for opening account with us! Please enter your name");
        String customerName;
        customerName = sc.nextLine();
        System.out.println("Thanks! Now please enter your address");
        String customerAddress = sc.nextLine();
        System.out.println("Thanks! Now please enter initial deposit you want to make");
        double customerBalance = 0;
        boolean b = true;
        do {
            try {
                customerBalance = sc.nextDouble();
                sc.nextLine();
                if (customerBalance <= this.minimumBalance) {
                    throw new MinimumBalanceException(this.minimumBalance);
                }
                b = false;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input try again");
                sc.nextLine();
            } catch (MinimumBalanceException m) {
                System.out.println("Try increasing the amount");
            }
        } while (b);

        this.customers.add(new Customer(customerName, customerAddress, customerBalance));

        this.transactions.add(new Transaction(customerName, "Initial Deposit", customerBalance, customerBalance, "credit"));
    }

    public boolean printCustomers() {


        for (Customer c: this.customers) {
            if (c.accountStatus == 1) {
                System.out.println(c.customerName + c.customerBalance);

            }
        }
        System.out.println();
        return true;
    }
    public boolean printTransactions() {

			System.out.println("Enter Your Name");
			String Name = sc.nextLine();
			Customer c = this.findAccount(Name);
			if (c == null || c.accountStatus==0) {
					System.out.println("Not Found");
					return true;

			} else {
				System.out.println("Date Time\t\t\tUser\t Transaction Details\tType\t\t Amount \t\t Balance");
				int tsize=transactions.size();
				System.out.println(tsize);

        for (int i=tsize, j=10;i>0 && j>1;i--,j-- )
				{
					Transaction t= transactions.get(i-1);

					if(t.Sender.equals(Name))
            System.out.println(t.dateTime+" \t"+t.Sender + " \t"+t.Reciever +" \t" +t.transactionType+" \t\t\t"+ t.Amount+"\t\t "+ t.Balance );

        System.out.println();
}
        return true;
    }}



    public boolean closeAccount(String toBeDeleted) {
        Customer c = findAccount(toBeDeleted);
				if(c==null){
					System.out.print("This Account Does Not Exist");
					return false;
				}
				c.accountStatus = 0;

        return true;
    }
    public Customer findAccount(String toBeDeleted) {
        for (Customer c: this.customers) {
            if (c.customerName.equals(toBeDeleted)) {
                return c;
            }

        }
        System.out.println("User Not Present" + toBeDeleted);
        return null;

    }
    public boolean updateTransaction(String Sender, String Reciever, Double Amt, Double UpdatedBalance, String transactionType) {
        this.transactions.add(new Transaction(Sender, Reciever, Amt, UpdatedBalance, transactionType));

        return true;
    }
}
