package ru.stqa.training.selenium.testsTask19;
import org.junit.Before;

import ru.stqa.training.selenium.app.NewApplication;
/**
 * Created by i.shapoval on 30.03.2017.
 */
public class TestBase {
    public static ThreadLocal<NewApplication> tlApp = new ThreadLocal<>();
    public NewApplication app;

    @Before
    public void start() {
    if (tlApp.get() !=null){
        app = tlApp.get();
        return;
    }

    app = new NewApplication();
    tlApp.set(app);

    Runtime.getRuntime().addShutdownHook(
            new Thread(() -> {app.quit(); app = null; })  );
    }
}
