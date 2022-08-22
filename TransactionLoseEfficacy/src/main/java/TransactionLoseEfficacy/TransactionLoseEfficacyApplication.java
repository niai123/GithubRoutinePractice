package TransactionLoseEfficacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author ymx
 * @Name TransactionLoseEfficacyApplication
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

@SpringBootApplication
@ComponentScan("TransactionLoseEfficacy")
@EnableTransactionManagement
public class TransactionLoseEfficacyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionLoseEfficacyApplication.class,args);
    }
}
