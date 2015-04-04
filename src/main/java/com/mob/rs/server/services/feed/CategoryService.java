package com.mob.rs.server.services.feed;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mob.rs.server.hibernate.model.CategoryEO;

@Component("categoryService")
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {

	@PersistenceContext
	private EntityManager em;

	public List<CategoryEO> findByParentCategory(CategoryEO category) {
		TypedQuery<CategoryEO> query = null;
		if (category != null) {
			query = em.createNamedQuery(
					"Category.findByParendId", CategoryEO.class);
			query.setParameter("parent", category);
		} else {
			query = em.createNamedQuery(
					"Category.findByNullParendId", CategoryEO.class);
		}
		List<CategoryEO> categories = query.getResultList();
		return categories;
	}
	
	public CategoryEO getCategoryEO(Integer id) {
		return em.find(CategoryEO.class, id);
	}

}
