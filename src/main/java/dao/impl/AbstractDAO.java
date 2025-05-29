package dao.impl;

import dao.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
}
