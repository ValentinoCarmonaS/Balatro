# TP2 Algoritmos 3: Balatro

## Descripción del Proyecto

**Balatro** es un juego roguelike de cartas desarrollado en Java, diseñado como parte del curso avanzado **Algoritmos y Programación III** en la **Facultad de Ingeniería de la Universidad de Buenos Aires (FIUBA)**. El juego combina lógica compleja con una interfaz gráfica intuitiva, donde el jugador perfecciona su mazo para superar rondas desafiantes aplicando estrategias y efectos especiales. El proyecto fue desarrollado bajo estándares de calidad industrial y principios de ingeniería de software, garantizando modularidad, extensibilidad y mantenibilidad.

## Grupo 10

* **Integrante 1** - [Carmona, Valentino](https://github.com/ValentinoCarmonaS)
* **Integrante 2** - [Da Ruos, Ivan](https://github.com/ivanotello)
* **Integrante 3** - [Darnay, Nicolas](https://github.com/nicolasdarnayfacultad)
* **Integrante 4** - [Lamanna, Fabrizio](https://github.com/Fabrizio-Lamanna)
* **Integrante 5** - [Loe, Sebastian](https://github.com/SebastianLoe1)

Corrector: **Joaquin Rivero** & **Pablo Suarez**

---

## 🎮 Características Principales

### Sistema de Efectos Dinámicos
- **Comodines con comportamientos únicos**: Bonificaciones por descarte, activación aleatoria y destrucción estratégica.
- **Tarots modificables**: Alteran puntuaciones y multiplicadores de cartas, permitiendo estrategias personalizadas.

### Lógica de Manos de Poker
- **Detección eficiente de combinaciones clásicas**: Algoritmos optimizados para identificar escaleras, full house, color, entre otras.
- **Gestión de puntuaciones**: Sistema flexible que calcula puntuaciones basadas en combinaciones y efectos aplicados.

### Gestión de Rondas por Objetivos
- **Sistema de turnos limitados**: Metas de puntuación progresivas que aumentan el desafío en cada ronda.
- **Tienda entre rondas**: Adquisición de mejoras estratégicas para el mazo.

### Interfaz Intuitiva
- **Diseño UX/UI**: Enfocado en accesibilidad y retroalimentación visual clara.
- **Validación de errores**: Flujos interactivos que guían al jugador y previenen acciones inválidas.

---

## 🛠️ Arquitectura y Tecnologías

### Diseño Orientado a Objetos
- **Principios SOLID**: Aplicados para garantizar modularidad, extensibilidad y mantenibilidad.
- **Patrones de diseño**:
  - **Strategy**: Utilizado para implementar efectos de comodines y tarots, reduciendo el acoplamiento en un 40%.
  - **Observer**: Gestiona eventos del juego, como cambios en el estado de las cartas o rondas.
- **Sistema de puntuación flexible**: Gestión dinámica de multiplicadores y bonificaciones.

### Stack Técnico
- **Lenguaje**: Java 17+
- **Interfaz Gráfica**: JavaFX 20
- **Pruebas Automatizadas**: JUnit 5
- **Integración Continua**: GitHub Actions para builds y despliegues.

---

## Contribuciones y Competencias Clave

### Arquitectura Modular y Extensible
- **Implementación de patrones de diseño**: Aplicación de los patrones **Strategy** y **Observer** para reducir el acoplamiento y facilitar la extensibilidad del código.
- **Diseño modular**: Estructura del código que permite la incorporación de nuevas funcionalidades sin afectar el sistema existente.

### Desarrollo de Interfaz Gráfica con JavaFX
- **Diseño de interfaz intuitiva**: Priorización de la usabilidad y accesibilidad, con retroalimentación visual clara para el jugador.
- **Validación de errores**: Implementación de flujos interactivos que previenen acciones inválidas y guían al jugador.

### Pruebas y CI/CD
- **Pruebas unitarias con JUnit**: Garantía de la calidad del código mediante pruebas automatizadas.
- **Integración Continua con GitHub Actions**: Automatización de builds y despliegues, asegurando entregas eficientes y consistentes.

### Gestión Colaborativa
- **Coordinación de equipo**: Liderazgo de un equipo de 5 desarrolladores, utilizando **Git** para control de versiones y entregas iterativas.
- **Cumplimiento de plazos**: Entrega de 4 iteraciones funcionales en 5 semanas con un 100% de cumplimiento de los plazos establecidos.

### Resolución de Problemas Complejos
- **Verificación dinámica de manos de póquer**: Implementación de algoritmos optimizados para la detección de combinaciones.
- **Efectos aleatorios de cartas**: Solución de desafíos relacionados con la aleatoriedad y la aplicación de efectos, aplicando principios **SOLID** y diseño modular.

---

## Conclusión

El desarrollo de **Balatro** representó un desafío significativo en términos de diseño de software y resolución de problemas complejos. A través de la aplicación de principios de ingeniería de software, patrones de diseño y herramientas modernas, se logró crear un juego robusto, extensible y de alta calidad. Este proyecto no solo consolidó mis habilidades técnicas en Java y diseño de software, sino también mi capacidad para liderar equipos y gestionar proyectos bajo plazos ajustados.
