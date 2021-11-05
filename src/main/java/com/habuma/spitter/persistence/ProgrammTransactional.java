package com.habuma.spitter.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.habuma.spitter.domain.Test;

@Repository
public class ProgrammTransactional implements GetData {
	
	private SessionFactory sessionFactory;
	
	private TransactionTemplate transactionTemplate;
	
	


	public ProgrammTransactional(SessionFactory sessionFactory) {
		
		this.sessionFactory=sessionFactory;
		
		
	}
	
	
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}




	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
		
		this.transactionTemplate.setTimeout(30);
	}


    

	private Session getCurrentSession() {
		
		return this.sessionFactory.getCurrentSession();
	}
	
	
	
	

	@Override
	public void printData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printData(int id) {
		
		Test test=transactionTemplate.execute(new TransactionCallback<Test>() {

			@Override
		    public Test doInTransaction(TransactionStatus status) {
				
				try{
                      Test test=getCurrentSession().get(Test.class,id);
				
					   return test;
					}
				catch(RuntimeException ex) {
					
					status.setRollbackOnly();
					
					return null;
					
				}
				
			}
		});
		
		if (!test.equals(null)) System.out.println("Programm transaction ID="+test.getID()+" Name:"+test.getName());

	}

	@Override
	public void setData() {
		// TODO Auto-generated method stub

	}


	@SuppressWarnings("hiding")
	@Override
	public  <Test> void setData( Test test) {
		
		transactionTemplate.execute(new TransactionCallbackWithoutResult () {
			
			// the code in this method runs in a transactional context
			protected void doInTransactionWithoutResult(TransactionStatus txStatus) {
				
				try {
					
					getCurrentSession().save(test);

					System.out.println("Save programm tx is OK");
					
		            
		        } catch (RuntimeException ex) {
		        	
		            txStatus.setRollbackOnly();
		            System.out.println("Save programm tx is FAILED");
		        }
				
				
			}
			
			
		});
		
		
		
	}

}
