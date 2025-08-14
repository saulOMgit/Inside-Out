# Inside Out - Mi Diario de Emociones

## Descripción

Inside Out es una aplicación de consola en Java para registrar y gestionar momentos vividos, asociados a diferentes emociones. Permite agregar, listar, buscar y eliminar momentos, así como filtrarlos por emoción o por mes y año.

El proyecto sigue una arquitectura simple con capas de **Model**, **View**, **Controller** y **Repository**, simulando persistencia en memoria.

## Estructura del Proyecto

- **models**: Contiene las clases `Momento` y `EmocionEnum`.
- **views**: Contiene `HomeView` y `DiarioView` para la interacción con el usuario.
- **controllers**: Contiene `HomeController` y `DiarioController` para gestionar la lógica de negocio y el flujo de la aplicación.
- **repositories**: Contiene `MomentRepository` y `MomentDB` para la gestión de almacenamiento de los momentos.

## Funcionalidades

1. **Agregar momento**: Registrar un momento con título, descripción, fecha y emoción.
2. **Listar momentos**: Mostrar todos los momentos guardados.
3. **Buscar momento por ID**: Encontrar un momento específico.
4. **Eliminar momento**: Eliminar un momento por su ID.
5. **Filtrar por emoción**: Mostrar momentos según su emoción.
6. **Filtrar por mes y año**: Mostrar momentos de un mes y año específicos.

## Diagrama de Clases

```mermaid
classDiagram
    class Momento {
        +int id
        +String titulo
        +String descripcion
        +EmocionEnum emocion
        +LocalDate fecha
        +Momento(String titulo, String descripcion, EmocionEnum emocion, LocalDate fecha)
        +String toString()
    }

    class EmocionEnum {
        <<enumeration>>
        ALEGRIA
        TRISTEZA
        IRA
        ASCO
        MIEDO
        ANSIEDAD
        ENVIDIA
        VERGUENZA
        ABURRIMIENTO
        NOSTALGIA
    }

    class MomentDB {
        -static List~Momento~ momentos
        +static List~Momento~ getMomentos()
        +static void addMomento(Momento m)
        +static boolean deleteMomento(int id)
    }

    class MomentRepository {
        -MomentDB db
        +void add(Momento m)
        +boolean deleteById(int id)
        +List~Momento~ getAll()
        +List~Momento~ findByEmocion(EmocionEnum e)
        +List~Momento~ findByMesYAnio(int mes, int ano)
    }

    class DiarioController {
        -MomentRepository repository
        +void agregarMomento(Momento m)
        +boolean eliminarMomento(int id)
        +List~Momento~ listarMomentos()
        +List~Momento~ filtrarPorEmocion(EmocionEnum e)
        +List~Momento~ filtrarPorMesAno(int mes, int ano)
    }

    class HomeController {
        -HomeView homeView
        -DiarioController diarioController
        +void start()
        +void manejarOpcion(int opcion)
    }

    class HomeView {
        -static Scanner scanner
        +static void printMenu()
        +static int readOption()
        +static void mostrarMensaje(String mensaje)
        +static void agregarMomento(DiarioController c)
        +static void eliminarMomento(DiarioController c)
        +static void listarMomentos(DiarioController c)
        +static void listarMomentosPorEmocion(DiarioController c)
        +static void listarMomentosPorMesAno(DiarioController c)
        +static void mostrarOpcionesEmocion()
        +static EmocionEnum fromIntEmocion(int opcion)
        -static String capitalize(String str)
    }

    class DiarioView {
        +void mostrarMomentos(List~Momento~ momentos)
    }

    %% Relaciones
    HomeController --> HomeView : usa
    HomeController --> DiarioController : coordina
    HomeView --> DiarioController : usa
    DiarioController --> MomentRepository : usa
    MomentRepository --> MomentDB : usa
    DiarioController --> Momento : contiene
    Momento --> EmocionEnum : tiene
    DiarioView --> Momento : muestra
```
## Test Coverage

![Test](./insideout/img/test.png)
## Cómo Ejecutar

1. Clonar el repositorio.
2. Compilar las clases de Java.
3. Ejecutar `HomeController` para iniciar la aplicación.

```bash
javac -d bin src/main/java/dev/saul/**/*.java
java -cp bin dev.saul.controllers.HomeController
```

## Notas

- La persistencia es simulada en memoria usando `MomentDB`. Al cerrar la aplicación, los datos se pierden.
- Los tests unitarios se pueden ejecutar para validar la funcionalidad de `HomeView` y `DiarioController`.

---

© 2025 Inside Out Project

