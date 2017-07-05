package com.demo.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimpleService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);
	
	

	
	//@Cacheable(value = "employee")
	public String getByEmployeeNo(String empNo) throws InterruptedException {
		logger.info("Inside getByEmployeeNo in SimpleService");
		simulateSlowService();
		doSomeWorkNewSpan();
		return "Employee:" + empNo;
	}

	//@Cacheable(value = "department")
	public String getByDepartmentNo(String departmentNo) {
		logger.info("Inside getByDepartmentNo in SimpleService");
		//simulateSlowService();
		return restTemplate.getForObject("http://localhost:5002/customer", String.class);
		//return "Department:" + departmentNo;
	}
	//@Cacheable(value = "customer")
	public String getCustomerByCustomerNo(String customerNo) {
		logger.info("Inside getCustomerByCustomerNo in SimpleService");
		simulateSlowService();
		return "Customer:" + customerNo;
	}

	private void simulateSlowService() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
