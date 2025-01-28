class NegativeNumberException extends Exception{
    public NegativeNumberException(String message){
        super(message);
    }
}
public class ExceptionHandling{
    public static void main(String[] args){
        try{
            subtract(10,15);
        }
        catch(NegativeNumberException e){
            System.out.println("Error:"+e.getMessage());
        }
    }
    public static void subtract(int a,int b) throws NegativeNumberException{
            if(b>a)
            throw new NegativeNumberException("Number is Negative");
            else{
                System.out.println(a-b);
            }
        
    }
}