package com.xt8.service.impl;

import static com.xt8.util.Constants.MAX_RECORDS;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.xt8.service.BasicService;
import com.xt8.util.EnableLog;
import com.xt8.util.Logic;

@Transactional
public abstract class BasicServiceImpl implements BasicService {

	@EnableLog
	protected Logger logger = null;

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public Object findById(Class clazz, Serializable id) {
		Session session = sessionFactory.openSession();
		Object entity = session.get(clazz, id);
		session.close();
		return entity;
	}

	@Override
	public Serializable save(Object entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Serializable id = session.save(entity);
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public Object saveAndReturn(Class clazz, Object entity) {
		Serializable id = save(entity);
		return findById(clazz, id);
	}

	@Override
	public void update(Object entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Object entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteByBatch(Collection entities) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Object entity : entities) {
			if (null != entity) {
				session.delete(entity);
			}
		}
		tx.commit();
		session.close();
	}

	@Override
	public List executeQuery(String hql, Object[] params) {
		return executeQueryByPage(hql, params, 1, MAX_RECORDS);
	}

	@Override
	public List executeQueryByPage(String hql, Object[] params, int pageNow,
			int pageSize) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List list = query.setFirstResult((pageNow - 1) * pageSize)
				.setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	@Override
	public int executeUpdate(String hql, Object[] params) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		int rows = query.executeUpdate();
		session.close();
		return rows;
	}

	@Override
	public long count(String hql, Object[] params) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(0, params[i]);
			}
		}
		long resultsCount = (Long) query.uniqueResult();
		session.close();
		return resultsCount;
	}

	@Override
	public int countPages(String hql, Object[] params, int pageSize) {
		int countRecords = (int) count(hql, params);
		if (0 == (countRecords % pageSize)) {
			return countRecords / pageSize;
		} else {
			return (countRecords / pageSize) + 1;
		}
	}

	@Override
	public ResultSet originalQueryByPage(String sql, Object[] params,
			int pageIndex, int pageSize) throws SQLException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		String pSql = sql + " Limit " + (pageIndex - 1) * pageSize + ", "
				+ pageSize + ";";
		System.out.println("sql: " + pSql);
		PreparedStatement pstmt = session.connection().prepareStatement(pSql,
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		if (null != params) {
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
		}
		ResultSet rs = pstmt.executeQuery();
		tr.commit();
		// session.close();
		return rs;
	}

	@Override
	public ResultSet originalQuery(String sql, Object[] params)
			throws SQLException {
		return originalQueryByPage(sql, params, 1, MAX_RECORDS);
	}

	@Override
	public List patternQuery(Class clazz, Map<String, String> map, Logic logic) {
		return patternQueryByPage(clazz, map, logic, 1, MAX_RECORDS);
	}

	@Override
	public List patternQueryByPage(Class clazz, Map<String, String> map,
			Logic logic, int pageIndex, int pageSize) {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(clazz);
		Set<String> keys = map.keySet();
		Criterion multiConditions = null;
		if (logic == Logic.OR) {
			multiConditions = Restrictions.idEq(-1);// 这个初始条件永远为假
		} else {
			multiConditions = Restrictions.not(Restrictions.idEq(-1));// 这个初始条件永远为真
		}
		for (String key : keys) {
			if (null != key && !"".equals(key)) {
				String value = map.get(key);
				if (null != value && !"".equals(value)) {
					if (logic == Logic.OR) {
						multiConditions = Restrictions.or(multiConditions,
								Restrictions.like(key, value, MatchMode.START));
					} else {
						multiConditions = Restrictions.and(multiConditions,
								Restrictions.like(key, value, MatchMode.START));
					}

				}
			}
		}
		c.add(multiConditions);
		List list = c.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	@Override
	public List find(Class clazz, Map<String, Object> conditions,
			int pageIndex, int pageSize) {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(clazz);

		if (null != conditions) {
			Set<String> keys = conditions.keySet();
			for (String key : keys) {
				if (null != key && !"".equals(key)) {
					c.add(Restrictions.eq(key, conditions.get(key)));
				}
			}
		}

		List list = c.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize).list();
		session.close();
		return list;
	}

	@Override
	public List find(Class clazz, Map<String, Object> conditions) {
		Session session = sessionFactory.openSession();
		Criteria c = session.createCriteria(clazz);
		if (conditions != null) {
			Set<String> keys = conditions.keySet();
			for (String key : keys) {
				if (null != key && !"".equals(key)) {
					c.add(Restrictions.eq(key, conditions.get(key)));
				}
			}
		}
		List list = c.list();
		session.close();
		return list;
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
