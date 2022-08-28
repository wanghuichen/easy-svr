package com.chen.common;

/**
 * 统一返回类型，全局唯一，采用双检锁式单例实现
 * @author whc
 * @date 2022/6/24 14:57
 */
public class ResultBean<C> {

    private Integer code;

    private String msg;

    private C data;


    /**防止 JMM 指令重排 */
    private static volatile ResultBean res;

    /** 私有化构造方法*/
    private ResultBean() {
    }

    /**
     * 创建对象，保证全局唯一性
     * @param code 状态码
     * @param msg  消息信息
     * @param data 具体数据
     * @return
     */
    public static ResultBean create(Integer code, String msg, Object data) {
        if (null == res) {
            synchronized (ResultBean.class) {
                if (null == res) {
                    res = new ResultBean();
                }
            }
        }
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    /**
     * 构造方法重载
     * @param code
     * @param msg
     * @return
     */
    public static ResultBean create(Integer code, String msg) {
        if (null == res) {
            synchronized (ResultBean.class) {
                if (null == res) {
                    res = new ResultBean();
                }
            }
        }
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    public void setData(Integer code, String msg, C data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public C getData() {
        return data;
    }

    public void setData(C data) {
        this.data = data;
    }
}
