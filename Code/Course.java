public class Course {
    public String course; 
    public String coName;
    public String credit; 
    public int num; 
    public static int[] GraCount; 
    public static int highscore; 
    public static int lowscore; 
    public static double averscore;  
    public Course() {
        course = coName = credit = "";
        num = highscore = 0;
        lowscore = 100;
        averscore = 0.0;
        GraCount = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    public void getCourseData(String course) {
        coName = course.split(",")[0];
        credit = course.split(",")[1];
    }
} 