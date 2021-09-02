# Reto #5 Zoologico - MVC con JAVA para Mision Tic 2022 🚀

<div style="text-align: center;">
  <img src="https://pbs.twimg.com/profile_images/1386480173613076484/FRbS-TaM_400x400.jpg" width="176" style="border-radius: 15px;">
  <img src="https://i.ytimg.com/vi/vLuxYtkxQTM/mqdefault.jpg" width="auto" style="border-radius: 15px;">
</div>

---

## Authors

- [Vanessa](https://www.github.com/)
- [Alexander](https://www.github.com/g1alexander)

## Configuración

- en el archivo **src/config/db[example].json** quitar **"[example]"** y colocar las credenciales dentro del **json**

```json
{
  "ip": "",
  "port": "",
  "db": "",
  "user": "",
  "password": ""
}
```

- Base de datos en la carpeta
  - crear primero una base de datos con el nombre **"zoologico"** en el gestor de base de datos y luego ejecutar el script: **zoologico.sql**

```txt
  src/db/zoologico.sql
```

- En dado caso de que no funcione el programa revisar tener las librerias
  - [JDBC mysql-java (5.1)](https://jar-download.com/artifacts/mysql/mysql-connector-java)
  - [json-simple-java (1.1.1)](https://jar-download.com/?search_box=JSON.simple)
  - JDK de Java v1.8
