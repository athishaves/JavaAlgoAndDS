package inheritance;

public class Singer extends Person implements Artist, SomeInterface {

    @Override
    public void walk() {
        System.out.println("Singer walked");
    }

    public void sing() {
        System.out.println("Singer sung");
    }

    @Override
    public void aboutMe(String a) {
        System.out.println("I am an artist " + a);
    }

    @Override
    public void aboutMe() {
        System.out.println("Came from Some Interface");
    }
}
