package au.edu.unsw.infs3634.Group79App;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HolderApi {

    @GET("api.php?amount=1&type=boolean")
    Call<Quiz> getPosts();
}