package com.bigcorp.companies.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bigcorp.companies.persistence.dao.ChildDao;
import com.bigcorp.companies.persistence.model.Child;
import com.bigcorp.companies.rest.bean.ChildRestBean;

@Component
public class ChildService {

	@Autowired
	private ChildDao childDao;

	public ChildDao getChildDao() {
		return childDao;
	}

	public void setChildDao(ChildDao childDao) {
		this.childDao = childDao;
	}

	public Child getChild(Long id) {
		return this.childDao.find(id);
	}

	public void removeById(Long id) {
		this.childDao.delete(id);
	}

	public ChildRestBean save(ChildRestBean childRestBean) {
		Child child = ChildRestBean.toChild(childRestBean);
		Child savedChild = this.childDao.merge(child);
		return ChildRestBean.toChildRestBean(savedChild);
	}

	public Collection<ChildRestBean> findAll() {
		Collection<ChildRestBean> restBeans = new ArrayList<>();
		for (Child child : this.childDao.findAll()) {
			restBeans.add(ChildRestBean.toChildRestBean(child));
		}
		return restBeans;
	}

	public ChildRestBean findById(Long childId) {
		Child child = this.childDao.find(childId);
		return ChildRestBean.toChildRestBean(child);
	}

}
