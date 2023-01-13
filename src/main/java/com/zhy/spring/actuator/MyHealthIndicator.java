package com.zhy.spring.actuator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


//@Component
public class MyHealthIndicator extends AbstractHealthIndicator implements InitializingBean {

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;

	public MyHealthIndicator(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super("MyHealthIndicator health check failed");
		this.jdbcTemplate = jdbcTemplate;
		this.dataSource = dataSource;
	}

	@Override
	protected void doHealthCheck(Health.Builder builder) {
		builder.up().withDetail("MyHealthIndicator", "MyHealthIndicator");
		String result = jdbcTemplate.queryForObject("select 1", String.class);

		builder.status(StringUtils.isBlank(result) ? Status.UP : Status.DOWN);

		DataSource dataSource = this.dataSource;
	}


	@Override
	public void afterPropertiesSet() throws Exception {

		System.out.println("MyHealthIndicator-afterPropertiesSet");

	}
}
