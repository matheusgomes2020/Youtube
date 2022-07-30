package matheusgomes.youtubeapp.youtube.api;

import java.util.List;

import matheusgomes.youtubeapp.youtube.model.Resultado;
import matheusgomes.youtubeapp.youtube.model.Video;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YoutubeService {

    /*

    https://www.googleapis.com/youtube/v3/
    search
    ?part=snippet
    &order=date
    &maxResults=20
    &key=AIzaSyCWQSq_0nPtoqzOWfFeC7H2boNTUq2c_Rs
    &channelId=UCVHFbqXqoYvEWM1Ddxl0QDg
    &q=desenvolvimento+android

    https://www.googleapis.com/youtube/v3/search?part=snippet&order=date&maxResults=20&key=AIzaSyCWQSq_0nPtoqzOWfFeC7H2boNTUq2c_Rs&channelId=UCVHFbqXqoYvEWM1Ddxl0QDg

     */

    @GET("search")
    Call<Resultado> recuperarVideos(
            @Query("part") String part,
            @Query("order") String order,
            @Query("maxResults") String maxResults,
            @Query("key") String key,
            @Query("channelId") String channelId,
            @Query("q") String q
    );

}
