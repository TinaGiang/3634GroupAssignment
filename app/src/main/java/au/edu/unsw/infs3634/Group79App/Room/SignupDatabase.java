package au.edu.unsw.infs3634.Group79App.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SignupTable.class}, version = 2)
public abstract class SignupDatabase extends RoomDatabase {

    public abstract SignupDao signupDao();

}
