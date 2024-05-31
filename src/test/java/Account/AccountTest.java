package Account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    public Account clone(Account ac) throws CloneNotSupportedException {
        return ac;
    }

    @Test
    void save() throws CloneNotSupportedException {

        Account acc = new Account("Initname");
        Account acc2 = new Account("Initname");

        acc.addCur(Currence.EUR, 300);
        acc2.addCur(Currence.EUR, 300);

        Leadable qs1 = acc.Save();

        acc.setName("newname");

        acc.addCur(Currence.RUB, 4300);

        qs1.load();

        assertEquals(acc, acc2);


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