package com.coderworld968.orm;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class MyDataSourceTransactionManager extends AbstractPlatformTransactionManager {

    private DataSource dataSource;

    public MyDataSourceTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    @Override
    protected Object doGetTransaction() throws TransactionException {
        TransactionX transactionX = new TransactionX();
        transactionX.setConnectionHolder(new ConnectionHolder(dataSource.getConnection()));
        return transactionX;
    }

    @SneakyThrows
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition)
            throws TransactionException {
        TransactionX transactionX = (TransactionX) transaction;
        Connection connection = transactionX.getConnectionHolder().getConnection();
        log.debug("Transaction doBegin connection:{}", connection);
        TransactionSynchronizationManager
                .bindResource(dataSource, transactionX.getConnectionHolder());
        connection.setAutoCommit(false);
    }

    @SneakyThrows
    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        TransactionX transactionX = (TransactionX) status.getTransaction();
        Connection connection = transactionX.getConnectionHolder().getConnection();
        log.debug("Transaction doCommit connection:{}", connection);
        connection.commit();
        connection.setAutoCommit(true);
        TransactionSynchronizationManager.unbindResource(dataSource);
    }

    @SneakyThrows
    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        TransactionX transactionX = (TransactionX) status.getTransaction();
        Connection connection = transactionX.getConnectionHolder().getConnection();
        log.debug("Transaction doRollback connection:{}", connection);
        connection.rollback();
        connection.setAutoCommit(true);
        TransactionSynchronizationManager.unbindResource(dataSource);
    }

    @SneakyThrows
    protected void doCleanupAfterCompletion(Object transaction) {
        TransactionX transactionX = (TransactionX) transaction;
        Connection connection = transactionX.getConnectionHolder().getConnection();
        connection.close();
    }
}
