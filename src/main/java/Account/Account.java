package Account;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Account {
    @Getter
    private String name; //имя владельца
    private Map<Currence, Integer> mapVal;
    private Deque<Command> commands  = new ArrayDeque<>();

    public Account(@NotNull String name) {
        if (name.isEmpty())  //(name.equals(null)||name.equals(""))
            throw new IllegalArgumentException(" Имя не может быть пустым");
        this.setName(name);
        this.mapVal = new HashMap<>();
       //this.commands = new ArrayDeque<>();

    }

    public void setName(@NotNull String name) {
        System.out.println("setName: " + name);
        if (name.isEmpty())  //(name.equals(null)||name.equals(""))
            throw new IllegalArgumentException(" Имя не может быть пустым");
        String oldName = this.name;

        this.commands.push(() -> {
            this.name = oldName;
        });
        this.name = name;
    }


//    public HashMap<Currence, Integer> getMapVal() {
//        return new HashMap<>(this.mapVal);
//    }

    public void addCur(@NotNull Currence cur, Integer collCur) {
        if (collCur < 0) throw new IllegalArgumentException("Не возможно добавить отрицательное число");
        if (mapVal.containsKey(cur)) {
            int vlcur = this.mapVal.get(cur);
            this.commands.push(() -> {
                this.mapVal.put(cur, vlcur);
            });
        } else {
            this.commands.push(() -> {
                this.mapVal.remove(cur);
            });

        }
        this.mapVal.put(cur, collCur);

    }

    public Account undo() throws NumberFormatException {
        if (commands.isEmpty()) throw new NullPointerException("Нет возможности откатится дальше");

        commands.pop().perform();

        System.out.println("--------this: " + this);
        return this;

    }

    private class Snapshot implements Leadable {
        private String name;
        private Map<Currence, Integer> mapVal;

        public Snapshot() {
            this.name = Account.this.name;
            this.mapVal = new HashMap<>(Account.this.mapVal);
        }

        @Override
        public void load() {
            Account.this.name = this.name;
            Account.this.mapVal = new HashMap<>(this.mapVal);
        }

    }

    public Leadable Save() {
        return new Snapshot();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(name, account.name) && Objects.equals(mapVal, account.mapVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mapVal);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", mapVal=" + mapVal +
                '}'
                ;
    }
}
