package com.demo.main.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.main.service.SimpleService;

@RestController
public class CacheDemoController {
	private static final Logger logger = LoggerFactory.getLogger(CacheDemoController.class);

	private final SimpleService simpleService;

	public CacheDemoController(SimpleService simpleService) {
		this.simpleService = simpleService;

	}
	/*@Autowired
	private Executor executor;
	     
	    @GetMapping("/new-thread")
	    public String helloSleuthNewThread() {
	        logger.info("New Thread");
	        Runnable runnable = () -> {
	            try {
	                Thread.sleep(1000L);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            logger.info("I'm inside the new thread - with a new span");
	        };
	        executor.execute(runnable);
	 
	        logger.info("I'm done - with the original span");
	        return "success";
	}*/
	  /*  @GetMapping("/async")
	    public String helloSleuthAsync() throws InterruptedException {
	        logger.info("Before Async Method Call");
	        simpleService.asyncMethod();
	        logger.info("After Async Method Call");
	         
	        return "success";
	    }*/
	@GetMapping("/employee")
	public void getByEmployeeNo() throws InterruptedException{
		logger.info("employee-12 -->" + simpleService.getByEmployeeNo("12"));
		
	}
	
	
	@GetMapping("/department")
	public void getMembersByNo(){
		logger.info("department-25 -->" + simpleService.getByDepartmentNo("25"));
		
	}
	
	@GetMapping("/customer")
	public void getCustomerByCustomerNo(){
		logger.info("customer-25 -->" + simpleService.getCustomerByCustomerNo("25"));
		
	}
}
