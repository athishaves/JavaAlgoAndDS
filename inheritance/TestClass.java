package inheritance;

public class TestClass {

    public static void testInheritance() {
        Person p = new Person();
        p.walk();

        Teacher t = new Teacher();      // Techer extends Person
        t.teach();
        t.walk();
    }


    public static void testUpCastClass() {
        // Up Casting

        Person person = new Teacher();
        person.walk();      // NOTE : The ouput will be "Teacher walked" and not "Person Walked"
//        p.teach();        This cannot be used now..

    }


    public static void testDownCastClass() {

        // Down Casting

        // Produces Compilation error
//        Person p = new Person();
//        Teacher t = p;

        // Produces Run Time error     (Class Cast Exception)
//        Person p = new Person();
//        Teacher t = (Teacher) p;

        // Correct way
        Teacher teacher = (Teacher) ((Person) new Teacher());
        teacher.walk(); teacher.teach();

        Teacher t = new Teacher();
        Person p = t;
        Teacher t2 = (Teacher) p;
        t2.teach();
        t2.walk();

        // Also not allowed     --> Run Time error      (Class Cast Exception)
//        Singer singer = new Singer();
//        Person person = singer;
//        Teacher teacher1 = (Teacher) person;
//        teacher1.teach();
//        teacher1.walk();

    }


    public static void testInterface() {
        // Singer inherits two interfaces where both contains method "aboutMe"
        Singer singer = new Singer();
        singer.aboutMe("Yayy!!");   // Singer Interface
        singer.aboutMe();   // SomeInterface interface
        singer.sing();  singer.walk();

        System.out.println("--------");

        // All Rounder extends Singer
        AllRounder allRounder = new AllRounder();
        allRounder.aboutMe();   allRounder.aboutMe("Blah Blah");
        allRounder.sing();  allRounder.walk();
    }




}
