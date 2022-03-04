package net.csdn.ac.tasking.springboot.repository;

import io.swagger.annotations.Api;
import net.csdn.ac.tasking.springboot.entity.User;

/**
 * 用户 Repository
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:56
 */
@Api("用户")
public interface UserRepository extends BaseRepository<User> {

}