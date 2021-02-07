import java.util.ArrayList;
import java.time.LocalDateTime;  // import the LocalDate class
import java.io.*;
public class Transaction{
  String Sender;
  String Reciever;
  Double Amount;
  Double Balance;
  String transactionType;
  LocalDateTime dateTime;

  Transaction(String S, String R,Double A, Double B,String T)
  {
    this.Sender=S;
    this.Reciever=R;
    this.Amount=A;
    this.Balance=B;
    this.transactionType=T;
    this.dateTime=LocalDateTime.now();
  }

}
