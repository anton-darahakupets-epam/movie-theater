package quoters;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Anton_Darahakupets
 *
 */
public class TerminatorQuoter implements Qouters {
	
	@InjectRandomInt(min = 2, max = 2)
	private int i;
	
	private static int a = 3;
	
	
	public TerminatorQuoter() {
		System.out.println("Hello from Constructor");
		System.out.println("i=" + i);
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Hello from INIT method  and a=" + a );
	}
	
	@Override
	public void sayQuoter() {
		for (int b = 0; b <= i; b++) {
			System.out.println("I WILL BE BACK");
		}
		System.out.println(i);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Hello from destroy");
	}

}
