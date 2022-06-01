package hu.progkorny.MetalCD.model.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
  }

  public NotFoundException(final String message) {
    super(message);
  }

  public NotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
