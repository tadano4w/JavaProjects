package app.mybatis;

public interface SampleDao {

	SampleData getValue();
	void insertName(String name);
	int deleteAll();
	String selectName();
}
