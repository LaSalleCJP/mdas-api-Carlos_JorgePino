## Hace todos los puntos pedidos (40%)

#### Permite crear usuarios vía endpoint

OK

#### Permite añadir favoritos vía endpoint

OK

#### Si el pokemon ya está marcado como favorito, ¿se lanza una excepción de dominio?

OK

#### Si el usuario ya existe, ¿se lanza una excepción de dominio?

OK, aunque está delegando la responsabilidad de crear ids de usuarios al backend en vez de al cliente. Tal y como está
hecho, os estáis apoyando en el `Name`, pero lo correcto sería hacerlo en el `Id` para que fuera un sistema idempotente.
¿Y si queremos crear dos usuarios que se llamen `John Doe`?

#### Si el usuario no existe y se le intenta asignar un pokemon favorito, ¿se lanza una excepción de dominio?

OK

#### Si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

OK

#### Hay tests unitarios

##### Capa aplicación

OK

##### Capa dominio

OK

**Puntuación: 38/40**

## Se aplican conceptos explicados (40%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

OK

#### Aggregates + VOs

- `user.getFavoritePokemons().getFavoritePokemons()` es un code smell que se llama "Feature Envy". Hay que evitar los
  gets para que los objetos de negocio puedan trabajar con sus propios datos. En vuestro caso, hay una clase que se
  llama `FavoritePokemons` dentro de user. Eso está bien porque le dais el peso que tiene la lista de Pokemon
  favoritos (First Class Citizen ✅). Sin embargo, para añadir un pokemon favorito y comprobar si ese pokemon ya está
  como favorito está en `UserAddPokemonFavorite`. Los servicios de dominio son para orquestar lógica de dominio, no para
  hacer lógica de dominio que les corresponde a otros objetos. Podríais haber hecho algo
  como `User.addFavoritePokemon(pokemonId)` y que dentro de este método se haga `favoritePokemons.add(pokemonId)`. Éste
  último método es quien debería hacer esta lógica:

 ```
 if (user.getFavoritePokemons().getFavoritePokemons().contains(new FavoritePokemon(pokemonId))) {
   throw new PokemonAlreadyAddException("Pokemon already add on list with Id: " + pokemonId);
 }
  ```

Y esta también:

```
user.getFavoritePokemons().getFavoritePokemons().add(new FavoritePokemon(pokemonId));
```

Es decir, `FavoritePokemons` quedaría algo como:

```
public class FavoritePokemons {
    private Set<FavoritePokemon> favoritePokemons;
    
    private void add(FavoritePokemon pokemon) throws PokemonAlreadyAddException {
        guardPokemonAlreadyAdded(pokemon);
        
        favoritePokemons.add(pokemon);
    }

    private void guardPokemonAlreadyAdded(FavoritePokemon pokemon) throws PokemonAlreadyAddException {
        if (favoritePokemons.contains(pokemon)) {
            throw new PokemonAlreadyAddException("Pokemon already add on list with Id: " + pokemon.getId().toString());
        }
    }
}
```

#### No se trabajan con tipos primitivos en dominio

KO, en el `UserAddPokemonFavorite` se le pasa un `pokemonId` que es de tipo `Integer`. Y luego `FavoritePokemon` tiene
también un `Integer` en lugar de un `PokemonId`.

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK

#### Se utilizan object mothers

OK, pero ¿por qué hay un `PokemonCreator` que hace lo que haría un `PokemonMother`?

**Puntuación: 27/40**

## Facilidad setup + README (20%)

#### El README contiene al menos los apartados "cómo ejecutar la aplicación", "cómo user la aplicación"

Falta indicar como lanzar los tests

#### Es sencillo seguir el apartado "cómo ejecutar la aplicación"

OK

**Puntuación: 17/20**

**PUNTUACIÓN FINAL: 82/100**
