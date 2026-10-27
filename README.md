# Conjunto Residencial

Aplicación de consola en Java para administrar el día a día de un conjunto residencial: pagos de administración, incidentes, control de entrada y salida de vehículos y recepción de mercancía. Toda la información se guarda en archivos CSV, así que no necesita base de datos ni servidor.

## Qué hace

El programa trabaja con tres tipos de usuario, cada uno con su propio menú después de iniciar sesión.

**Propietario**
- Consulta y paga los cobros pendientes (debe digitar el monto completo para que el pago quede efectuado).
- Reporta incidentes indicando descripción y ubicación.
- Autoriza vehículos propios (placa y tipo).
- Ve sus vehículos autorizados y su mercancía registrada.

**Vigilante**
- Revisa los incidentes reportados y decide si los registra o los archiva.
- Registra la entrada y la salida de vehículos por placa.
- Registra la llegada de mercancía a nombre de un propietario.

**Administrador**
- Genera cobros a los propietarios y consulta los pagos pendientes.
- Consulta incidentes y marca los que ya fueron resueltos.
- Ve los vehículos autorizados y registra entradas y salidas.
- Registra mercancía y consulta la ya registrada.
- Exporta todos los datos a los archivos CSV.
- Modifica o elimina líneas de los CSV desde el propio programa.

En la pantalla inicial también se puede registrar un usuario nuevo eligiendo su tipo. Los propietarios piden número de apartamento y los vigilantes piden turno (diurno o nocturno).

## Cómo funciona por dentro

Al arrancar, el programa lee los CSV y construye los objetos en memoria. Todo lo que se hace durante la sesión ocurre en memoria, y solo se escribe a disco cuando el administrador elige "Exportar datos". Si se cierra el programa sin exportar, los cambios se pierden.

Organización del código, en `programmingproject/src/main/java/com/mycompany/programmingproject/`:

| Clase | Responsabilidad |
|---|---|
| `Programmingproject` | Punto de entrada. Carga los datos, muestra los menús y dirige el flujo. |
| `Usuario` | Clase base con id, nombre, email, contraseña y tipo. |
| `Propietario`, `Vigilante`, `Administrador` | Subclases de `Usuario`, con las acciones propias de cada rol. |
| `Vehiculo`, `Pago`, `Incidente`, `Mercancia`, `RegistroAcceso`, `Apartamento` | Modelos de datos. |
| `GestionPagos`, `GestionIncidentes`, `GestionMercancia`, `ControlAcceso` | Mantienen las listas de cada tipo de dato y la lógica asociada. Son singleton. |
| `IniciarSesion` | Guarda el usuario que tiene la sesión activa. También es singleton. |
| `archivoHandler` | Lectura, escritura, modificación y eliminación de líneas en los CSV. |
| `Utilidades` | Funciones para pedir datos por consola y buscar usuarios. |

## Archivos de datos

Los CSV están en la raíz del repositorio, sin encabezado y separados por comas.

| Archivo | Columnas |
|---|---|
| `usuarios.csv` | id, nombre, email, contraseña, tipo, y luego apartamento y al día (propietario) o turno (vigilante) |
| `vehiculos.csv` | placa, tipo, id del propietario, autorizado |
| `pagos.csv` | id, id del propietario, monto, medio de pago, fecha, concepto, estado |
| `incidentes.csv` | id, descripción, id de quien reportó, fecha y hora, ubicación, estado |
| `mercancias.csv` | id, descripción, id del destinatario, fecha y hora de llegada, entregada |
| `registroacceso.csv` | id, placa, fecha y hora de entrada, fecha y hora de salida, id de quien registró |

Como el formato es CSV simple, los textos no deben contener comas.

## Requisitos

- JDK 19 o superior
- Maven (opcional, solo para compilar)

## Cómo ejecutarlo

El programa busca los CSV en el directorio desde el que se ejecuta, así que hay que lanzarlo desde la raíz del repositorio, no desde la carpeta `programmingproject`.

Compilar:

```
mvn -f programmingproject/pom.xml compile
```

Ejecutar, desde la raíz:

```
java -cp programmingproject/target/classes com.mycompany.programmingproject.Programmingproject
```

Si se prefiere no usar Maven, también se puede compilar directamente con `javac`:

```
javac -d programmingproject/target/classes programmingproject/src/main/java/com/mycompany/programmingproject/*.java
```

## Primer ingreso

El repositorio trae un usuario de cada tipo en `usuarios.csv` para probar. Por ejemplo, el id `1` es un propietario y los ids `2` y `3` son administradores. Las contraseñas de estos usuarios de ejemplo están en el mismo archivo. Un vigilante se puede crear desde la opción "Registrar usuario" del menú inicial.

## Limitaciones conocidas

- Las contraseñas se guardan en texto plano dentro de `usuarios.csv`. Es aceptable para un proyecto académico, pero no para uso real.
- No hay persistencia automática: hay que exportar desde el menú del administrador.
- Cualquier persona puede registrarse como administrador desde el menú inicial.
- Los datos se leen con `split(",")`, por lo que una coma dentro de un campo rompe la línea.
