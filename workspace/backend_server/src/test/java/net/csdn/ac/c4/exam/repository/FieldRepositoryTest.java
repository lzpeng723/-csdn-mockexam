package net.csdn.ac.c4.exam.repository;

import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.entity.Field;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author lzpeng
 * @version 1.0
 * @date 2022/4/6 17:58
 */
@Slf4j
@SpringBootTest
class FieldRepositoryTest {

    @Autowired
    private FieldRepository fieldRepository;

    @Test
    void testFindAll() {
        List<Field> fieldList = fieldRepository.findAll();
        log.info("{}", fieldList);
    }

}