package Account;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    public Account clone(Account ac) throws CloneNotSupportedException {
        return ac;
    }

    @Test
    void save() throws CloneNotSupportedException {

        Account acc = new Account("Initname");
        acc.addCur(Currence.EUR, 300);
        String oldname = acc.getName();
        Map mapval = new HashMap(acc.getMapVal());
        Leadable qs1 = acc.Save();
        acc.addCur(Currence.EUR, 500);
        acc.addCur(Currence.RUB, 300);
        acc.setName("newname");
        qs1.load();
        //acc.addCur(Currence.RUB, 300);

        assertEquals(oldname, acc.getName());
        assertEquals(mapval, acc.getMapVal());
    }

    @Test
    void setName() {
        Account acc = new Account("Initname");
        try {
            acc.setName(null);
            fail("Exception not thrown");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e);
            // System.out.println(e.getMessage());
        }

        try {
            acc.setName("");
            fail("Exception not thrown");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e);

        }

    }


    @Test
    void addCur() {
        Account acc = new Account("Initname");
        try {
            acc.addCur(Currence.EUR, -300);
            fail("Exception not thrown");
        } catch (final IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    void undo() {
        Account acc = new Account("Initname");
        String oldName = acc.getName();
        acc.setName("newname");
        String newName = acc.getName();
        acc.undo();
        String afternewname = acc.getName();
        assertEquals(oldName, afternewname);
    }


}