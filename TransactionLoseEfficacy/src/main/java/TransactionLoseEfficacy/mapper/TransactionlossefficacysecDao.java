package TransactionLoseEfficacy.mapper;

import TransactionLoseEfficacy.model.Transactionlossefficacysec;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionlossefficacysecDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Transactionlossefficacysec record);

    int insertSelective(Transactionlossefficacysec record);

    Transactionlossefficacysec selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Transactionlossefficacysec record);

    int updateByPrimaryKey(Transactionlossefficacysec record);
}