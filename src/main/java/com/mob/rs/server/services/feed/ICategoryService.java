package com.mob.rs.server.services.feed;

import java.util.List;

import com.mob.rs.server.hibernate.model.CategoryEO;

public interface ICategoryService {
	
	public List<CategoryEO> findByParentCategory(CategoryEO category);
	
	public CategoryEO getCategoryEO(Integer id);

}
