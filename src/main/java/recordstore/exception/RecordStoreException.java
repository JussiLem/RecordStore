package recordstore.exception;

public class RecordStoreException extends RuntimeException {

  public RecordStoreException() {
    super();
  }

  public RecordStoreException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public RecordStoreException(final String message) {
    super(message);
  }

  public RecordStoreException(final Throwable cause) {
    super(cause);
  }
}
