package com.mall.schedle.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DBScan {

	@PostConstruct
	public void postConStruct() {

		ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
		es.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {

				Date d = new Date();

				DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				String s = sdf.format(d);
				System.out.println(s);

			}
		}, 0, 60, TimeUnit.SECONDS);

	}
}
