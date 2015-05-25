package app.error;

/**
 * 業務例外
 */
public class BusinessErrorException extends RuntimeException {

    /** シリアルバージョンID */
    private static final long serialVersionUID = 1L;

    /** デフォルトメッセージID */
    private static final String DEFAULT_MESSAGE_ID = "businessError.defaultMessage";

    /** メッセージID */
    private String messageId = DEFAULT_MESSAGE_ID;

    /**
     * コンストラクタ
     */
    public BusinessErrorException() {
        super();
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     */
    public BusinessErrorException(String message) {
        super(message);
    }

    /**
     * コンストラクタ
     *
     * @param cause 例外の原因
     */
    public BusinessErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param cause 例外の原因
     */
    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * メッセージIDを取得します。
     * @return メッセージID
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * メッセージIDを設定します。
     * @param messageId メッセージID
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
