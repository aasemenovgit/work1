package Account;

import java.lang.reflect.Field;

public class Start {
    public static void main(String[] args) {
        Account acc = new Account("Feodr");


        acc.addCur(Currence.USD,5);
        acc.addCur(Currence.RUB,105);
        System.out.println(acc);
        acc.addCur(Currence.USD,12);
        System.out.println(acc);

        acc.addCur(Currence.RUB,99);

        System.out.println(acc);

        acc.setName("Pasha");
        Leadable qs1 = acc.Save();
        System.out.println(acc);

        acc.undo();
        System.out.println(acc);

        acc.undo();
        System.out.println(acc);
        acc.undo();
        System.out.println(acc);
        acc.undo();
        System.out.println(acc);
        acc.undo();
        System.out.println(acc);

        qs1.load();
        System.out.println(acc);



    }
}
