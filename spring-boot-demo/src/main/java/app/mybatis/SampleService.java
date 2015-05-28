package app.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SampleService {

	@Autowired
	SampleDao dao;

	public SampleData getValue() {
		return dao.getValue();
	}

	// @Transactionalを指定すると、自動でコミット/ロールバックが行われる
	@Transactional
	public void register(String name) {
		dao.insertName(name);

    	if (name.contains("error")) {
    		throw new RuntimeException();
    	}
	}
}