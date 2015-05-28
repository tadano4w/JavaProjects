package app.mybatis;

public interface SampleMapper {
	SampleData select();

	void insertName(String name);
}
