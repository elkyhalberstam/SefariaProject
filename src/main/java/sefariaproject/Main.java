package sefariaproject;


import sefariaproject.dagger.DaggerSefariaProjectComponent;
import sefariaproject.dagger.SefariaProjectComponent;

public class Main {
    public static void main(String[] args) {

        SefariaProjectComponent component = DaggerSefariaProjectComponent
                .builder()
                .build();
        SefariaFrame frame = component.providesSefariaFrame();
        frame.setVisible(true);

//        SefariaFrame frame = new SefariaFrame();
//        frame.setVisible(true);
    }
}