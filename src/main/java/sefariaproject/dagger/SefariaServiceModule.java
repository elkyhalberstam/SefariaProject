package sefariaproject.dagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sefariaproject.SefariaService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.*;

@Module
public class SefariaServiceModule {

        @Provides
        public SefariaService providesSefariaService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.sefaria.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            SefariaService service = retrofit.create(SefariaService.class);
            return service;
        }


    @Provides
        @Named("textArea")
        @Singleton
        public JTextArea providesTextArea() {
            return new JTextArea();
        }
}