import java.util.*;
public class Hashmap
{
	public static void main(String[] args) {
	    Scanner as = new Scanner(System.in);
		Map<String,Integer> Interns = new HashMap<>();
		int size = as.nextInt();
		String names[] = new String[size];
		int ids[]  = new int[size];
		for(int i=0;i<size;i++){
		    names[i] = as.next();
		    ids[i] = as.nextInt();
		}
		for(int i=0;i<size;i++){
		    Interns.put(names[i],ids[i]);
		}
		String name = as.next();
		int id = as.nextInt();
		
		for(int i=0;i<size;i++){
		    if(Interns.containsKey(name) && id== Interns.get(name)){
		        System.out.print(name +"is Present");
		        break;
		    }
		}
		
		
	}
}