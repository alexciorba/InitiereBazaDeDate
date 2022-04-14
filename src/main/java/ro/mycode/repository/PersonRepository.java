package ro.mycode.repository;

import ro.mycode.model.Persoana;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private String JdbcURL= "jdbc:mysql://localhost:3306/persoana?" + "autoReconnect=true&useSSL=false";
    private String username="root";
    private String password="root";
    private Connection connection=null;
    private Statement statement=null;

    public PersonRepository(){

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

    //todo : add to persoana

    public void addPersoana(Persoana persoana){
        String insertTo="";
        insertTo+="insert into persoane ";
        insertTo+="(first_name,last_name,email,age) ";
        insertTo+="values(";
        insertTo+=String.format("'%s','%s','%s',%d",persoana.getFirstName(),persoana.getLastName(),persoana.getEmail(),persoana.getAge());
        insertTo+=")";
        executeStatement(insertTo);
    }


    //todo : delete from persoana

    public void deletePersoana(int age){
        String delete="";
        delete+=String.format("delete from persoane where age=%d",age);
        executeStatement(delete);
    }

    //todo : update first email


    public void update(Persoana persoana){
        String update=" update persoane ";
        update+= String.format("set first_name='%s', ",persoana.getFirstName());
        update+= String.format("last_name='%s', ",persoana.getLastName());
        update+= String.format("age=%d ",persoana.getAge());
        update+=String.format("where email='%s'",persoana.getEmail());
        executeStatement(update);

    }


    private ResultSet allPersoane(){
        executeStatement("select * from persoane");
        try{
            return statement.getResultSet();

        }catch (Exception e){
            System.out.println("Nu s a executat schita");
            return null;
        }
    }

    public List<Persoana> allPersons(){

        ResultSet set=allPersoane();
        List<Persoana>persoanas=new ArrayList<>();
        try{
            while (set.next()){
                persoanas.add(new Persoana(set.getInt(1),set.getString(2),set.getString(3),set.getString(4),Integer.parseInt(set.getString(5))));
            }
        }catch (Exception e){
            System.out.println("Nu s a creat lista");
        }
        return persoanas;


    }











}
