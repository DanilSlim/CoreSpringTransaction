package com.habuma.spitter.persistence;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.habuma.spitter.domain.Test;

@Repository
public class DeclareTransactional implements GetData {
	
	private SessionFactory sessionFactory;
	
	
	@Autowired
	public  DeclareTransactional(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	
	private Session getCurrentSession() {
		
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public void printData() {
		// TODO Auto-generated method stub

	}

	@Transactional(transactionManager = "txManager", propagation = Propagation.REQUIRED,readOnly = true)
	@Override
	public void printData(int id) {
		Test test=getCurrentSession().get(Test.class, id);
		
		System.out.println("DeclareTransactional ID="+test.getID()+" Test DeclareTransactional Name:"+test.getName());

	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void setData(T test) {
		// TODO Auto-generated method stub

	}

}
