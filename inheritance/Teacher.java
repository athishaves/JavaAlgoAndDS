package inheritance;

public class Teacher extends Person {

    @Override
    public void walk() {
        System.out.println("Teacher walked");
    }

    public void teach() {
        System.out.println("Teacher taught");
    }

}
