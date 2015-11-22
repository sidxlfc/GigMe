package b.loginapp;

/**
 * Created by Siddharth Shah on 9/18/2015.
 */
public class User
{
    String name, uname, pass;
    int age;

    public User(String name, String uname, String pass, int age)
    {
        this.name=name;
        this.uname=uname;
        this.pass=pass;
        this.age=age;
    }

    public User(String uname, String pass)
    {
        this.uname=uname;
        this.pass=pass;
        name="";
        age = -1;
    }
}
