package app.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * mybatisのMapperインタフェースに付与するアノテーション
 */
@Target(ElementType.TYPE)
public @interface Mapper {
}
