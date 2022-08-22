package TransactionLoseEfficacy.controller;

import TransactionLoseEfficacy.mapper.TransactionLossEfficacyMapper;
import TransactionLoseEfficacy.model.Response;
import TransactionLoseEfficacy.model.Transactionlossefficacy;
import TransactionLoseEfficacy.service.TransactionLoseEfficacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ymx
 * @Name TransactionLoseEfficacyController
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

@RestController
@RequestMapping("/transactionLose")
public class TransactionLoseEfficacyController {

    @Autowired
    private TransactionLoseEfficacyService transactionLoseEfficacyService;

    @Autowired
    private TransactionLossEfficacyMapper transactionLossEfficacyMapper;


    @RequestMapping("/test")
    public Response transactionTest(){
        try {
            transactionLoseEfficacyService.testTransactionModel();
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查");
        }
    }

    @RequestMapping("/cannnotWork1")
    public Response transactionCannotWork1(){
        try {
            transactionLoseEfficacyService.testTransactionNotWork();
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查");
        }
    }

    @RequestMapping(value = "/cannnotWork2",method = RequestMethod.GET)
    public Response transactionCannotWork2(@RequestParam int sampleCode){
        try {
            // 如果sampleCode 为0, 则调取 testTransactionNotWorkSecCondition 这个方法
            if (sampleCode == 0) {
                transactionLoseEfficacyService.testTransactionNotWorkSecCondition();
            } else if (sampleCode == 1) {
                // 如果sampleCode 为1， 则调取testTransactionNotWorkThirdCondition 这个方法
                transactionLoseEfficacyService.testTransactionNotWorkThirdCondition();
            }
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查 " + e.getMessage());
        }
    }

    @RequestMapping(value = "/cannotWork3", method = RequestMethod.GET)
    public Response transactionCannotWork3(@RequestParam int test) {
        try {
            if (test == 0) {
                testTransactional();
            }
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查 " + e.getMessage());
        }
    }
    // 如果transaction函数不是public，则事务失效
    @Transactional(rollbackFor = Exception.class)
    void testTransactional() throws Exception {
        Transactionlossefficacy transactionlossefficacy = new Transactionlossefficacy();
        transactionlossefficacy.setName("testController");
        transactionlossefficacy.setListId(123);
        transactionLossEfficacyMapper.insert(transactionlossefficacy);
        if (transactionlossefficacy.getListId() == 123) {
            throw new Exception("interrupt the transaction, test the transaction performance");
        }
        transactionlossefficacy.setName("testController1");
        transactionLossEfficacyMapper.insert(transactionlossefficacy);
    }

    @RequestMapping(value = "/cannotWork4", method = RequestMethod.GET)
    public Response transactionCannotWork4(@RequestParam int test) {
        try {
            if (test == 0) {
                transactionLoseEfficacyService.testTransactionWithMyisamEngine();
            }
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查 " + e.getMessage());
        }
    }

    @RequestMapping(value = "/cannotWork5", method = RequestMethod.GET)
    public Response transactionCannotWork5(@RequestParam int test) {
        try {
            if (test == 0) {
                transactionLoseEfficacyService.testTransactionWithTryCatch();
            }
            return Response.success();
        } catch(Exception e) {
            return Response.fail("内部报错，请检查 " + e.getMessage());
        }
    }
}
