package net.csdn.ac.tasking.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import net.csdn.ac.tasking.springboot.entity.User;
import net.csdn.ac.tasking.springboot.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 21:52
 */
@Service
public class UserService extends BaseService<User> {


    protected static final String ENTITY_NAME = "net.csdn.ac.tasking.springboot.entity.User";

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#result.id", unless = "#result == null")
    public User save(User user) {
        return super.save(user);
    }

    @Override
    @CacheEvict(value = ENTITY_NAME, key = "#id")
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public User update(String id, User model) {
        return super.update(id, model);
    }

    @Override
    @Cacheable(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public User findById(String id) {
        return super.findById(id);
    }

    /**
     * 从 json 读取数据
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public List<User> readDataFromJson(String json) throws JsonProcessingException {
        List<User> list = objectMapper.readValue(json, new TypeReference<List<User>>() {
        });
        return list;
    }


}
