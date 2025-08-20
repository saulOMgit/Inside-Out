package dev.saul.views;

import dev.saul.controllers.DiarioController;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.models.PositividadEnum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class HomeViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private InputStream originalIn;

    @Mock
    private DiarioController diarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));
        originalIn = System.in;
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        HomeView.setScanner(System.in);
    }

    @Test
    void printMenu_shouldDisplayCorrectMenu() {
        HomeView.printMenu();
        String output = outContent.toString();

        assertThat(output, containsString("===== MI DIARIO ====="));
        assertThat(output, containsString("1. Agregar momento"));
        assertThat(output, containsString("2. Listar momentos"));
        assertThat(output, containsString("0. Salir"));
    }

    @Test
    void readOption_withValidInput_shouldReturnNumber() {
        provideInput("5");
        int option = HomeView.readOption();
        assertThat(option, is(5));
    }

    @Test
    void readOption_withInvalidInput_shouldReturnMinusOne() {
        provideInput("invalid");
        int option = HomeView.readOption();
        assertThat(option, is(-1));
    }

    @Test
    void mostrarMensaje_shouldPrintMessage() {
        String testMessage = "Este es un mensaje de prueba";
        HomeView.mostrarMensaje(testMessage);
        assertThat(outContent.toString(), containsString(testMessage));
    }

 @Test
void agregarMomento_shouldAddMomentoToController() {
    // Configurar entrada simulada
    String input = "Título prueba\n01/01/2023\nDescripción prueba\n1\n1\n"; 
    provideInput(input);

    // Ejecutar el método
    HomeView.agregarMomento(diarioController);

    // Verificar salida
    String output = outContent.toString();
    assertThat(output, containsString("Ingrese el título:"));
    assertThat(output, containsString("Ingrese la fecha (dd/MM/yyyy):"));
    assertThat(output, containsString("Ingrese la descripción:"));
    assertThat(output, containsString("Selecciona una emoción:"));
    assertThat(output, containsString("¿El momento fue bueno o malo? (1 = Bueno, 2 = Malo)")); // <-- ajustado
    assertThat(output, containsString("Momento vivido añadido correctamente."));

    // Verificar interacción con el controlador
    verify(diarioController).agregarMomento(any(Momento.class));
}



    @Test
    void listarMomentos_withEmptyList_shouldShowEmptyMessage() {
        when(diarioController.listarMomentos()).thenReturn(List.of());

        HomeView.listarMomentos(diarioController);

        assertThat(outContent.toString(), containsString("No hay momentos guardados."));
    }

    @Test
    void listarMomentos_withMoments_shouldShowMoments() {
        Momento momento = new Momento("Título", "Descripción", EmocionEnum.ALEGRIA, LocalDate.now());
        when(diarioController.listarMomentos()).thenReturn(List.of(momento));

        HomeView.listarMomentos(diarioController);

        String output = outContent.toString();
        assertThat(output, containsString("Lista de momentos vividos:"));
        assertThat(output, containsString(momento.toString()));
    }

   @Test
void buscarMomentoPorId_withExistingId_shouldShowMoment() {
    LocalDate fecha = LocalDate.of(2025, 8, 11);
    Momento momento = new Momento(
        "Título",
        fecha,
        "Descripción",
        EmocionEnum.ALEGRIA,
        PositividadEnum.BUENO
    );

    when(diarioController.listarMomentos()).thenReturn(List.of(momento));

    // usar el id real generado
    provideInput(momento.getId() + "\n");

    HomeView.buscarMomentoPorId(diarioController);

    String output = outContent.toString();
    assertThat(output, containsString(momento.toString()));
}


    @Test
    void buscarMomentoPorId_withNonExistingId_shouldShowNotFound() {
        when(diarioController.listarMomentos()).thenReturn(List.of());

        provideInput("99");
        HomeView.buscarMomentoPorId(diarioController);

        String output = outContent.toString();
        assertThat(output, containsString("No se encontró momento con ese ID."));
    }

    @Test
    void eliminarMomento_withExistingId_shouldShowSuccessMessage() {
        when(diarioController.eliminarMomento(1)).thenReturn(true);

        provideInput("1");
        HomeView.eliminarMomento(diarioController);

        String output = outContent.toString();
        assertThat(output, containsString("Momento eliminado correctamente."));
    }

    @Test
    void eliminarMomento_withNonExistingId_shouldShowNotFound() {
        when(diarioController.eliminarMomento(99)).thenReturn(false);

        provideInput("99");
        HomeView.eliminarMomento(diarioController);

        String output = outContent.toString();
        assertThat(output, containsString("No se encontró momento con ese ID."));
    }

    @Test
    void mostrarOpcionesEmocion_shouldDisplayAllEmotions() {
        HomeView.mostrarOpcionesEmocion();

        String output = outContent.toString();

        // Verificar que muestra todas las emociones en formato capitalizado
        for (EmocionEnum emocion : EmocionEnum.values()) {
            assertThat(output, containsString(HomeView.capitalize(emocion.name())));
        }

        // Verificar que muestra los números correctamente
        for (int i = 1; i <= EmocionEnum.values().length; i++) {
            assertThat(output, containsString(i + ". "));
        }
    }

    @Test
    void fromIntEmocion_withValidInput_shouldReturnCorrectEmotion() {
        EmocionEnum result = HomeView.fromIntEmocion(1);
        assertThat(result, is(EmocionEnum.values()[0]));
    }

    @Test
    void listarMomentosPorEmocion_withSelectedEmotion_shouldFilterMoments() {
        // Configuración del test
        EmocionEnum emocion = EmocionEnum.ALEGRIA;
        Momento momento = new Momento("Título", "Descripción", emocion, LocalDate.now());

        // Mock del repositorio
        when(diarioController.filtrarPorEmocion(emocion)).thenReturn(List.of(momento));

        // Simular entrada del usuario (selección de ALEGRIA que es la opción 1)
        provideInput("1");

        // Ejecutar el método
        HomeView.listarMomentosPorEmocion(diarioController);

        // Verificaciones
        String output = outContent.toString();

        // Verificar que muestra la emoción capitalizada ("Alegria")
        assertThat(output, containsString("Momentos con emoción Alegria:"));

        // Verificar que muestra el momento
        assertThat(output, containsString(momento.toString()));

        // Verificar que se llamó al método correcto del controlador
        verify(diarioController).filtrarPorEmocion(emocion);
    }

    @Test
    void listarMomentosPorMesAno_withMoments_shouldShowFilteredMoments() {
        int mes = 1;
        int ano = 2023;
        Momento momento = new Momento("Título", "Descripción", EmocionEnum.ALEGRIA, LocalDate.of(ano, mes, 1));
        when(diarioController.filtrarPorMesAno(mes, ano)).thenReturn(List.of(momento));

        provideInput(mes + "\n" + ano);
        HomeView.listarMomentosPorMesAno(diarioController);

        String output = outContent.toString();
        assertThat(output, containsString("Momentos del " + mes + "/" + ano + ":"));
        assertThat(output, containsString(momento.toString()));
    }

    private void provideInput(String data) {
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        HomeView.setScanner(in);
    }
}