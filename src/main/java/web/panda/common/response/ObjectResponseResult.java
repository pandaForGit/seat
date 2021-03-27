package web.panda.common.response;

import lombok.Data;
import lombok.ToString;

/**
 * 分页统一响应
 */
@Data
@ToString
public class ObjectResponseResult extends ResponseResult {

    ObjectResult objectResult;
    //统一响应 数据集，操作是否成功，操作代码，提示信息
    public ObjectResponseResult(ResultCode resultCode,ObjectResult objectResult){
        super(resultCode);
        this.objectResult = objectResult;
    }

}