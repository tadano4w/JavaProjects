package app.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleService {

	@Autowired
	SampleDao dao;

	@Transactional
	public SampleData getValue() {
		return dao.getValue();
	}
}