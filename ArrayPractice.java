import java.util.*;
public class ArrayPractice
{
	public static void main(String[] args) {
	    String Interns[] = {"Asbar","Kishore","Santhosh","Sabari","Abinaya","Aakash","Yuvan","Vignesh","Swatheeswari","Charanguru","Balaji"};
	    System.out.println(Interns[2]);
	    System.out.println(Interns[4]);
	    System.out.println(Interns[6]);
	    
	    String t=Interns[6];
	    Interns[6]=Interns[9];
	    Interns[9]=t;
	    
	    for(int i=0;i<11;i++){
	        System.out.println(Interns[i]);
	    }
		
		
	}
}