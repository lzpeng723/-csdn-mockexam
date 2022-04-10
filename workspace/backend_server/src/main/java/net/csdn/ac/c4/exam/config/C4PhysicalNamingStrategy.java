package net.csdn.ac.c4.exam.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * @date: 2020/3/19
 * @time: 14:45
 * @author:   lzpeng723
 * @see org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy
 */
public class C4PhysicalNamingStrategy extends SpringPhysicalNamingStrategy {


    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier identifier = super.toPhysicalTableName(name, jdbcEnvironment);
        return getIdentifier("data_" + identifier.getText(), identifier.isQuoted(), jdbcEnvironment);
    }


}

