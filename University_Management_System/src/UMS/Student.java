package UMS;

public class Student extends Person {
    public Student() {
    }

    public void register_student() {
        super.AddPersonData("student", "student_subject");
    }

    public void login_student() {
        super.LoginPerson("student", "student_subject");
    }
}
