package dev.saul.views;

import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class DiarioViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private DiarioView diarioView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        diarioView = new DiarioView();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void mostrarMomentos_withEmptyList_shouldDisplayEmptyMessage() {
        // Given
        List<Momento> momentos = List.of();
        
        // When
        diarioView.mostrarMomentos(momentos);
        
        // Then
        assertThat(outContent.toString(), containsString("No hay momentos guardados."));
    }

    @Test
    void mostrarMomentos_withMoments_shouldDisplayAllMoments() {
        // Given
        Momento momento1 = new Momento("Título 1", "Descripción 1", EmocionEnum.ALEGRIA, LocalDate.now());
        Momento momento2 = new Momento("Título 2", "Descripción 2", EmocionEnum.TRISTEZA, LocalDate.now().minusDays(1));
        List<Momento> momentos = List.of(momento1, momento2);
        
        // When
        diarioView.mostrarMomentos(momentos);
        
        // Then
        String output = outContent.toString();
        assertThat(output, containsString(momento1.toString()));
        assertThat(output, containsString(momento2.toString()));
    }

    @Test
    void mostrarMensaje_shouldDisplayCorrectMessage() {
        // Given
        String mensaje = "Este es un mensaje de prueba";
        
        // When
        diarioView.mostrarMensaje(mensaje);
        
        // Then
        assertThat(outContent.toString(), equalTo(mensaje + System.lineSeparator()));
    }

   @Test
void mostrarMomentos_shouldDisplayMomentsInCorrectFormat() {
    // Given
    Momento momento = new Momento("Mi título", "Mi descripción", EmocionEnum.ALEGRIA, LocalDate.of(2023, 5, 15));
    List<Momento> momentos = List.of(momento);
    
    // When
    diarioView.mostrarMomentos(momentos);
    
    // Then
    String output = outContent.toString();
    assertThat(output, startsWith(momento.getId() + ". Ocurrió el: 2023-05-15"));
    assertThat(output, containsString("Título: Mi título"));
    assertThat(output, containsString("Descripción: Mi descripción"));
    assertThat(output, containsString("Emoción: ALEGRIA"));
    assertThat(output, endsWith(System.lineSeparator()));
}
}