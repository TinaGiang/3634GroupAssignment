package au.edu.unsw.infs3634.signup.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SignupDao {



    @Insert
    void insertDetails(SignupTable data);

    @Query("SELECT * FROM SignupTable")
    List<SignupTable> getDetails();


    /*@Delete
    void deleteAllData();*/
}
