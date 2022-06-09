package hu.progkorny.metalcd.model;

import java.util.Objects;

/**
 * THIS IS METAL CD MODEL CLASS .
 */
public class MetalCD {

  private Long id;
  private String name;
  private Genre genre;
  private Type type;

  public MetalCD() {
  }

  /**
   * METAL CD CONSTRUCTOR.
   */
  public MetalCD(final Long id,
                 final String name,
                 final Genre genre,
                 final Type type) {
    this.id = id;
    this.name = name;
    this.genre = genre;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(final Genre genre) {
    this.genre = genre;
  }

  public Type getType() {
    return type;
  }

  public void setType(final Type type) {
    this.type = type;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MetalCD)) {
      return false;
    }
    final MetalCD metalCD = (MetalCD) o;
    return Objects.equals(id, metalCD.id)
            && Objects.equals(name, metalCD.name)
            && genre == metalCD.genre
            && type == metalCD.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, genre, type);
  }

  @Override
  public String toString() {
    return "RolePlay{"
            + "id=" + id
            + ", name='" + name + '\''
            + ", genre=" + genre
            + ", type=" + type
            + '}';
  }
}
