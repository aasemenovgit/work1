package Account;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Account {
   @Getter
    private String name; //имя владельца
    public String testtest;
    //private HashMap<Currence,Integer> mapVal = new HashMap<>();
    private Map<Currence,Integer> mapVal ;
   // private HashMap<Currence,Integer> mapVal;
    //private Stack<Account> st = new Stack<>();
    //private  ;


    public Leadable Save()
    {
        return  new Snapshot();
    }
    private class Snapshot implements Leadable
    {
        private String name;

        private Map<Currence,Integer> mapVal;

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
    private Deque<Command> commands = new ArrayDeque<>();
    public Account(@NotNull String name) {
        if (name.isEmpty())  //(name.equals(null)||name.equals(""))
            throw new IllegalArgumentException(" Имя не может быть пустым");
        this.setName(name);
        this.mapVal = new HashMap<>();

        /// mapVal = new HashMap<>();\

       // st.push((Account)0);
                //new Account(name));
    }

    public void setName( @NotNull String name) {
        System.out.println("setName: "+name);
        if (name.isEmpty())  //(name.equals(null)||name.equals(""))
            throw new IllegalArgumentException(" Имя не может быть пустым");
        String oldName =this.name;

        this.commands.push(()->{this.name =oldName;});
        this.name = name;
       // Account nt =new Account(name);
        //nt =this;
        //st.push(nt);
    }

   // public String getName() {
     //   return name;
       // }

    public HashMap<Currence, Integer> getMapVal() {
        return new HashMap<>(this.mapVal);

    }
    @Cache
    public void addCur(Currence cur, Integer collCur) {
        if(collCur < 0 ) throw new IllegalArgumentException("Не возможно добавить отрицательное число");
        //HashMap<Currence,Integer> oldMapVal = this.mapVal;
/*
        if(!mapVal.containsKey(cur))
        {mapVal.put(cur,collCur);
            this.commands.push(()->{this.mapVal.remove(cur);});
            System.out.println("comands1: "+commands.toString());
        }
        else {
            this.commands.push(()->{this.mapVal.replace(cur, mapVal.get(cur));});
            mapVal.replace(cur, collCur);
            System.out.println("comands1: "+commands.toString());

        }


 */
        if(mapVal.containsKey(cur))
        {
            int vlcur = this.mapVal.get(cur);
           // collCur
         //  this.commands.push(()->{this.mapVal.replace(cur,collCur,vlcur) /*.put(cur, collCur )*/;});
            this.commands.push(()->{this.mapVal.put(cur, vlcur );});
           // System.out.println(" mapVal.get(cur): "+ this.mapVal.get(cur)+ "  new: "+collCur);
           // System.out.println(" 1 : "+commands.toString());
        }
        else
        {
            this.commands.push(()->{this.mapVal.remove(cur);});
            //System.out.println(" 2 : "+commands.toString());
        }    //  this.commands.push(()->{this.mapVal = oldMapVal;});
        this.mapVal.put(cur,collCur);
       // System.out.println("this: "+this);
    }

    public Account undo() throws NumberFormatException {
        if(commands.isEmpty())  throw new NullPointerException("Нет возможности откатится дальше");
       //System.out.println("COMANG: "+this.commands.toString());
        commands.pop().perform();
          //this.commands.getLast();
        System.out.println("--------this: "+this);
        return this;

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
