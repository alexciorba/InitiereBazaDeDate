package ro.mycode.repository;

import ro.mycode.model.Elev;
import ro.mycode.model.Persoana;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ElevRepository {
    private String JdbcURL= "jdbc:mysql://localhost:3306/elev?" + "autoReconnect=true&useSSL=false";
    private String username="root";
    private String password="root";
    private Connection connection=null;
    private Statement statement=null;


    public ElevRepository(){

        try{
            connection= DriverManager.getConnection(JdbcURL,username,password);
            statement=connection.createStatement();

            System.out.println("s-a conectat");

        }catch (SQLException e){
            System.out.println("eroare conectare la baza de date");
        }
    }

    public void executeStatement(String execute){
        try{
            statement.execute(execute);

        }catch (SQLException exc){
            System.out.println("Nu am reusit" + execute);
        }
    }

    //todo : add to elev

    public void addElev(Elev elev){
        String insertTo="";
        insertTo+="insert into elevi ";
        insertTo+="(first_name,last_name,email,age) ";
        insertTo+="values(";
        insertTo+=String.format("'%s','%s','%s',%d",elev.getFirstName(),elev.getLastName(),elev.getEmail(),elev.getAge());
        insertTo+=")";
        executeStatement(insertTo);
    }

    //todo : delete from elev

    public void deleteElev(int age){
        String delete="";
        delete+=String.format("delete from elevi where age=%d",age);
        executeStatement(delete);
    }

    //todo : update first email


    public void update(Elev elev){
        String update=" update elevi ";
        update+= String.format("set first_name='%s', ",elev.getFirstName());
        update+= String.format("last_name='%s', ",elev.getLastName());
        update+= String.format("age=%d ",elev.getAge());
        update+=String.format("where email='%s'",elev.getEmail());
        executeStatement(update);

    }


    private ResultSet allElevi(){
        executeStatement("select * from elevi");
        try{
            return statement.getResultSet();

        }catch (Exception e){
            System.out.println("Nu s a executat schita");
            return null;
        }
    }

    public List<Elev> allElevs(){

        ResultSet set=allElevi();
        List<Elev>elevs=new ArrayList<>();
        try{
            while (set.next()){
                elevs.add(new Elev(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),Integer.parseInt(set.getString(5))));
            }
        }catch (Exception e){
            System.out.println("Nu s a creat lista");
        }
        return elevs;


    }










}
