# Autofoco

Red social de fotografía de vehículos (carros y motos), desarrollada como taller
de la materia Dispositivos Móviles, Universidad Surcolombiana.

## Integrantes

- **Juan David Fierro Calderón** — estudiante de sexto semestre de Ingeniería de
  Software, Universidad Surcolombiana.
- **Luis Samuel Toconas Parra** — estudiante de sexto semestre de Ingeniería de
  Software, Universidad Surcolombiana. ([GitHub](https://github.com/Sam-9606))

## Qué es

App nativa de Android que simula una red social enfocada en fotografía
automotriz: los usuarios suben fotos de su carro o moto, encuentros,
restauraciones y detalles de motor o rines. Los patrones de interacción están
inspirados en Instagram, pero con marca e identidad visual propias.

## Stack técnico

- Kotlin
- Layouts XML + Views (sin Jetpack Compose)
- ViewBinding
- Material Components para Android
- `ConstraintLayout` como raíz de cada pantalla, `LinearLayout` para agrupaciones internas
- Navegación con `Activity` + `Intent` explícito
- `RecyclerView` para el feed
- Sin backend ni persistencia: todos los datos viven en memoria mientras la app está abierta

## Estructura del proyecto

```
app/src/main/
├── java/com/usco/autofoco/
│   ├── LoginActivity.kt
│   ├── RegistroActivity.kt
│   ├── PrincipalActivity.kt
│   ├── Publicacion.kt          // data class de una publicación del feed
│   ├── PublicacionAdapter.kt   // adapter del RecyclerView del feed
│   ├── Extras.kt               // claves de los putExtra entre Activities
│   └── Validaciones.kt         // funciones puras de validación de formularios
└── res/
    ├── layout/                 // activity_login, activity_registro, activity_principal, item_publicacion
    ├── values/                 // colors, dimens, strings, themes
    ├── drawable/                // formas base, íconos y placeholders de fotos del feed
    └── menu/                    // menú de la bottom navigation
```

No hay subcarpetas de paquetes ni capas adicionales (MVVM, Room, Retrofit, etc.):
el taller pide una app simple de Views + Intents, y la estructura se mantiene así
de plana a propósito.

## Cómo ejecutar

1. Clonar el repositorio.
2. Abrirlo en Android Studio y esperar a que termine el sync de Gradle.
3. Ejecutar en un emulador o dispositivo físico con API 24 o superior.

No necesita configuración adicional: no usa internet, ni claves de API, ni base
de datos.

## Cómo probar

No hay backend real, así que el login y el registro solo validan el formato de
los datos, no verifican contra una cuenta que ya exista:

- **Login:** cualquier correo con formato válido (ej. `piloto@autofoco.com`) y
  una contraseña de 6 caracteres o más. El switch "Recordarme" se refleja luego
  en la pantalla principal.
- **Registro:** completa todos los campos, escribe la misma contraseña dos
  veces, elige qué manejas (Carro, Moto o Ambos) y acepta los términos.

Ambos flujos navegan a la pantalla principal, que muestra los datos recibidos
por `Intent` (nombre o usuario, vehículo, si la sesión quedó recordada, etc.) y
un feed de 6 publicaciones de ejemplo con el corazón de "me gusta" funcional.
Desde la pantalla principal, solo la pestaña "Inicio" de la barra inferior está
activa; las demás muestran un aviso de que estarán disponibles próximamente.

## Flujo de navegación

```
LoginActivity  --Intent-->  PrincipalActivity
      |
      '--Intent-->  RegistroActivity  --Intent-->  PrincipalActivity
```

Al llegar a `PrincipalActivity` desde cualquiera de los dos caminos se llama a
`finish()` en la pantalla de origen, así que el botón atrás no regresa al login
ni al registro.
