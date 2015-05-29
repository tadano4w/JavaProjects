package app.db;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@RunWith(SpringJUnit4ClassRunner.class)

// AnnotationConfigContextLoaderを指定することで内部クラスで@Configurationが付与されたクラスの設定を読み込む
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class DbAccessHelperTest {

	// 以下はあまりよい例ではないが、テスト用に作成したBeanをAutowiredできているのが確認できる。

    // テストに使用するBeanを用意する
    @Configuration
    static class TestConfig {
    	@Bean(name="testString")
    	public String testString() {
    		return "abc%def_ghi";
    	}
    }

    // 定義したBeanをDIする
    @Autowired
    @Qualifier("testString")
    private String pattern;

    @Test
	public void 部分一致用文字列が生成されること() {
		Assert.assertThat(DbAccessHelper.toPartialMatchPattern(pattern), is("%abc\\%def\\_ghi%"));
	}

    @Test(expected = NullPointerException.class)
	public void 部分一致用文字列生成ではNullを許容しないこと() {
		DbAccessHelper.toPartialMatchPattern(null);
	}

    @Test
	public void 前方一致用文字列が生成されること() {
		Assert.assertThat(DbAccessHelper.toPrefixMatchPattern(pattern), is("abc\\%def\\_ghi%"));
	}

    @Test(expected = NullPointerException.class)
	public void 前方一致用文字列生成ではNullを許容しないこと() {
		DbAccessHelper.toPrefixMatchPattern(null);
	}

    @Test
	public void 後方一致用文字列が生成されること() {
		Assert.assertThat(DbAccessHelper.toSuffixMatchPattern(pattern), is("%abc\\%def\\_ghi"));
	}

    @Test(expected = NullPointerException.class)
	public void 後方一致用文字列生成ではNullを許容しないこと() {
		DbAccessHelper.toSuffixMatchPattern(null);
	}
}
