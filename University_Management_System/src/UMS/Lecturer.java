package UMS;

public class Lecturer extends Person {
    public Lecturer() {
    }

    public void register_lecturer() { super.AddPersonData("lecturer", "lecturer_subject"); }

    public void login_lecturer() {
        super.LoginPerson("lecturer", "lecturer_subject");
    }
}
