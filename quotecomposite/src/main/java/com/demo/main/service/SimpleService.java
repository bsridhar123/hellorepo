package com.demo.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RefreshScope
public class SimpleService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private RestTemplate restTemplate;

	
	public void doSomeWorkNewSpan() throws InterruptedException {
	    logger.info("I'm in the original span");
	 
	    Span newSpan = tracer.createSpan("newSpan");
	    try {
	        Thread.sleep(1000L);
	        logger.info("I'm in the new span doing some cool work that needs its own span");
	    } finally {
	        tracer.close(newSpan);
	    }
	 
	    logger.info("I'm in the original span");
	}
	@Async
	public void asyncMethod()  {
	    logger.info("Start Async Method");
	    try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    logger.info("End Async Method");
	}
	@Cacheable(value = "employee")
	public String getByEmployeeNo(String empNo) throws InterruptedException {
		logger.info("Inside getByEmployeeNo in SimpleService");
		simulateSlowService();
		doSomeWorkNewSpan();
		return "Employee:" + empNo;
	}

	@Cacheable(value = "department")
	public String getByDepartmentNo(String departmentNo) {
		logger.info("Inside getByDepartmentNo in quote-composite-SimpleService");
		//simulateSlowService();
		return restTemplate.getForObject("http://localhost:5003/employee", String.class);
		//return "Department-quotecomposite::" + departmentNo;
	}
	@Cacheable(value = "customer")
	public String getCustomerByCustomerNo(String customerNo) {
		logger.info("Inside getCustomerByCustomerNo in SimpleService");
		//simulateSlowService();
		return restTemplate.getForObject("http://localhost:5003/employee", String.class);
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
