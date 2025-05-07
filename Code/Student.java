public class Student {
    public String stu; // raw data (String) of each row of individual student
    public String sur; // surname
    public String giv; // given name
    public String id;  // student's id
    public double score;  // student's score of one course
    public String Grade;  // Grade decided by score
    public double[] credit;  //each course credit the student get (must not be static!!!)
    public double GPA; // Grade Points Average
    public String[] takecourse; // all courses the student take
    public int[] courseCredit; // course credit
    
    
    // constructor
    public Student() {
        
        // initialization
         stu = sur = giv = id  = Grade;
         score = 0;
         GPA = 0.0;
         credit = new double[100];
         takecourse = new String[100];
         courseCredit = new int[100];
    }
    
    
    // get student's surname by splitting the string
    public static String getsur(String stu) {
        
        return stu.split(",")[0];
    }
    
    // get student's given name by splitting the string
    public static String getgiv(String stu) {
        
        return stu.split(",")[1];
    }
    
    // get student's id by splitting the string
    public static String getid(String stu) {
        
        return stu.split(",")[2];
    }
    
    // get student's score (using round method) by splitting the string
    public static int getscore(String stu) {
        
        String str = stu.split(",")[3]; // store the score string
        double sc = Double.parseDouble(str); // parse str into type double
      
        return (int) Math.round(sc); // return score in integer type
    }
    
    // compute Grade
    public void getGrade(double score) {
        
        if(score >=93 && score <= 100) { Grade = "A+"; ++Course.GraCount[0];}
        if(score >=88 && score <= 92) { Grade = "A"; ++Course.GraCount[1];}
        if(score >=83 && score <= 87) { Grade = "A-"; ++Course.GraCount[2];}
        if(score >=78 && score <= 82) { Grade = "B+"; ++Course.GraCount[3];}
        if(score >=72 && score <= 77) { Grade = "B"; ++Course.GraCount[4];} 
        if(score >=68 && score <= 71) { Grade = "B-"; ++Course.GraCount[5];}
        if(score >=63 && score <= 67) { Grade = "C+"; ++Course.GraCount[6];}
        if(score >=58 && score <= 62) { Grade = "C"; ++Course.GraCount[7];}
        if(score >=53 && score <= 57) { Grade = "C-"; ++Course.GraCount[8];}
        if(score >=50 && score <= 52) { Grade = "D"; ++Course.GraCount[9];} 
        if(score >=40 && score <= 49) { Grade = "F"; ++Course.GraCount[10];}
        if(score <= 39) { Grade = "O"; ++Course.GraCount[11];}
        
    }
    
    // combine all methods above
    public void getStuData(String stu) {
        sur = getsur(stu);
        giv = getgiv(stu);
        id = getid(stu);
        score = getscore(stu);
        getGrade(score);
    }
    
    
    // Grade parse to credit
    public double Grade2Credit(String Grade) {
        
        if(Grade.equals("A+")) {return 4.0;}
        else if(Grade.equals("A")) {return 3.7;}
        else if(Grade.equals("A-")) {return 3.3;}
        else if(Grade.equals("B+")) {return 3.0;}
        else if(Grade.equals("B")) {return 2.7;}
        else if(Grade.equals("B-")) {return 2.3;}
        else if(Grade.equals("C+")) {return 2.0;}
        else if(Grade.equals("C")) {return 1.7;}
        else if(Grade.equals("C-")) {return 1.3;}
        else if(Grade.equals("D")) {return 1.0;}
        else {return 0.0;}
        
    }
    
    
    // Credit parse to Grade
    public String Credit2Grade(Student ST, int index) {
        
        if(ST.credit[index] == 4.0) {return "A+";}
        else if(ST.credit[index] == 3.7) {return "A";}
        else if(ST.credit[index] == 3.3) {return "A-";}
        else if(ST.credit[index] == 3.0) {return "B+";}
        else if(ST.credit[index] == 2.7) {return "B";}
        else if(ST.credit[index] == 2.3) {return "B-";}
        else if(ST.credit[index] == 2.0) {return "C+";}
        else if(ST.credit[index] == 1.7) {return "C";}
        else if(ST.credit[index] == 1.3) {return "C-";}
        else if(ST.credit[index] == 1.0) {return "D";}
        else {return "Fail";}
        
    }
    
    // compute the GPA
    public double computeGPA(Student ST) {
        
        // initialization
        double sum = 0.0;
        int coursesum = 0;
        
        for(int i = 0; i < ST.takecourse.length; ++i) {
                        
            sum = sum + ST.credit[i] * ST.courseCredit[i];
            coursesum = coursesum + ST.courseCredit[i];
            
        }
        
        // change to .2f format
        double d = sum/coursesum;
        return (double) Math.round(d * 100) / 100;
        
    }
    
        
} // end the class