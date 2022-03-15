package com.property.emlakburada.repository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class JdbcConnectionRepository implements DbConnectionRepository{
	
	private String dbUrl = "localhost";

	@Override
	public void connect() {
		// TODO connect to db with jdbc
		
	}

}
