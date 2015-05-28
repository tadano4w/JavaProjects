package app.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao {

	@Autowired
	SqlSession sqlSession;

	public SampleData getValue() {
		SampleMapper mapper = sqlSession.getMapper(SampleMapper.class);
		return mapper.select();
	}
}
