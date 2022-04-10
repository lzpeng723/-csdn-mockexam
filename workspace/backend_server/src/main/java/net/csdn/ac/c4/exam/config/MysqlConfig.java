package net.csdn.ac.c4.exam.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.stereotype.Component;

/**
 * @author lzpeng723
 */
@Component
public class MysqlConfig extends MySQL8Dialect {
	
	@Override
	public String getTableTypeString() {
		return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}

}