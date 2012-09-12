package md.victordov.lab.services;

import java.util.ArrayList;

import md.victordov.lab.common.exception.MyDaoException;

public interface GenericService<T> {
	
	public long createFunction() throws MyDaoException;
	
	public boolean updateFunction() throws MyDaoException;

	public void printOne() throws MyDaoException;

	public  ArrayList<T> printAll() throws MyDaoException;


}
