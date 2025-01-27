class Bank{
    String name;
    double balance;
    String  accno;
    public Bank(String name,double balance,String accno){
        this.name=name;
        this.balance=balance;
        this.accno=accno;
    }
    public  String getName(){
        return name;
    }

    public  double getBalance(){
        return balance;
    }

    public  String getAccno(){
        return accno;
    }
    
    public void deposit(double amt){
        if(amt>0){
            balance+=amt;
            System.out.println("Balance is:"+balance);
        }
        else{
            System.out.print("It cannot be Deposited");
        }
    }
    public void withdraw(double amt){
        if(amt>0 && amt<=balance){
            balance-=amt;
            System.out.println("Balance is:"+balance);
        }
        else{
            System.out.print("Insufficient Balance");
        }
    }
    public void display(){
        System.out.print("All details are  displayed successfully");
    }
}
public class AmoutTransaction {
    public static void main(String[] args){
        Bank bank = new Bank("Asbar",1000.0,"23456789787");
        bank.deposit(500.0);
        bank.withdraw(100.0);
        System.out.print("Name is "+bank.getName());
        System.out.print("Balance is "+bank.getBalance());
        System.out.print("Account Number is "+bank.getAccno());
        System.out.println("Updated Balance is:"+bank.balance);
        bank.display();

    }
}
