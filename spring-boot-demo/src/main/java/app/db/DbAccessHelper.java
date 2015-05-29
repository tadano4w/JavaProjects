package app.db;


/**
 * DBアクセスヘルパ
 */
public final class DbAccessHelper {

    /** LIKE句のエスケープ文字 */
    private static final String LIKE_ESCAPE_TOKEN = "\\";

    /** LIKE句でエスケープする対象の文字 */
    private static final String LIKE_ESCAPE_TARGETS = "%_";

    /**
     * コンストラクタ
     */
    private DbAccessHelper() {
    }

    /**
     * 部分一致用文字列作成
     *
     * @param str 作成元となる文字列
     * @return 作成した文字列
     */
    public static String toPartialMatchPattern(String str) {
        return "%" + escapeLike(str) + "%";
    }

    /**
     * 前方一致用文字列作成
     *
     * @param str 作成元となる文字列
     * @return 作成した文字列
     */
    public static String toPrefixMatchPattern(String str) {
        return escapeLike(str) + "%";
    }

    /**
     * 後方一致用文字列作成
     *
     * @param str 作成元となる文字列
     * @return 作成した文字列
     */
    public static String toSuffixMatchPattern(String str) {
        return "%" + escapeLike(str);
    }

    /**
     * LIKE句用エスケープ文字列作成
     *
     * @param str エスケープする文字列
     * @return エスケープした文字列
     */
    private static String escapeLike(String str) {
        if (str == null) {
            throw new NullPointerException();
        }

        int length = str.length();
        if (length == 0) {
            return str;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = str.charAt(i);
            if (isLikeEscapedTarget(ch)) {
                builder.append(LIKE_ESCAPE_TOKEN);
            }
            builder.append(ch);
        }
        return builder.toString();
    }

    /**
     * LIKE句用エスケープの対象文字かどうかを返す
     *
     * @param ch 検査する文字
     * @return LIKE句用エスケープの対象文字かどうか
     */
    private static boolean isLikeEscapedTarget(char ch) {
    	return LIKE_ESCAPE_TARGETS.contains(Character.toString(ch));
    }
}
