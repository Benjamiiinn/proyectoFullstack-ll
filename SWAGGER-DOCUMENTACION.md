# 📚 Documentación API Swagger - Tienda FullStack II

## 🎯 Descripción

Esta documentación Swagger/OpenAPI proporciona una interfaz interactiva completa para explorar y probar todos los endpoints de la API REST de la Tienda FullStack II. La documentación está completamente en español e incluye descripciones detalladas, ejemplos y la capacidad de probar los endpoints directamente desde el navegador.

## 🚀 Acceso a la Documentación

### URLs de Acceso

Una vez que el backend esté en ejecución:

- **Swagger UI (Interfaz Interactiva)**: http://localhost:8080/swagger-ui.html
- **Documentación JSON de OpenAPI**: http://localhost:8080/v3/api-docs
- **Documentación YAML de OpenAPI**: http://localhost:8080/v3/api-docs.yaml

## 📋 Características de la Documentación

### ✅ Lo que incluye:

1. **Endpoints organizados por categorías**:
   - 🔐 **Autenticación**: Login, registro de usuarios y administradores
   - 🎮 **Productos**: CRUD completo de videojuegos y productos
   - 🛒 **Pedidos**: Gestión de compras y pedidos
   - 👥 **Usuarios**: Administración de usuarios
   - 📁 **Categorías**: Gestión de categorías de productos
   - 🎯 **Plataformas**: Gestión de plataformas (PlayStation, Xbox, etc.)
   - 📧 **Contacto**: Sistema de mensajes de contacto

2. **Detalles técnicos**:
   - Métodos HTTP (GET, POST, PUT, DELETE, PATCH)
   - Parámetros requeridos y opcionales
   - Esquemas de petición y respuesta
   - Códigos de respuesta HTTP
   - Ejemplos de uso

3. **Sistema de autenticación JWT**:
   - Botón "Authorize" para autenticación
   - Soporte para Bearer Token
   - Indicación de endpoints protegidos

## 🔧 Cómo Usar la Documentación

### Paso 1: Iniciar el Backend

```bash
# Navegar a la carpeta del backend
cd proyectoFullstack-ll

# Ejecutar con Maven
./mvnw spring-boot:run

# O con Maven Wrapper en Windows
mvnw.cmd spring-boot:run
```

### Paso 2: Acceder a Swagger UI

1. Abrir navegador web
2. Ir a: http://localhost:8080/swagger-ui.html
3. Verás la interfaz de Swagger con todos los endpoints disponibles

### Paso 3: Probar Endpoints Públicos

Los siguientes endpoints no requieren autenticación:

- `POST /auth/login` - Iniciar sesión
- `POST /auth/register` - Registrar usuario
- `GET /api/v1/productos/**` - Ver productos
- `GET /api/v1/categorias/**` - Ver categorías
- `GET /api/v1/plataformas/**` - Ver plataformas
- `POST /api/v1/contacto` - Enviar mensaje de contacto

**Ejemplo de prueba**:
1. Expandir el endpoint `GET /api/v1/productos`
2. Hacer clic en "Try it out"
3. Hacer clic en "Execute"
4. Ver la respuesta con todos los productos

### Paso 4: Autenticarse para Endpoints Protegidos

Para usar endpoints que requieren autenticación:

1. **Registrarse o Iniciar Sesión**:
   - Expandir `POST /auth/login` o `POST /auth/register`
   - Hacer clic en "Try it out"
   - Completar el JSON con tus credenciales:
   ```json
   {
     "username": "usuario@ejemplo.com",
     "password": "Password123"
   }
   ```
   - Hacer clic en "Execute"
   - **Copiar el token JWT** de la respuesta

