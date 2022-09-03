package com.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Repo;
import com.entity.Feedback;

@Service
public class AppService {

	@Autowired
	private Repo Repo;
	
	public boolean addFeedback( Feedback f) {
		Repo.save(f);
		return true;
	}
}
