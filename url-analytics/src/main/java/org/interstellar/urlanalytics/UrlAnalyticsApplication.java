package org.interstellar.urlanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.interstellar.urlanalytics", "org.interstellar.urlshortenercommonlib"})

public class UrlAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlAnalyticsApplication.class, args);
	}

}
