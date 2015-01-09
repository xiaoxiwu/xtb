package com.xt8.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xt8.util.Logic;

public interface BasicService {

	public Serializable save(final Object entity);

	public Object saveAndReturn(Class clazz, final Object entity);

	public void update(final Object entity);

	public void delete(final Object entity);

	public void deleteByBatch(final Collection<Object> entities);

	public Object findById(Class clazz, final Serializable id);

	public List executeQuery(final String hql, final Object[] params);

	public List executeQueryByPage(final String hql, final Object[] params,
			final int pageNow, final int pageSize);

	public int executeUpdate(final String hql, final Object[] params);

	public long count(final String hql, final Object[] params);

	public int countPages(final String hql, final Object[] params,
			final int pageSize);

	/**
	 * 原生ｓｑｌ语句查询
	 * 
	 * @param sql
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public ResultSet originalQueryByPage(String sql, Object[] params,
			int pageIndex, int pageSize) throws SQLException;

	public ResultSet originalQuery(String sql, Object[] params)
			throws SQLException;

	public List find(Class clazz, Map<String, Object> conditions, int pageIndex,
			int pageSize);

	public List find(Class clazz, Map<String, Object> conditions);

	/**
	 * 模糊查询
	 * 
	 * @param clazz
	 *            实体类
	 * @param map
	 *            键值对，键为实体类中的属性名，值为模糊的值
	 * @param logic
	 *            逻辑，与/或
	 * @return
	 */
	public List patternQuery(Class clazz, Map<String, String> map, Logic logic);

	public List patternQueryByPage(Class clazz, Map<String, String> map,
			Logic logic, int pageIndex, int pageSize);
}
