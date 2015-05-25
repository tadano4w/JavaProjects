package app.error;

/**
 * システムエラー例外
 */
public class SystemErrorException extends RuntimeException {

    /** シリアルバージョンID */
    private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @param message 例外の詳細メッセージ
     */
    public SystemErrorException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     * @param message 例外の詳細メッセージ
     * @param cause 例外の原因
     */
    public SystemErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
