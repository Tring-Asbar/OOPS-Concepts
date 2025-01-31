import java.util.*;

class Student {
    private String name;
    private int rollno;
    private int marks;
    private int countOfSubjects;
    private int avg;
    private String grade;

    public Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public int getRollno() {
        return rollno;
    }

    public void addSubjectMark(String subject, int mark) {
        marks += mark;
        countOfSubjects += 1;
    }

    public void calcAverage() {
        avg = marks / countOfSubjects;
    }

    public void generateGrade() {
        if (avg > 90) {
            grade = "O";
        } else if (avg <= 90 && avg > 80) {
            grade = "A+";
        } else if (avg <= 80 && avg > 70) {
            grade = "A";
        } else if (avg <= 70 && avg > 60) {
            grade = "B+";
        } else if (avg <= 60 && avg > 50) {
            grade = "B";
        } else if (avg <= 50 && avg >= 45) {
            grade = "C";
        } else
            grade = "Fail!!, The Grade is not Generated";

    }

    public String getGrade() {
        return grade;
    }

    public int getAverage() {
        return avg;
    }

    public void display() {
        System.out.println("Student Name: " + getName());
        System.out.println("Student Roll Number: " + getRollno());
        System.out.println("Student Average Mark: " + getAverage());
        System.out.println("Student Grade: " + getGrade());
        System.out.println();
    }

}

class Validation {
    public void calcAllAverage(Student obj[], int k) {
        
            for (int i = 0; i <= k; i++) {
                    if (obj[i].getAverage() == 0) {
                        obj[i].calcAverage();
                    }
            }
    }

    public void calcAllGrade(Student obj[], int k) {
        
        for (int i = 0; i <= k; i++) {
                if (obj[i].getGrade() == null) {
                    obj[i].generateGrade();
                }
        }
    }
}


public class GradingSystem {
    public static void main(String[] args) {
        Student obj[] = new Student[100];
        Validation validate = new Validation();

	    Scanner as = new Scanner(System.in);
	    int k=-1;
	    
	    System.out.println("=====================Exam Grading System===================");
	    while(true){
	        System.out.println("1.Create Student\n2.Add Subject and Marks\n3.Calculate Average\n4.Generate Grade\n5.Display Student Details\n6.Exit\nEnter the Choice(1/2/3/4/5/6):");
            if (!as.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid number.");
                as.next();
                
            }

            int choice=as.nextInt();
	        switch(choice){
	            case 1:
	                System.out.print("Enter Student Name: ");
                    String name = as.next();
                    System.out.print("Enter Roll Number: ");
                    if (!as.hasNextInt()) {
                        System.out.println("Invalid input! Marks must be an integer.");
                        as.next();
                         
                    }
                    int rollno = as.nextInt();
	                obj[++k] = new Student(name,rollno);
	                System.out.println("--------Object Created Successfully----------");
	                break;
	            case 2:
                    try{
                        if(k==-1){
                            throw new Exception("No Student Added. Create Student First");
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Enter the number Of Subjects: ");
                    if (!as.hasNextInt()) {
                        System.out.println("Invalid input! Marks must be an integer.");
                        as.next();
                        
                    }
                    int num = as.nextInt();
                    for(int i=0;i<num;i++){
                        System.out.print("Enter Subject: ");
                        String subject = as.next();
                        System.out.print("Enter Mark: ");
                        if (!as.hasNextInt()) {
                            System.out.println("Invalid input! Marks must be an integer.");
                            as.next();
                        }
                        int mark = as.nextInt();
                        obj[k].addSubjectMark(subject,mark);
                    }
	                
	                System.out.println("------------Marks and Subjects are Added-----------");
	                break;
	            case 3:
                    try{
                        if(k==-1){
                            throw new Exception("No Student Added. Create Student First");
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        
                    }
                    validate.calcAllAverage(obj,k);
                    
	                System.out.println("--------------Average is Calculated--------------");
	                break;
	            case 4:
                    try{
                        if(k==-1){
                            throw new Exception("No Student Added. Create Student First");
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        
                    }
                    validate.calcAllGrade(obj,k);
	                System.out.println("--------------Grade is generated----------------");
	                break;
	            case 5:
                    try{
                        if(k==-1){
                            throw new Exception("No Student Added. Create Student First");
                        }
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
	                for(int i=0;i<=k;i++){
                        
                        obj[i].display();
	                }
	                break;
	            case 6:
	                System.out.println("=====================All the Best for your Academics===========================");
	                return;
                default:
                    System.out.print("Invalid Choice,Please Enter Any of the given Choices");
                    break;
	        }
	    }
    
    }
}