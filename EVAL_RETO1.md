## Hace todos los puntos pedidos (40%)

#### Dado un nombre vía argumento, devolver sus tipos

OK

#### Dado un nombre vía endpoint, devolver sus tipos

KO, devuelve los tipos pero no en JSON que es como pide el enunciado

#### Si no existe el pokemon, ¿se lanza una excepción de dominio?

OK

#### Si la api da timeout, ¿se lanza una excepción de dominio?

OK

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP/un error legible en consola?

OK, aunque la excepción `PokeApiConnectionErrorException` tiene exactamente el nombre de la implementación del
repositorio (`PokeApi...`). Aquí nos estamos cargando la inversión de dependencias. Si mañana en lugar de una API es una
base de datos mysql, tenemos que renombrar el dominio y es lo que queremos evitar. Un nombre más adecuado
sería `RepositoryUnavailableException` o algo similar

**Puntuación: 33/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

OK a las capas, aunque el caso de uso no debería ser una interfaz (no hay puerto y adaptador ni es necesaria la
inversión de dependencias). Además, que el nombre sea `***Impl` es un code smell.

La separación entre bounded context y module no existe. Vuestro BC es `pokemon`, ¿cuál es el módulo?.
Como sugerencias:

```
pokedex
|_ pokemon
   |_ application
   |_ domain
   |_ infrastructure
```

O incluso

```
pokemon
|_ pokemon
   |_ application
   |_ domain
   |_ infrastructure
```

#### Aggregates + VOs

OK

#### No se trabajan con tipos primitivos en dominio

El id del pokemon es un integer, debería ser `PokemonId` o similar

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK, aunque el nombre del `PokemonRepositoryPort` no me convence. La palabra `Port` es un concepto técnico y hablar de
cosas técnicas en dominio no es buena idea. Lo mismo con el sufijo `DomainService`, etc.

**Puntuación: 30/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo user la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

OK

**Puntuación: 20/20**

## Extra

- Commits en "baby steps". Pequeños y legibles
- Muy buen README, gracias
- Docker! :)

**Puntuación: +10**

## Observaciones

- Estaría bien en el README indicar los requisitos de la aplicación para ejecutarse. Por lo que veo, como mínimo docker
  es necesario y si queremos compilarla, npm también
- Usad
  el [estándar](https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Why-you-should-make-kebab-case-a-URL-naming-convention-best-practice)
  de escritura de URLs (kebab case). Ej: `/pokemon-type` o `/type` pasándole el `name` como un query param

**PUNTUACIÓN FINAL: 93/100**
