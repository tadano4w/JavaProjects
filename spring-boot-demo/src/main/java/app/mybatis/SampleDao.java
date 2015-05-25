package app.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDao {

	@Autowired
	SqlSessionFactory sessionFactory;

	public SampleData getValue() {
		try (SqlSession session = sessionFactory.openSession()) {
			SampleMapper mapper = session.getMapper(SampleMapper.class);
			return mapper.select();
		}
	}
}
