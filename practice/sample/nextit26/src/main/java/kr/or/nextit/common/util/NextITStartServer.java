package kr.or.nextit.common.util;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NextITStartServer {
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		Runtime rt = Runtime.getRuntime();
		System.out.println("========================start========================");
		try {
			Process proc = rt.exec("/home/pc32/anaconda3/envs/class1/bin/python /home/pc32/PycharmProjects/pythonProject/week5/day20/flask_api/ex_02_flaskrest.py");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
