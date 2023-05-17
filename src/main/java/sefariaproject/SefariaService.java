package sefariaproject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sefariaproject.index.Content1;
import sefariaproject.text.Text;

import java.util.List;

public interface SefariaService {
    @GET("/api/index/")
    Observable<List<Content1>> getSefariaIndex();

    @GET("/api/texts/{ref}")
    Observable<Text> getSefariaText(@Path("ref") String ref);

    @GET("/api/index/titles/")
    Observable<Text> getSefariaTitles();


}
