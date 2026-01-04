**Minor Contracts App**

**Resumen Del Proyecto**

- **Descripción:** Aplicación Java (Maven) que procesa un fichero XML de contratos menores, persiste los registros en una base de datos MySQL y genera un XML de salida transformado.
- **Propósito:** Ejemplo académico/práctico de acceso a datos, lectura/escritura de XML y persistencia JDBC.

**Estructura Del Repositorio**

- **Proyecto Maven:** [MinorContractsApp/pom.xml](MinorContractsApp/pom.xml)
- **Clase principal:** [MinorContractsApp/src/main/java/Main.java](MinorContractsApp/src/main/java/Main.java)
- **Conexión BD:** [MinorContractsApp/src/main/java/db/DatabaseConnection.java](MinorContractsApp/src/main/java/db/DatabaseConnection.java)
- **DAO:** [MinorContractsApp/src/main/java/dao/ContractDAO.java](MinorContractsApp/src/main/java/dao/ContractDAO.java)
- **Modelo:** [MinorContractsApp/src/main/java/model/Contract.java](MinorContractsApp/src/main/java/model/Contract.java)
- **XML (entrada):** [MinorContractsApp/src/main/resources/contratos.xml](MinorContractsApp/src/main/resources/contratos.xml)
- **SQL creación BD:** [MinorContractsApp/src/main/database/createDatabaseAndTable.sql](MinorContractsApp/src/main/database/createDatabaseAndTable.sql)

**Requisitos**

- **Java:** JDK 23 (según `pom.xml`) o compatible.
- **Maven:** Para compilar y manejar dependencias.
- **MySQL:** Servidor MySQL en `localhost` puerto `3306`. El proyecto usa la base `minor_contracts_db` y el usuario `root` (contraseña vacía por defecto en `DatabaseConnection.java`). Ajustar según su entorno.

**Instalación Y Ejecución**

1. Abrir una terminal y situarse en la carpeta del proyecto:

```bash
cd MinorContractsApp
```

2. Compilar y empaquetar:

```bash
mvn clean package
mvn dependency:copy-dependencies
```

3. Ejecutar la aplicación (Windows):

```bash
java -cp "target/classes;target/dependency/*" Main
```

Nota: si usa IDE (IntelliJ/Eclipse) puede ejecutar la clase `Main` directamente.

**Base De Datos**

- **Creación:** Ejecutar el script SQL para crear la base y la tabla:

```sql
-- Archivo: MinorContractsApp/src/main/database/createDatabaseAndTable.sql
CREATE DATABASE IF NOT EXISTS minor_contracts_db;
USE minor_contracts_db;
CREATE TABLE IF NOT EXISTS contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nif VARCHAR(20),
    awardedTo VARCHAR(255),
    genericObject VARCHAR(255),
    objectDescription TEXT,
    awardedDate VARCHAR(50),
    amount DECIMAL(10, 2),
    consultedProviders TEXT
);
```

- **Conexión:** Revisar y adaptar credenciales en [MinorContractsApp/src/main/java/db/DatabaseConnection.java](MinorContractsApp/src/main/java/db/DatabaseConnection.java).

**Casos De Prueba / Ejemplo**

- **Flujo de ejemplo (manual):**

  1. Asegúrese de que MySQL esté en ejecución y la base `minor_contracts_db` creada.
  2. Coloque/valide el fichero de entrada en [MinorContractsApp/src/main/resources/contratos.xml](MinorContractsApp/src/main/resources/contratos.xml).
  3. Ejecute la aplicación (`java -cp "target/classes;target/dependency/*" Main`).

- **Salida esperada en consola:**

```
Iniciando el procesador de Contratos Menores...
Leyendo archivo XML...
Total de contratos analizados: X
Guardando contratos en la base de datos...
Datos guardados exitosamente en la base de datos.
Generando XML de salida...
Archivo de salida creado: src/main/resources/contracts-output.xml
¡Proceso completado exitosamente!
```

- **Verificación en BD:**

```sql
USE minor_contracts_db;
SELECT COUNT(*) FROM contracts;
SELECT * FROM contracts LIMIT 10;
```

**Componentes Principales**

- **`Main` :** Orquesta el flujo: lectura XML -> persistencia -> escritura XML.
- **`XMLReader` / `XMLWriter` :** Manejan la deserialización/serialización de documentos XML.
- **`ContractDAO` :** Inserta los objetos `Contract` en la tabla `contracts` usando JDBC.
- **`DatabaseConnection` :** Provee la conexión JDBC a MySQL (ajustar URL/credenciales si procede).

**Notas Adicionales**

- **Dependencias:** El `pom.xml` incluye `mysql-connector-java` y dependencias para tests con JUnit.
- **Adaptaciones:** Si desea usar otra BD (por ejemplo, SQLite o PostgreSQL), cambie la URL JDBC y adapte el driver en `pom.xml`.
- **Privacidad de credenciales:** Para producción, no deje credenciales en código; use variables de entorno o un archivo de configuración seguro.

**¿Qué puedo hacer después?**

- **Ejecutar pruebas unitarias** (si existen) con `mvn test`.
- **Agregar manejo de configuración** (ej.: `application.properties`) para no tener credenciales hard-codeadas.
- **Mejorar empaquetado** con un ejecutable 'fat-jar' si desea distribuir fácilmente la aplicación.
