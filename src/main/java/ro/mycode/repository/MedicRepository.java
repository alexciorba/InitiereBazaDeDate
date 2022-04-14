package ro.mycode.repository;

import ro.mycode.model.Medic;
import ro.mycode.model.Persoana;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MedicRepository {

    private String JdbcURL= "jdbc:mysql://localhost:3306/medic?" + "autoReconnect=true&useSSL=false";
    private String username="root";
    private String password="root";
    private Connection connection=null;
    private Statement statement=null;

    public MedicRepository(){

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

    //todo : add to medic

    public void addMedic(Medic medic){
        String insertTo="";
        insertTo+="insert into medici ";
        insertTo+="(first_name,last_name,email,age) ";
        insertTo+="values(";
        insertTo+=String.format("'%s','%s','%s',%d",medic.getFirstName(),medic.getLastName(),medic.getEmail(),medic.getAge());
        insertTo+=")";
        executeStatement(insertTo);
    }

    //todo : delete from persoana

    public void deleteMedic(int age){
        String delete="";
        delete+=String.format("delete from medici where age=%d",age);
        executeStatement(delete);
    }
    //todo : update first email


    public void update(Medic medic){
        String update=" update medici ";
        update+= String.format("set first_name='%s', ",medic.getFirstName());
        update+= String.format("last_name='%s', ",medic.getLastName());
        update+= String.format("age=%d ",medic.getAge());
        update+=String.format("where email='%s'",medic.getEmail());
        executeStatement(update);

    }

    private ResultSet allMedici(){
        executeStatement("select * from medici");
        try{
            return statement.getResultSet();

        }catch (Exception e){
            System.out.println("Nu s a executat schita");
            return null;
        }
    }
    public List<Medic> allMedics(){

        ResultSet set=allMedici();
        List<Medic>medics=new ArrayList<>();
        try{
            while (set.next()){
                medics.add(new Medic(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),Integer.parseInt(set.getString(5))));
            }
        }catch (Exception e){
            System.out.println("Nu s a creat lista");
        }
        return medics;


    }

}