2. **Autorizar en Swagger**:
   - Hacer clic en el botón **"Authorize"** 🔓 (esquina superior derecha)
   - En el campo "Value", ingresar: `Bearer <tu-token-aqui>`
   - Ejemplo: `Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
   - Hacer clic en "Authorize" y luego "Close"

3. **Usar Endpoints Protegidos**:
   - Ahora puedes probar cualquier endpoint que requiera autenticación
   - El token se enviará automáticamente con cada petición

### Paso 5: Probar Endpoints de Administrador

Los endpoints marcados con 🔒 y que requieren rol ADMIN:

- Gestión completa de productos (POST, PUT, DELETE)
- Ver todos los usuarios
- Gestión de categorías y plataformas
- Ver todos los pedidos
- Gestión de mensajes de contacto

**Para probar como ADMIN**:
1. Usar el endpoint `POST /auth/register/admin` para crear un usuario admin
2. Iniciar sesión con esas credenciales
3. Usar el token JWT obtenido

## 📖 Estructura de la Documentación

### Categorías de Endpoints

#### 🔐 Autenticación
- **POST /auth/login**: Iniciar sesión
- **POST /auth/register**: Registrar usuario normal
- **POST /auth/register/admin**: Registrar administrador

#### 🎮 Productos
- **GET /api/v1/productos**: Listar todos los productos
- **GET /api/v1/productos/{id}**: Obtener producto por ID
- **GET /api/v1/productos/categoria/{nombre}**: Buscar por categoría
- **GET /api/v1/productos/destacados**: Productos destacados
- **GET /api/v1/productos/plataforma/{nombre}**: Buscar por plataforma
- **POST /api/v1/productos**: Crear producto (ADMIN)
- **PUT /api/v1/productos/{id}**: Actualizar producto (ADMIN)
- **DELETE /api/v1/productos/{id}**: Eliminar producto (ADMIN)

#### 🛒 Pedidos
- **POST /api/v1/pedidos**: Realizar compra
- **GET /api/v1/pedidos/{id}**: Ver pedido específico
- **GET /api/v1/pedidos/usuario/{id}**: Mis pedidos
- **GET /api/v1/pedidos**: Listar todos (ADMIN)
- **PUT /api/v1/pedidos/{id}/estado**: Actualizar estado (ADMIN)
- **GET /api/v1/pedidos/buscar**: Buscar por fechas (ADMIN)

#### 👥 Usuarios
- **GET /api/v1/usuarios**: Listar usuarios (ADMIN)
- **GET /api/v1/usuarios/{id}**: Ver usuario
- **GET /api/v1/usuarios/rut/{rut}**: Buscar por RUT (ADMIN)
- **POST /api/v1/usuarios**: Crear usuario (ADMIN)
- **PUT /api/v1/usuarios/{id}**: Actualizar usuario
- **DELETE /api/v1/usuarios/{id}**: Eliminar usuario (ADMIN)
- **PATCH /api/v1/usuarios/{id}/estado**: Cambiar estado (ADMIN)

#### 📁 Categorías
- **GET /api/v1/categorias**: Listar categorías
- **GET /api/v1/categorias/{id}**: Ver categoría
- **POST /api/v1/categorias**: Crear categoría (ADMIN)
- **PUT /api/v1/categorias/{id}**: Actualizar categoría (ADMIN)
- **DELETE /api/v1/categorias/{id}**: Eliminar categoría (ADMIN)

#### 🎯 Plataformas
- **GET /api/v1/plataformas**: Listar plataformas
- **GET /api/v1/plataformas/{id}**: Ver plataforma
- **POST /api/v1/plataformas**: Crear plataforma (ADMIN)
- **PUT /api/v1/plataformas/{id}**: Actualizar plataforma (ADMIN)
- **DELETE /api/v1/plataformas/{id}**: Eliminar plataforma (ADMIN)

#### 📧 Contacto
- **POST /api/v1/contacto**: Enviar mensaje
- **GET /api/v1/contacto**: Listar mensajes (ADMIN)
- **GET /api/v1/contacto/{id}**: Ver mensaje (ADMIN)
- **DELETE /api/v1/contacto/{id}**: Eliminar mensaje (ADMIN)
- **PATCH /api/v1/contacto/{id}/leido**: Marcar como leído (ADMIN)

## 🛡️ Seguridad y Roles

### Roles de Usuario

1. **USER** (Usuario estándar):
   - Ver productos, categorías y plataformas
   - Realizar compras
   - Ver y editar su propio perfil
   - Ver sus propios pedidos

2. **ADMIN** (Administrador):
   - Todos los permisos de USER
   - Gestión completa de productos
   - Gestión de usuarios
   - Ver todos los pedidos
   - Gestión de categorías y plataformas
   - Gestión de mensajes de contacto

### Sistema de Autenticación

- **Tipo**: JWT (JSON Web Token)
- **Formato**: Bearer Token
- **Ubicación**: Header Authorization
- **Formato completo**: `Authorization: Bearer <token>`

## 🔍 Características Avanzadas

### Filtros y Búsqueda
- Búsqueda de productos por categoría
- Búsqueda de productos por plataforma
- Filtro de productos destacados
- Búsqueda de pedidos por rango de fechas
- Búsqueda de usuarios por RUT

### Códigos de Respuesta HTTP

- **200 OK**: Petición exitosa
- **201 Created**: Recurso creado exitosamente
- **204 No Content**: Recurso eliminado exitosamente
- **400 Bad Request**: Error en los datos enviados
- **401 Unauthorized**: No autenticado
- **403 Forbidden**: No tiene permisos
- **404 Not Found**: Recurso no encontrado
- **500 Internal Server Error**: Error del servidor

## 💡 Consejos de Uso

1. **Explorar antes de probar**: Lee las descripciones de los endpoints antes de ejecutarlos
2. **Usar ejemplos**: Los campos tienen ejemplos que puedes usar como referencia
3. **Verificar roles**: Asegúrate de tener el rol correcto para endpoints protegidos
4. **Guardar el token**: Copia y guarda tu token JWT para no tener que iniciar sesión constantemente
5. **Revisar respuestas**: Lee los códigos de respuesta HTTP para entender qué sucedió

## 🐛 Solución de Problemas

### Problema: No puedo acceder a Swagger UI

**Solución**:
1. Verificar que el backend esté corriendo en el puerto 8080
2. Comprobar que no haya errores en la consola del backend
3. Intentar acceder a: http://localhost:8080/v3/api-docs

### Problema: "401 Unauthorized" en endpoints protegidos

**Solución**:
1. Asegurarte de estar autenticado
2. Verificar que el token JWT sea válido
3. Comprobar que el formato sea: `Bearer <token>`
4. El token puede haber expirado, intenta iniciar sesión nuevamente

### Problema: "403 Forbidden" en endpoints de admin

**Solución**:
1. Verificar que tu usuario tenga rol ADMIN
2. Usar el endpoint `/auth/register/admin` para crear un usuario administrador

## 📦 Dependencias Utilizadas

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

## 🔗 Enlaces Útiles

- **OpenAPI Specification**: https://swagger.io/specification/
- **Springdoc OpenAPI**: https://springdoc.org/
- **JWT.io**: https://jwt.io/ (para decodificar tokens JWT)

## 📝 Notas Adicionales

- La documentación se genera automáticamente basándose en las anotaciones del código
- Los esquemas de datos se generan a partir de las clases Java
- La documentación está completamente en español para facilitar su uso
- Todos los endpoints están probados y documentados con ejemplos reales

## 🎓 Para Desarrolladores

Si deseas agregar documentación a nuevos endpoints:

1. Agregar la anotación `@Tag` a la clase del controlador
2. Agregar `@Operation` a cada método
3. Usar `@ApiResponses` para documentar las respuestas
4. Usar `@Parameter` para documentar los parámetros
5. Usar `@Schema` en los DTOs para documentar los campos

**Ejemplo**:
```java
@Operation(
    summary = "Breve descripción",
    description = "Descripción detallada del endpoint"
)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Éxito"),
    @ApiResponse(responseCode = "404", description = "No encontrado")
})
@GetMapping("/{id}")
public ResponseEntity<Entity> getById(@Parameter(description = "ID del recurso") @PathVariable int id) {
    // implementación
}
```

---

## 📞 Soporte

Si tienes problemas o preguntas sobre la documentación de la API, por favor contacta al equipo de desarrollo.

**¡Disfruta explorando la API de Tienda FullStack II! 🎮🛒**
