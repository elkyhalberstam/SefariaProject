package sefariaproject;

import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sefariaproject.text.Text;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SefariaServiceTest {

    @Test
    void getSefariaText() {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.sefaria.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        SefariaService service = retrofit.create(SefariaService.class);

        //when
        Text text = service.getSefariaText("gen").blockingFirst();

        //then
        assertNotNull(text);
        assertNotNull(text.ref);
    }
}