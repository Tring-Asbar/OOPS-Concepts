import java.util.*;
class Student{
    private String name;
    private int rollno;
    private int marks;
    private int countOfSubjects;
    private int avg;
    private String grade;
    
    public Student(String name,int rollno){
        this.name=name;
        this.rollno=rollno;
    }
    public String getName(){
        return name;
    }
    public int getRollno(){
        return rollno;
    }
    public void addSubjectMark(String subject, int mark){
        marks+=mark;
        countOfSubjects+=1;
    }
    public int calcAverage(){
        avg = marks/countOfSubjects;
        return avg;
    }
    public String generateGrade(){
        if(avg>90){
            grade= "O";
            return grade;
        }
        else if(avg<=90 && avg>80){
            grade= "A+";
            return grade;
        }
        else if(avg<=80 && avg>70){
            grade= "A";
            return grade;
        }
        else if(avg<=70 && avg>60){
            grade= "B+";
            return grade;
        }
        else if(avg<=60 && avg>50){
            grade= "B";
            return grade;
        }
        else if(avg<=50 && avg>=45){
            grade= "C";
            return grade;
        }
        
        grade= "Fail!!, The Grade is not Generated";
        return grade;
        
    }
    public void display(){
        System.out.println("Student Name: "+name);
	    System.out.println("Student Roll Number: "+rollno);
		System.out.println("Student Average Mark: "+avg);
		System.out.println("Student Grade: "+grade);
		System.out.println();
    }
    
}
public class GradingSystem
{
	public static void main(String[] args) {
	    Scanner as = new Scanner(System.in);
	    int k=-1;
	    Student obj[] = new Student[100];
	    System.out.println("=====================Exam Grading System===================");
	    while(true){
	        System.out.println("1.Create Student Object\n2.Add Subject and Marks\n3.Calculate Average\n4.Generate Grade\n5.Display Student Details\n6.Exit\nEnter the Choice(1/2/3/4/5/6):");
	        switch(as.nextInt()){
	            case 1:
	                System.out.print("Enter Student Name and Roll Number: ");
	                obj[++k] = new Student(as.next(),as.nextInt());
	                System.out.println("--------Object Created Successfully----------");
	                break;
	            case 2:
                    System.out.println("Enter the number Of Subjects: ");
                    int num = as.nextInt();
                    for(int i=0;i<num;i++){
                        System.out.print("Enter Subject and Mark: ");
                        obj[k].addSubjectMark(as.next(),as.nextInt());
                    }
	                
	                System.out.println("------------Marks and Subjects are Added-----------");
	                break;
	            case 3:
	                obj[k].calcAverage();
	                System.out.println("--------------Average is Calculated--------------");
	                break;
	            case 4:
	                obj[k].generateGrade();
	                System.out.println("--------------Grade is generated----------------");
	                break;
	            case 5:
	                for(int i=0;i<=k;i++){
                        // System.out.println("Student Name: "+obj[i].getName());
                        // System.out.println("Student Roll Number: "+obj[i].getRollno());
                        // System.out.println("Student Average Mark: "+obj[i].avg);
                        // System.out.println("Student Grade: "+obj[i].grade);
                        // System.out.println();
                        obj[i].display();
	                }
	                break;
	            case 6:
	                System.out.println("=====================All the Best for your Academics===========================");
	                return;
	        }
	    }
	}
}