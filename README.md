# 🍽️ MyRecipesApp

**MyRecipesApp** es una aplicación Android desarrollada con **Jetpack Compose**, que permite registrar, consultar, editar y eliminar recetas de cocina. La app utiliza persistencia local con Room, consumo de API externa con Retrofit, navegación entre pantallas con Navigation Compose y lógica desacoplada con ViewModel.

---

## 🎯 Objetivo del Proyecto

Diseñar y desarrollar una aplicación CRUD completa de recetas de cocina, que cumpla con los principios de arquitectura limpia y buenas prácticas de desarrollo móvil usando Jetpack Compose.

---

## 🧩 Funcionalidades

- 📋 Listado de recetas almacenadas localmente
- ➕ Agregar nueva receta con nombre, ingredientes, pasos y categoría
- 🔍 Ver detalles completos de una receta
- ✏️ Editar receta existente
- 🗑️ Eliminar receta
- 🌐 Consultar recetas desde una API externa (solo lectura)
- ✅ Persistencia con Room
- 🔁 Arquitectura desacoplada con ViewModel y StateFlow

---

## 🧱 Arquitectura

El proyecto está dividido en las siguientes capas:

```
- data/
  - local/     → Room (Entidad, DAO, Base de datos)
  - remote/    → Retrofit (API)
  - repository/ → Fuente de datos unificada
- domain/
  - model/     → Entidad compartida (Receta)
  - usecase/   → Casos de uso (Insertar, obtener, eliminar)
- presentation/
  - navigation/ → Control de rutas entre pantallas
  - screens/    → UI para Home, Formulario, Detalles
  - viewmodel/  → Lógica de presentación y estados
```

---

## 🛠️ Tecnologías y Librerías

| Herramienta | Uso |
|-------------|-----|
| Jetpack Compose | Interfaz de usuario |
| Navigation Compose | Navegación entre pantallas |
| Room | Base de datos local |
| ViewModel + StateFlow | Manejo de estados y lógica |
| Retrofit + Moshi | Consumo de API externa |
| Kotlin Coroutines | Llamadas asíncronas y reactivas |
| Android Studio | IDE de desarrollo |

---

## 🖼️ Estructura de Pantallas

- **HomeScreen**: Lista de recetas, botón para agregar
- **FormScreen**: Formulario para nueva receta o editar
- **DetailScreen**: Vista detallada con botón editar/eliminar

---

## 🌍 API Externa

La aplicación se conecta a una API REST pública para consultar recetas de ejemplo (solo lectura). Ejemplo: [TheMealDB](https://www.themealdb.com/api.php)

---

## 🚀 Cómo ejecutar

1. Clona este repositorio:
   ```bash
   git clone https://github.com/usuario/MyRecipesApp.git
   ```
2. Abre el proyecto en **Android Studio**.
3. Ejecuta el proyecto en un emulador o dispositivo físico con Android 7.0+.
4. Asegúrate de tener conexión a internet para el consumo de la API.

---

## 📌 Requisitos del Taller

✅ Jetpack Compose  
✅ Room (CRUD local)  
✅ Retrofit + API externa  
✅ Navigation entre 3 pantallas  
✅ ViewModel con StateFlow  
✅ Código limpio y modular

---

## ✨ Créditos

Desarrollado por: [Tu nombre aquí]  
Proyecto académico para el curso de desarrollo móvil con Android y Jetpack Compose.
