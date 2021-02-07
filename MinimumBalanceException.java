class MinimumBalanceException extends Exception{
  MinimumBalanceException(){
    super("Minimum Balance Insufficient");
  }
  MinimumBalanceException(Double minBal){
    super("Minimum Balance must be always be equal to: "+minBal+" ,Hence! Transaction Declined ");
  }
}
