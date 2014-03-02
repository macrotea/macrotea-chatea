package com.mtea.chatea.data;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.mtea.chatea.action.client.ChatClientAction;
import com.mtea.chatea.model.ChatCounter;
import com.mtea.chatea.model.ChatStat;

@Service
public class ScheduleService implements ApplicationListener<ContextRefreshedEvent>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChatClientAction chatClientAction;
	
	public void autoPush() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				logger.info("推送线程已启动");
				while (true) {
					
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(60));
					} catch (InterruptedException ignore) {
					}
					chatClientAction.statPushed(new ChatStat(ChatCounter.loginTotalCounter, ChatCounter.msgTotalCounter));
				}
			}
		}).start();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			autoPush();
		}
	}
}
