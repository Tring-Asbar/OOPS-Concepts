import java.util.*;

class Student {
    private String name;
    private int rollno;
    private int marks;
    int countOfSubjects;
    private int avg;
    private String grade;
    private Set<String> subjects;

    public Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
        this.subjects = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getRollno() {
        return rollno;
    }

    public void addSubjectMark(String subject, int mark) {
        subjects.add(subject);
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
    public void calcAllAverage(Student[] student, int k) {
        for (int i = 0; i <= k; i++) {
            if (student[i].getAverage() == 0) {
                if(student[i].countOfSubjects==0){
                    System.out.println("Marks and Subjects are not Added for "+student[i].getName()+". Add Subjects and Marks.");
                    addAllSubjectMarks(student[i]);
                    System.out.println("Subjects and marks Added for "+student[i].getName());
                }
                    student[i].calcAverage();
            }
        }
        System.out.println("Average is Calculated for All Students");
    }

    public void calcAllGrade(Student[] student, int k) {
        for (int i = 0; i <= k; i++) {
            if(student[i].getAverage()==0){
                calcAllAverage(student, k);
            }
            if (student[i].getGrade() == null) {
                student[i].generateGrade();
            }
        }
        System.out.println("Grade is Generated for All Students");
    }

    public boolean checkRollno(int rollno,Student[] student,int k){
        for(int i=0;i<=k;i++){
            if(student[i].getRollno()==rollno){
                return false;
            }
        }
        return true;
    }
    public void addAllSubjectMarks(Student student){
        Scanner as = new Scanner(System.in);
        Set<String> subjects = new HashSet<>();
        String subject;
        System.out.println("Enter the number Of Subjects: ");
        if (!as.hasNextInt()) {
            System.out.println("Invalid input! Number Of Subjects must be an integer.");
            as.next();
        }
        int numOfSubjects = as.nextInt();
            for(int i=0;i<numOfSubjects;i++){
                System.out.print("Enter Subject: ");
                subject = as.next();
                if(subjects.size()==0){
                    subjects.add(subject);
                }
                else if(subjects.contains(subject)){
                    System.out.println("This Subject is already Added");
                    i-=1;
                    continue;
                }
                else{
                    subjects.add(subject);
                }
                
                System.out.print("Enter Mark: ");
                if(!as.hasNextInt()){
                    System.out.println("Invalid input! Marks must be an integer.");
                    as.next();
                }
                int mark = as.nextInt();
                student.addSubjectMark(subject,mark);
            }   
    }

}

enum Choices{
    create,
    add,
    average,
    grade,
    display,
    exit;
}

public class GradingSystem {
    public static void main(String[] args) {
        Student student[] = new Student[100];
        Validation validate = new Validation();

	    Scanner as = new Scanner(System.in);
	    int studentCount=-1;
	    
	    System.out.println("=====================Exam Grading System===================");
	    while(true){
	        System.out.println("Create Student(create)\nAdd Subject and Marks(add)\nCalculate Average(average)\nGenerate Grade(grade)\nDisplay Student Details(display)\nExit(exit)\nEnter the Choice(create/add/average/grade/display/exit):");
            String choice=as.next().toLowerCase();
            try{
                Choices selectedChoice = Choices.valueOf(choice);

                switch(selectedChoice){
                    case create:
                        System.out.print("Enter Student Name: ");
                        String name = as.next();
                        System.out.print("Enter Roll Number: ");
                        if (!as.hasNextInt()) {
                            System.out.println("Invalid input! Roll Number must be an integer.");
                            as.next();
                        }
                        int rollno = as.nextInt();
                        boolean status = validate.checkRollno(rollno,student,studentCount);
                        if(status==false){
                            System.out.println("Roll Number is assigned to other Student");
                            break;
                        }
                        student[++studentCount] = new Student(name,rollno);
                        System.out.println("--------Student Added Successfully----------");
                        break;
                    case add:
                        try{
                            if(studentCount==-1){
                                throw new Exception("No Student Added. Create Student First");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        for(int i=0;i<=studentCount;i++){
                            if(student[i].countOfSubjects==0){
                                System.out.println("Add Subjects and marks for "+student[i].getName());
                                validate.addAllSubjectMarks(student[i]);
                                System.out.println("Subjects and marks Added for "+student[i].getName());
                            }
                        }
                        
                        System.out.println("------------Marks and Subjects are Added for All Students-----------");
                        break;
                    case average:
                        try{
                            if(studentCount==-1){
                                throw new Exception("No Student Added. Create Student First");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                            
                        }
                        validate.calcAllAverage(student,studentCount);
                        if(student[studentCount].getAverage()!=0)
                            System.out.println("--------------Average is Calculated--------------");
                        break;
                    case grade:
                        try{
                            if(studentCount==-1){
                                throw new Exception("No Student Added. Create Student First");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                            
                        }
                        validate.calcAllGrade(student,studentCount);
                        if(student[studentCount].getGrade()!=null)
                            System.out.println("--------------Grade is generated----------------");
                        break;
                    case display:
                        try{
                            if(studentCount==-1){
                                throw new Exception("No Student Added. Create Student First");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        for(int i=0;i<=studentCount;i++){
                            if(student[i].getGrade()==null){
                                validate.calcAllGrade(student,studentCount);
                            }
                            student[i].display();
                        }
                        break;
                    case exit:
                        System.out.println("=====================All the Best for your Academics===========================");
                        as.close();
                        return;
                    default:
                        System.out.println("Invalid Choice,Please Enter Any of the given Choices");
                        break; 
                }
            }
            catch(IllegalArgumentException e){
                System.out.println("Invalid Choice,Please Enter Any of the given Choices");
            }
	    }
    }
}