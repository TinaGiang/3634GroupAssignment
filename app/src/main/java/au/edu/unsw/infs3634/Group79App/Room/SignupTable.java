package au.edu.unsw.infs3634.Group79App.Room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SignupTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "email")
    public String semail;

    @ColumnInfo(name = "first_name")
    public String sfirstname;

    @ColumnInfo(name = "last_name")
    public String slastname;

    @ColumnInfo(name = "password")
    public String spassword;

    @ColumnInfo(name = "dbscore")
    public int dbscore;



    public static int size() {
        return SignupTable.size();
    }

    public String getFirstName() {
        return sfirstname;
    }

    public void setFirstName(String sfirstname) {
        this.sfirstname = sfirstname;
    }

    public String getLastName() {
        return slastname;
    }

    public void setFLastName(String slastname) {
        this.slastname = slastname;
    }

    public String getEmail() {
        return semail;
    }

    public void setEmail(String semail) {
        this.semail = semail;
    }

    public String getPassword() {
        return spassword;
    }

    public void setPassword(String spassword) {
        this.spassword = spassword;
    }

    public int getDBscore() {
        return dbscore;
    }

    public void setDBscore(int dbscore) {
        this.dbscore = dbscore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
