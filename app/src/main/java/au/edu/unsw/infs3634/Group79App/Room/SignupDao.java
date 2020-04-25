package au.edu.unsw.infs3634.Group79App.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SignupDao {

    @Insert
    void insertDetails(SignupTable data);

    @Query("SELECT * FROM SignupTable ORDER BY dbscore DESC")
    List<SignupTable> getDetails();




//    @Query("UPDATE SignupTable SET dbscore = 0")
    //   void updatescore (SignupTable dbscore);



    @Delete
    void deleteAllData(SignupTable data);
}
