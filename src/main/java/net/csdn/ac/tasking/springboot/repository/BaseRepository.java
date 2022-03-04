package net.csdn.ac.tasking.springboot.repository;

import net.csdn.ac.tasking.springboot.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @date: 2020/2/1
 * @time: 23:36
 * @author: lzpeng
 */
@NoRepositoryBean
//@RepositoryRestResource
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T>/*, QuerydslPredicateExecutor<T>*/ {


    /**
     * 得到不在 ids 列表中的实体
     *
     * @param ids id 列表
     * @return
     */
    List<T> findAllByIdNotIn(Iterable<Long> ids);


}