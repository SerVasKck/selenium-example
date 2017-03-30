package ru.stqa.training.selenium;
import org.junit.Before;
/**
 * Created by i.shapoval on 30.03.2017.
 */
public class TestBase {
    public static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
    if (tlApp.get() !=null){
        app = tlApp.get();
        return;
    }

    app = new Application();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {app.quit(); app = null; })  );
    }
}
