/**
 * Created by ahmedyakout on 10/30/16.
 */
public class Test {


    static class A {
        String data;
        A (String data) {
            this.data = data;
        }
    }

    static void updateData(A a) {
       a.data = "updated";
    }

    public static void main(String[] args) {
        A a = new A("old");
        updateData(a);
        System.out.print(a.data);

    }

}
