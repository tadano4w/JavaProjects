package app.mybatis;

import app.db.Mapper;

@Mapper
public interface SampleMapper {
	SampleData select();

	void insertName(String name);

	int deleteAll();

	String selectName();
}
