package sefariaproject.dagger;

import dagger.Component;
import sefariaproject.SefariaFrame;

import javax.inject.Singleton;

@Singleton
@Component(modules = {SefariaServiceModule.class})
public
interface SefariaProjectComponent {

    SefariaFrame providesSefariaFrame();

}
