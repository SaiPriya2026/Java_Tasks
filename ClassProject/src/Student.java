

public class Student {



        int id;
        String name;
        String course;
        double feePaid;
        boolean active;

        Student(int id, String name, String course, double feePaid, boolean active) {
            this.id = id;
            this.name = name;
            this.course = course;
            this.feePaid = feePaid;
            this.active = active;
        }

        public String toString() {
            return id + " " + name + " " + course + " " + feePaid + " " + active;
        }
    }

