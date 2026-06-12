public class Course {

    private String courseName;
    private Double fee;
    private String trainerName;

    public Course(String courseName, Double fee, String trainerName) {
        this.courseName = courseName;
        this.fee = fee;
        this.trainerName = trainerName;
    }

    public String getCourseName() { return courseName; }
    public Double getFee() { return fee; }
    public String getTrainerName() { return trainerName; }
}