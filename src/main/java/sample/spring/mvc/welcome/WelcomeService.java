package sample.spring.mvc.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.spring.mvc.db.Sample;
import sample.spring.mvc.db.SampleMapper;

@Service
@Transactional
public class WelcomeService {

	@Autowired
	SampleMapper dao;

	public Sample getDto(Integer id) {

		//		// SQLiteだとテーブルを認識しないためコメントアウト
		//		SampleExample example = new SampleExample();
		//		example.createCriteria().andIdEqualTo(id);
		//
		//		return dao.selectByExample(example).get(0).getName();

		Sample sample = new Sample();
		sample.setId(id);
		sample.setName("ほげ");
		sample.setRoom("101");

		return sample;
	}

	public String findName(Integer id) {
		return dao.findName(id);
	}

	public String getName(Integer id) {
		return dao.getName(id);
	}
}
