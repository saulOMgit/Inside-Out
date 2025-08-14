package dev.saul.controllers;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomeControllerTest {

    @Test
    public void constructor_debeInicializarDiarioController() {
        HomeController home = new HomeController();
        assertThat(home.getDiarioController(), is(notNullValue()));
    }
}
