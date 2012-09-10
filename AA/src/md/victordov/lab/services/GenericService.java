package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;
import md.victordov.lab.vo.Universitate;

public interface GenericService<T> {
	
	public long createFunction() throws MyDaoException;
	
	public boolean updateFunction(T t) throws MyDaoException;

	public T getOne(Long id) throws MyDaoException;

	public  ArrayList<T> getAll() throws MyDaoException;
	
	public boolean deleteFunction(Long id);


}
