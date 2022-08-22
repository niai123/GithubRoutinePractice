package TransactionLoseEfficacy.service.impl;


import TransactionLoseEfficacy.mapper.TransactionLossEfficacyMapper;
import TransactionLoseEfficacy.mapper.TransactionlossefficacysecDao;
import TransactionLoseEfficacy.model.Response;
import TransactionLoseEfficacy.model.Transactionlossefficacy;
import TransactionLoseEfficacy.model.Transactionlossefficacysec;
import TransactionLoseEfficacy.service.TransactionLoseEfficacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ymx
 * @Name TransactionLoseEfficacyServiceImpl
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

@Service
public class TransactionLoseEfficacyServiceImpl implements TransactionLoseEfficacyService {

    @Autowired
    private TransactionLossEfficacyMapper transactionLoseEfficacyMapper;

    @Autowired
    private TransactionlossefficacysecDao transactionlossefficacysecDao;

    //如果不加rollbackfor，则transactional 不生效
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactionModel() throws Exception {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        transactionlossefficacy.setListId(10);
        transactionlossefficacy.setName("test1");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
        if (transactionlossefficacy.getListId() == 10) {
            throw new Exception("test");
        }
        transactionlossefficacy.setName("test2");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
    }

    //如果不加rollbackfor，则transactional 不生效
    @Transactional
    @Override
    public void testTransactionNotWork() throws Exception {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        transactionlossefficacy.setListId(10);
        transactionlossefficacy.setName("test1");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
        if (transactionlossefficacy.getListId() == 10) {
            throw new Exception("test");
        }
        transactionlossefficacy.setName("test2");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
    }

    public void makeChaos() {
        try {
            testTransactionNotWorkSecCondition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 当事务方法直接在方法中调取事务方法，事务仍然生效
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactionNotWorkSecCondition() throws Exception {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        transactionlossefficacy.setListId(10);
        transactionlossefficacy.setName("test3");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
        testTransactionNotWorkThirdCondition();
        transactionlossefficacy.setName("test4");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
    }
    // 当事务方法被其他方法调用，事务仍然生效？ 不是说不有效嘛，还是我的实验方法不对
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTransactionNotWorkThirdCondition() throws Exception {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        transactionlossefficacy.setListId(8);
        transactionlossefficacy.setName("test3");
        testThrowExceptionWithoutTransaction();
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
        transactionlossefficacy.setName("test4");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    // 需要数据库支持事务操作，如果数据库引擎不支持事务操作，则transaction 失效
    public void testTransactionWithMyisamEngine() throws Exception {
        Transactionlossefficacysec transactionlossefficacysec = new Transactionlossefficacysec();
        transactionlossefficacysec.setListid(777);
        transactionlossefficacysec.setName("testMyisamEngine");
        transactionlossefficacysecDao.insert(transactionlossefficacysec);
        if (transactionlossefficacysec.getListid() == 777) {
            throw new Exception("myisam can not support transaction operation");
        }
        transactionlossefficacysec.setName("testMyisamEngine1");
        transactionlossefficacysecDao.insert(transactionlossefficacysec);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    // 有try catch 吞异常的时候，事务失效
    public void testTransactionWithTryCatch() {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        try {
            transactionlossefficacy.setListId(7);
            transactionlossefficacy.setName("test5");
            transactionLoseEfficacyMapper.insert(transactionlossefficacy);
            if (transactionlossefficacy.getListId() == 7) {
                throw new Exception();
            }
            transactionlossefficacy.setName("test6");
            transactionLoseEfficacyMapper.insert(transactionlossefficacy);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void testThrowExceptionWithoutTransaction() throws Exception{
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        boolean a = true;
        if (a == true) {
            throw new Exception("deliberately damage the system");
        }
        transactionlossefficacy.setListId(100);
        transactionlossefficacy.setName("test5");
        transactionLoseEfficacyMapper.insert(transactionlossefficacy);
    }

}
