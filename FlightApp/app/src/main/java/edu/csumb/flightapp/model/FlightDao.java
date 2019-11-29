package edu.csumb.flightapp.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.csumb.flightapp.model.User;


//TODO update this class to include
//   query method to read LogRecords
//      use the annotation @Query("select * from LogRecord order by datetime desc")
//   insert method to insert new LogRecord into database

@Dao
public interface FlightDao {

    @Query("select * from User")
    List<User> getAllUsers();

    @Query("select * from User where username = :username and password= :password")
    User login(String username, String password);

    @Query("select * from User where username = :username")
    User getUserByName(String username);

    @Insert
    void addUser(User user);


    @Query("select * from Flight")
    List<Flight> getAllFlights();

    @Query("select * from Flight where departure=:departure and arrival=:arrival")
    List<Flight> searchFlight(String departure, String arrival);

    @Insert
    void addFlight(Flight flight);

    @Update
    void updateFlight(Flight flight);

    @Query("select * from LogRecord order by time desc")
    List<LogRecord> getAllLogRecords();

    @Insert
    void addLogRecord(LogRecord rec);

    //@Insert
    //long addReservation(Reservation res);

    //TODO
    //@Delete
    //void deleteReservation(Reservation res);
    //Todo
    //@Query("select * from reservation where username = :name")
    //List<Reservation> getReservationForUser(String name);



}
