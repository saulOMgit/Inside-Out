package dev.saul.models;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//estaria bien test de .count()
public class EmocionTest {

    @Test
    public void debeContenerTodasLasEmocionesEnOrden() {
        EmocionEnum[] emocionesEsperadas = {
            EmocionEnum.ALEGRIA,
            EmocionEnum.TRISTEZA,
            EmocionEnum.IRA,
            EmocionEnum.ASCO,
            EmocionEnum.MIEDO,
            EmocionEnum.ANSIEDAD,
            EmocionEnum.ENVIDIA,
            EmocionEnum.VERGUENZA,
            EmocionEnum.ABURRIMIENTO,
            EmocionEnum.NOSTALGIA
        };

        assertThat(EmocionEnum.values(), is(emocionesEsperadas));
    }

    @Test
    public void valueOfDebeRetornarLaEmocionCorrecta() {
        assertThat(EmocionEnum.valueOf("MIEDO"), is(EmocionEnum.MIEDO));
        assertThat(EmocionEnum.valueOf("VERGUENZA"), is(EmocionEnum.VERGUENZA));
    }

    @Test
    public void valueOfConValorInvalidoDebeLanzarExcepcion() {
        try {
            EmocionEnum.valueOf("FELICIDAD");
        } catch (IllegalArgumentException e) {
            assertThat(e, instanceOf(IllegalArgumentException.class));
            return;
        }
        throw new AssertionError("Se esperaba IllegalArgumentException para valor inválido");
    }
}
