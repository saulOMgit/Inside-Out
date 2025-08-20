package dev.saul.models;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

public class MomentoTest {

    @Test
    public void testConstructorYGetters() {
        LocalDate fecha = LocalDate.of(2025, 8, 11);
        Momento momento = new Momento("Viaje", "Fui a la playa", EmocionEnum.ALEGRIA, fecha);

        assertThat(momento.getTitulo(), is("Viaje"));
        assertThat(momento.getDescripcion(), is("Fui a la playa"));
        assertThat(momento.getEmocion(), is(EmocionEnum.ALEGRIA));
        assertThat(momento.getFechaMomento(), is(fecha));
        assertThat(momento.getId(), greaterThan(0));
        assertThat(momento.getFechaCreacion(), is(notNullValue()));
    }

    @Test
    public void testSetters() {
        Momento momento = new Momento("Viaje", "Fui a la playa", EmocionEnum.ALEGRIA, LocalDate.now());

        momento.setTitulo("Escapada");
        momento.setDescripcion("Visité la montaña");

        assertThat(momento.getTitulo(), is("Escapada"));
        assertThat(momento.getDescripcion(), is("Visité la montaña"));
    }

@Test
public void testToString() {
    LocalDate fecha = LocalDate.of(2025, 8, 11);

    Momento momento = new Momento(
            "Viaje",
            fecha,
            "Fui a la playa",
            EmocionEnum.ALEGRIA,
            PositividadEnum.BUENO
    );

    String texto = momento.toString();

    assertThat(texto, containsString("titulo=Viaje"));
    assertThat(texto, containsString("descripcion=Fui a la playa"));
    assertThat(texto, containsString("emocion=ALEGRIA"));
    assertThat(texto, containsString("fechaMomento=" + fecha));
    assertThat(texto, containsString("positividad=BUENO"));
}



}
