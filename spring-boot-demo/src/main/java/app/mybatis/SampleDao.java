package app.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao {

	@Autowired
	SqlSessionTemplate sqlSession;

	public SampleData getValue() {
		SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
		return mapper.select();
	}

	public void insertName(String name) {
		SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
		mapper.insertName(name);
	}

	public int deleteAll() {
		SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
		return mapper.deleteAll();
	}

	public String selectName() {
		SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
		return mapper.selectName();
	}
}
