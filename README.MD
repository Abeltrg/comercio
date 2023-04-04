# Api para ecommerse
API para gestionar un pequeño ecommerse

Funcionalidades:
* Obtener un listado de todos los productos
* Obtener un listado de todos los productos disponibles (con stock)
* Obtener los productos más vendidos en la última semana, ordenados de más a menos, indicando las unidades vendidas
* Obtener un listado de cestas almacenadas
* Añade productos a una cesta
* Resta productos de una cesta
* Obtener un lista de facturas almacenadas
* Facturación de una cesta indicando los datos de cliente y pago, actualización de stock

Tecnologías utilizadas:
* Java 17
* Spring Boot 2.7.10 con Maven
* Spring JPA
* H2 Database
* Swagger 2.9.2

Se han añadido algunos Test unitarios

Al arrancar el proyecto, la base de datos se inicializa automáticamente con los siguientes productos:
'''[
  {
    "id": "1020f066-b479-4e9c-8d2d-6265f9d5f306",
    "codigoBarras": "8484848456352",
    "nombre": "Gorra de color negro",
    "descripcion": "Gorra",
    "precio": 9.5,
    "stock": 20,
    "productosCestas": [],
    "lineasFacturas": []
  },
  {
    "id": "07022e61-bd73-4ac7-9753-f1e2e2c40472",
    "codigoBarras": "8484848478569",
    "nombre": "Calcetines de algodón de color azul marino",
    "descripcion": "Calcetines",
    "precio": 5,
    "stock": 60,
    "productosCestas": [],
    "lineasFacturas": []
  },
  {
    "id": "55ab3f48-f529-4b09-a316-ceddd607cbf7",
    "codigoBarras": "8484848412356",
    "nombre": "Pantalon vaquiro de color azul celeste",
    "descripcion": "Pantalón vaquero",
    "precio": 20,
    "stock": 30,
    "productosCestas": [],
    "lineasFacturas": []
  },
  {
    "id": "e3ba8e0f-fba2-4117-8be5-f07c10f050a3",
    "codigoBarras": "8484848474586",
    "nombre": "Camiseta de poliéster de color verde",
    "descripcion": "Camisa",
    "precio": 12,
    "stock": 30,
    "productosCestas": [],
    "lineasFacturas": []
  },
  {
    "id": "43eccebb-c107-434a-ab76-1c1e3d36e836",
    "codigoBarras": "8484848478946",
    "nombre": "Tenis de running para usuario principiante",
    "descripcion": "Tenis de running",
    "precio": 15,
    "stock": 20,
    "productosCestas": [],
    "lineasFacturas": []
  },
  {
    "id": "e76293dd-41ef-47c2-9d1a-95b5da9d9383",
    "codigoBarras": "8484848463528",
    "nombre": "Boxer de algodón de color verde oscuro",
    "descripcion": "Boxer",
    "precio": 6,
    "stock": 10,
    "productosCestas": [],
    "lineasFacturas": []
  }
]'''

## Uso

* Lanzar el proyecto
* Acceder a http://localhost:8080/swagger-ui.html
* Para acceder a la BD en memoria: http://localhost:8080/h2-console/

Datos de acceso para la base de datos (sin password):
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:comerciobd
Username: sa