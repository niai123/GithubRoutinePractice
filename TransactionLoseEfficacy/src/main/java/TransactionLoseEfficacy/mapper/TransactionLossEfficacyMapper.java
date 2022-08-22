package TransactionLoseEfficacy.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ymx
 * @Name TransactionLoseEfficacyMapper
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

import TransactionLoseEfficacy.model.Transactionlossefficacy;

@Mapper
public interface TransactionLossEfficacyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Transactionlossefficacy record);

    int insertSelective(Transactionlossefficacy record);

    Transactionlossefficacy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transactionlossefficacy record);

    int updateByPrimaryKey(Transactionlossefficacy record);
}