package web.panda.common.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ObjectResult<T> {
    //数据列表
    private T obj;

    public ObjectResult() {
    }

    public ObjectResult(T obj) {
        this.obj = obj;
    }
}
