package web.panda.common.response;

import lombok.ToString;

@ToString
public enum CommonCode implements ResultCode{
    SUCCESS(true,200,"success"),
    NULLRESULT(true,10000,"没有任何数据"),
    FAIL(false,11111,"操作失败！"),
    TOKENERROR(false,11112,"token错误或失效"),
    WITHOUTLOGIN(false,11112,"没有登录"),
    WITHACCOUNT(false,11113,"没有该账号"),
    PASSWORDERROR(false,11114,"密码错误"),
    ISNOTYOURES(false,11116,"订单不是你的！"),
    NUMBERPASS(false,11117,"订单数量超过！"),
    TIMESETERROR(false,11118,"时间设置冲突！"),
    WITHOUTSUB(false,11119,"请先长按二维码，关注鼎安交通"),
    APPOINTMENTED(false,11120,"你已经预定了"),
    ORDERED(false,11121,"你已经参与抢购了"),
    NULLFILE(false,11130,"文件为空"),
    FILETYPEERROR(false,11131,"文件格式不正确！"),
    ADDRESSNULL(false,11132,"地址为空！");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    }
