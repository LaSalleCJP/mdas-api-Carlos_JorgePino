## Hace todos los puntos pedidos (40%)

#### Permite obtener los detalles de un pokemon vía endpoint

OK

#### Si no existe el pokemon, ¿se lanza una excepción de dominio?

OK

#### Si la api da timeout, ¿se lanza una excepción de dominio?

OK

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK

#### Tests de aceptación

OK

#### Tests de integración

KO

**Puntuación:  32/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

OK

#### Aggregates + VOs

OK

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK, aunque ¿por qué habéis añadido un adapter? La propia implementación del repositorio http ya debería hacer
ese "mapeo" a dominio.

#### Se utilizan object mothers

OK

**Puntuación: 40/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo usar la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

OK

**Puntuación: 20/20**

**PUNTUACIÓN FINAL: 92/100**
