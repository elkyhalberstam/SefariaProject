package sefariaproject;

import hu.akarnokd.rxjava3.swing.RxSwingPlugins;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import sefariaproject.text.Text;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextControllerTest {

    static {
        // This makes it so that our service returns immediately
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxSwingPlugins.setOnEdtScheduler(scheduler -> Schedulers.trampoline());    }

    @Test
    void updateText() {
        //given
        SefariaService service = mock();
        JTextArea textArea = mock();
        TextController controller = new TextController(service, textArea);
        Text text = new Text();
        text.text = new String[]{"In the beginning"};
        text.he = new String[]{"In the beginning"};
        Observable<Text> observable = Observable.just(text);
        doReturn(observable).when(service).getSefariaText("gen");

        //when
        controller.updateText("gen");

        //then
        verify(service).getSefariaText("gen");
        verify(textArea).setText("1) \n"
                + "In the beginning\n"
                + "In the beginning\n");
    }
}