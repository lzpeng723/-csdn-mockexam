package net.csdn.ac.check.core.dao;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 数据库访问对象
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Component
public class MySQLDao<T> extends BaseObject {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询数量
	 *
	 * @param sql
	 * @param args
	 * @return
	 */
	public int count(final String sql, final @Nullable Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, args);
		} catch (DataAccessException e) {
			logger.warn("count data exception: {}", e.getMessage());
		}
		return Constant.FAILURE_CODE;
	}

	/**
	 * 查询单条数据
	 *
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 */
	public Object findOne(final String sql, final RowMapper<?> rowMapper, final @Nullable Object... args) {
		try {
			List<?> list = jdbcTemplate.query(sql, rowMapper, args);
			if (list.size() > Constant.DEFAULT_INDEX) {
				return list.get(Constant.DEFAULT_INDEX);
			}
		} catch (DataAccessException e) {
			logger.warn("find data exception: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 获得列表
	 *
	 * @param sql
	 * @param rowMapper
	 * @param args
	 * @return
	 */
	public List<?> find(final String sql, final RowMapper<?> rowMapper, final @Nullable Object... args) {
		try {
			List<?> list = jdbcTemplate.query(sql, rowMapper, args);
			if (Constant.DEFAULT_INDEX != list.size()) {
				return list;
			}
		} catch (DataAccessException e) {
			logger.warn("find data exception: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 获得列表
	 *
	 * @param sql
	 * @param params
	 * @param rowMapper
	 * @return
	 */
	public List<?> find(final String sql, final Map<String, Object> params, final RowMapper<?> rowMapper) {
		try {
			NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
			List<?> list = jdbc.query(sql, params, rowMapper);
			if (Constant.DEFAULT_INDEX != list.size()) {
				return list;
			}
		} catch (DataAccessException e) {
			logger.warn("find data exception: {}", e.getMessage());
		}
		return null;
	}

	/**
	 * 创建数据
	 *
	 * @param sql
	 * @param args
	 * @return
	 */
	public int create(final String sql, final @Nullable Object... args) {
		try {
			if (Constant.DEFAULT_INDEX < jdbcTemplate.update(sql, args)) {
				return Constant.SUCCESS_CODE;
			}
			return Constant.FAILURE_CODE;
		} catch (DuplicateKeyException e) {
			logger.warn("data duplicate exception: {}", e.getMessage());
			throw new DuplicateKeyException("data duplicate exception");
		} catch (DataAccessException e) {
			logger.warn("create data exception: {}", e.getMessage());
			throw new RuntimeException("create data exception");
		}
	}

	/**
	 * 更新或删除数据
	 *
	 * @param sql
	 * @param args
	 * @return
	 */
	public boolean update(final String sql, final @Nullable Object... args) {
		try {
			return Constant.DEFAULT_INDEX <= jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			logger.warn("update data exception: {}", e.getMessage());
			throw new RuntimeException("update data exception");
		}
	}

	/**
	 * 批量操作数据
	 *
	 * @param sql
	 * @param list
	 * @return
	 */
	public int batch(final String sql, final List<Object[]> list) {
		try {
			int[] result = jdbcTemplate.batchUpdate(sql, list);
			if (result.length != list.size()) {
				return Constant.FAILURE_CODE;
			}
			return Constant.SUCCESS_CODE;
		} catch (DuplicateKeyException e) {
			logger.warn("data duplicate exception: {}", e.getMessage());
			throw new DuplicateKeyException("data duplicate exception");
		} catch (DataAccessException e) {
			logger.warn("batch data exception: {}", e.getMessage());
			throw new RuntimeException("batch data exception");
		}
	}
}
