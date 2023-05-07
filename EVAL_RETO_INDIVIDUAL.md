## Hace todos los puntos pedidos (40%)

#### El nombre del repositorio es el correcto (mdas-api-${nombre}_${apellido})

OK

#### Permite obtener los detalles de un pokemon vía endpoint (datos + número de veces que se ha marcado como favorito)

OK, aunque el contador de número de veces marcado como favorito debería empezar en 0, no en null:

```
{"status":"OK","message":null,"data":{"id":{"id":448},"name":{"name":"lucario"},"height":{"value":12.0},"weight":{"value":540.0},"pokemonFavoritesCounter":{"value":null}}}
```

#### Actualiza el contador de favoritos vía eventos

El endpoint no funciona, aunque por el código parece que debería hacerlo correctamente

#### ¿Se controlan las excepciones de dominio? Y si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

Veo que se capturan excepciones, pero intentando añadir un pokemon como favorito recibo un 500 con un "Internal Server
  Error" que justo no es una excepción de dominio.

#### Tests unitarios

KO

#### Tests de aceptación

KO

#### Tests de integración

KO

**Puntuación: 25/40**

## Se aplican conceptos explicados (50%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

- El encargado de publicar los eventos es la capa de aplicación (el orquestador).
- En el servicio de dominio se importan clases de infraestructura:

```
  import com.mdas.api.g6.user.user.infrastructure.publisher.Publisher;
  import com.mdas.api.g6.user.user.infrastructure.publisher.dto.MessagePublisher;
```

- El evento debería crearse en el dominio en el método de `addFavoritePokemon`. Lo que estás haciendo es emitir un dto
  de infraestructura en vez de un evento de dominio.
- Los métodos del repo deberían ser más generics (usar `save()` tanto para creación como para actualización; no usar
  un `update()`)
- El servicio `PokemonSaver` no sería necesario... con un `repository.save()` sería suficiente, no?

#### Aggregates + VOs

OK

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK

#### Se usan subscribers

OK, aunque el nombre `Consumer` no es muy claro... ya que por cada evento de dominio que se emita debería haber un
subscriber.

#### Se lanzan eventos de dominio

KO, el "evento" `MessagePublisher` está definido en infraestructura. Además, debería lanzarse en el agregado, no en el
servicio de dominio. Si delegas la responsabilidad en el servicio de dominio, no estás asegurando la integridad del
agregado y que siempre que se añada un favorito se lance el evento.

#### Se utilizan object mothers

OK

**Puntuación: 37/50**

## Facilidad setup + README (10%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo usar la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

- Es bastante manual (con un docker-compose podrías haber automatizado la creación de la cola).
- Al no tener el comando `jq` los cURLs no funcionan (indicas que no es obligatorio el comando).
- Se devuelve también el número de pokemons favoritos, pero en el encabezado del Readme se
  indica `Get a pokemon details (ID, Name, Height and Weight)`
- El endpoint de añadir un pokemon favorito no
  funciona: `{"timestamp":"2023-05-07T15:54:08.268+00:00","status":500,"error":"Internal Server Error","path":"/ms-ne-pokemon/v1/user/add-favorite-pokemon"}`

**Puntuación: 6/10**

## Extra

- Se ha entregado tarde

**Puntuación: -10**


**PUNTUACIÓN FINAL: 58/100**
