package TransactionLoseEfficacy.service;
/**
 * @Author ymx
 * @Name TransactionLoseEfficacyService
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

public interface TransactionLoseEfficacyService {
    void testTransactionModel() throws Exception;

    void testTransactionNotWork() throws Exception;

    void testTransactionNotWorkSecCondition() throws Exception;

    void testTransactionNotWorkThirdCondition() throws Exception;

    void testTransactionWithMyisamEngine() throws Exception;

    void testTransactionWithTryCatch();
}
