# Fablica de madera

## Como construir el proyecto

### Clonar el repositorio

```bash
git clone https://github.com/pablogtejido/fabricaMadera.git
cd fabricaMadera
```

### Crear credenciales en el panel de control de mysql

Las credenciales son:

- Usuario

> spq

- Contraseña

> spq

### Crear esquema

Seguir el fichero [dataBase.sql](https://github.com/pablogtejido/fabricaMadera/blob/master/src/main/resources/dataBase.sql).

Para la primera creacion no serán necesarias las primeras dos lineas. Siendo solamente necesarias ejecutar:

```SQL
/* CREATE ''fabricaMaderaDB' DATABASE */
CREATE SCHEMA fabricaMaderaDB;
/* CREATE THE USER 'products_user1' AT LOCAL SERVER WITH PASSWORD 'password' */
CREATE USER 'usuario'@'%' IDENTIFIED BY 'usuario';
/* GRANT FULL ACCESS TO THE DATABASE FOR THE USER 'products_user1' AT LOCAL SERVER*/
GRANT ALL ON fabricaMaderaDB.* TO 'usuario'@'%';
```

### Verificar el proyecto

```bash
mvn verify
```

### Ejecutar el proyecto

Power Shell

> Power shell necesita unos " extra

```PowerShell
mvn exec:java "-Dexec.args='--server'"
```

cmd/bash

```bash
mvn exec:java "-Dexec.args='--server'"
```

> -Dexec.args='--server' Sirve para poder ejecturar el servidor.
> Sin eso simplemente ejecutas el GUI
