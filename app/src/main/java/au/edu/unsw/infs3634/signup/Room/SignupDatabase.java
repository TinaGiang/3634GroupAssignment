package au.edu.unsw.infs3634.signup.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SignupTable.class}, version = 2)
public abstract class SignupDatabase extends RoomDatabase {

    public abstract SignupDao signupDao();

}
