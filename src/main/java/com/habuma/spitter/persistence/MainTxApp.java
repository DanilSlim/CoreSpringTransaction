package com.habuma.spitter.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.habuma.spitter.domain.Test;

public class MainTxApp {

	public static void main(String[] args) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("appContext.xml");
		
		GetData getData=(GetData) context.getBean("progTx");
		
		Test test=new Test();
		test.setID(123);
		test.setName("PgTx234");
		
		//getData.setData(test);
		
		getData.printData(123);
		
		getData=(GetData) context.getBean("declareTransactional");
		
		getData.printData(777);

	}

}
