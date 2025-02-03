import java.util.*;

class Student {
    private String name;
    private int rollno;
    private int totalMarks;
    int countOfSubjects;
    private int avg;
    private String grade;
    private Set<String> subjects;
    private List<Integer> marks;

    public Student(String name, int rollno) {
        this.name = name;
        this.rollno = rollno;
        this.subjects = new LinkedHashSet<>();
        this.marks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getRollno() {
        return rollno;
    }

    public void addSubjectMark(String subject, int mark) {
        subjects.add(subject);
        marks.add(mark);
        totalMarks += mark;
        countOfSubjects += 1;
    }

    public void calcAverage() {
        avg = totalMarks / countOfSubjects;
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
            grade = "Fail!!, Not eligible to receive the grade";

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
        int subjectNum=0;
        for(String subject:subjects){
            System.out.println(subject+"-"+marks.get(subjectNum));
            subjectNum++;
        }
        System.out.println("Student Average Mark: "+ (getAverage()==0?"--":getAverage()));
        System.out.println("Student Grade: "+(getGrade()==null?"--":getGrade()));
        System.out.println();
    }

}

class Validation {
    public void calcAllAverage(Student[] student, int numOfStudents) {
        for (int i = 0; i <= numOfStudents; i++) {
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

    public void calcAllGrade(Student[] student, int numOfStudents) {
        for (int i = 0; i <= numOfStudents; i++) {
            if(student[i].getAverage()==0){
                calcAllAverage(student, numOfStudents);
            }
            if (student[i].getGrade() == null) {
                student[i].generateGrade();
            }
        }
        System.out.println("Grade is Generated for All Students");
    }

    public boolean checkRollno(int rollno,Student[] student,int numOfStudents){
        for(int i=0;i<=numOfStudents;i++){
            if(student[i].getRollno()==rollno){
                return false;
            }
        }
        return true;
    }
    public void addAllSubjectMarks(Student student){
        Scanner scanner = new Scanner(System.in);
        Set<String> subjects = new HashSet<>();
        String subject;
        System.out.println("Enter the number Of Subjects: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Number Of Subjects must be an integer.");
            scanner.next();
        }
        int numOfSubjects = scanner.nextInt();
            for(int i=0;i<numOfSubjects;i++){
                System.out.println("Enter Subject: ");
                subject = scanner.next();
                if(!validateInput(subject)){
                    i-=1;
                    continue;
                }
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
                
                System.out.println("Enter Mark(For 100): ");
                while(!scanner.hasNextInt()){
                    System.out.println("Invalid input! Marks must be an integer.\nEnter Mark:");
                    scanner.next();
                }
                int mark = scanner.nextInt();
                mark=validateMark(scanner,mark);
                
                student.addSubjectMark(subject,mark);

            }   
    }
    public boolean validateInput(String input){
        
        while(!input.matches("[a-zA-Z]+")){
            System.out.println("Invalid Input");
            return false;
        }
        return true;
    }
    public int validateMark(Scanner scanner,int mark){
        while(mark>100 || mark<0){
            System.out.println("Mark should be 0 to 100");
            System.out.print("Enter Mark: ");
            mark = scanner.nextInt();
        }
        return mark;
    }

}


enum Choices {
    CREATE {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            context.create(scanner);
        }
    },
    ADD {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            context.subjectMarks(scanner);
        }
    },
    AVERAGE {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            context.average();
        }
    },
    GRADE {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            context.grade();
        }
    },
    DISPLAY {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            context.displayStudents();
        }
    },
    EXIT {
        @Override
        public void execute(Scanner scanner, GradingContext context) {
            System.out.println("========================All the best for your Academics==============================");
            System.exit(0);
        }
    };

    // Abstract method for execution
    public abstract void execute(Scanner scanner, GradingContext context);
}

class GradingContext{
    Student student[] = new Student[100];
    Validation validate = new Validation();
    int studentCount=-1;
    public void create(Scanner scanner){
        System.out.print("Enter Student Name: ");
        String name = scanner.next();
        while(!validate.validateInput(name)){
            System.out.print("Enter Student Name: ");
            name = scanner.next();
        }
        System.out.print("Enter Roll Number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Roll Number must be an integer.");
            scanner.next();
        }
        int rollno = scanner.nextInt();
        boolean status = validate.checkRollno(rollno,student,studentCount);
        if(!status){
            System.out.println("Roll Number is assigned to other Student");
            return;
        }
        student[++studentCount] = new Student(name,rollno);
        System.out.println("--------Student Added Successfully----------");
    }
    public void subjectMarks(Scanner choiceInput){
        try{
            if(studentCount==-1){
                throw new Exception("Students are not yet created, please create and check");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        for(int i=0;i<=studentCount;i++){
            if(student[i].countOfSubjects==0){
                System.out.println("Enter Subjects and marks for "+student[i].getName());
                validate.addAllSubjectMarks(student[i]);
                System.out.println("Subjects and marks Added for "+student[i].getName());
            }
        }
        
        System.out.println("------------Marks and Subjects are Added for All Students-----------");
    }
    public void average(){
        try{
            if(studentCount==-1){
                throw new Exception("Students are not yet created, please create and check");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
            
        }
        validate.calcAllAverage(student,studentCount);
        if(student[studentCount].getAverage()!=0)
            System.out.println("--------------Average is Calculated--------------");
    }
    public void grade(){
        try{
            if(studentCount==-1){
                throw new Exception("Students are not yet created, please create and check");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
            
        }
        validate.calcAllGrade(student,studentCount);
        if(student[studentCount].getGrade()!=null)
            System.out.println("--------------Grade is generated----------------");
    }
    public void displayStudents(){
        try{
            if(studentCount==-1){
                throw new Exception("Students are not yet created, please create and check");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        for(int i=0;i<=studentCount;i++){
            student[i].display();
        }
    }
}

public class GradingSystem {
    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    GradingContext context = new GradingContext();
	    
	    System.out.println("=====================Exam Grading System===================");

	    while(true){
	        System.out.println("Create Student(create)");
            System.out.println("Add Subject and Marks(add)");
            System.out.println("Calculate Average(average)");
            System.out.println("Generate Grade(grade)");
            System.out.println("Display Student Details(display)");
            System.out.println("Exit(exit)");
            System.out.println("Enter the Choice(create/add/average/grade/display/exit)");
            
            try{
                String choice=scanner.next().trim().toUpperCase();
                Choices selectedChoice = Choices.valueOf(choice);
                selectedChoice.execute(scanner, context);
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Invalid Choice. Please enter a valid option.");
            }
	    }
        
    }
}