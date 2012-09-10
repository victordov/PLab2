package md.victordov.lab.dao;

import java.util.Collection;

import md.victordov.lab.common.exception.MyDaoException;

public interface GenericDAO<T> {

	Collection<T> retrieve() throws MyDaoException;

	T retrieve(long id) throws MyDaoException;

	boolean create(T t);

	boolean update(T t);

	boolean delete(Long id);

}
