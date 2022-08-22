package TransactionLoseEfficacy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ymx
 * @Name Resposne
 * @CreateTime 2022/8/18
 * @ProjectName TransactionLoseEfficacy
 * @Description:
 */

@Data
public class Response implements Serializable {

    private int code;

    private String message;

    private Object data;

    public Response(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static Response success() {
        return new Response(ResponseCode.success.getCode(),null,ResponseCode.success.getMessage());
    }
    public static Response success(Object data) {
        return new Response(ResponseCode.success.getCode(),data,ResponseCode.success.getMessage());
    }

    public static Response fail(Object data) {
        return new Response(ResponseCode.fail.getCode(), data, ResponseCode.fail.getMessage());
    }
}