package com.mw.education.controller;

import java.util.HashMap;

/**
 * AjaxResult用于封装Ajax请求的返回结果。
 * 它继承自HashMap，以便于将返回码、消息和数据等信息以键值对的形式组织。
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** 返回码标签，用于标识返回码在结果中的键 */
    /** 状态码 */
    public static final String CODE_TAG = "code";
    /** 消息标签，用于标识消息在结果中的键 */
    /** 返回内容 */
    public static final String MSG_TAG = "msg";
    /** 数据标签，用于标识数据在结果中的键 */
    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 构造函数，创建一个空的AjaxResult对象。
     */
    public AjaxResult() {
    }

    /**
     * 创建一个表示成功的AjaxResult对象。
     *
     * @return 返回一个设置好成功码和成功消息的AjaxResult对象。
     */
    public static AjaxResult success() {
        return new AjaxResult().code(200).msg("操作成功");
    }

    /**
     * 创建一个表示失败的AjaxResult对象。
     *
     * @return 返回一个设置好失败码和失败消息的AjaxResult对象。
     */
    public static AjaxResult error() {
        return new AjaxResult().code(500).msg("操作失败");
    }

    /**
     * 创建一个表示请求错误的AjaxResult对象。
     *
     * @return 返回一个设置好错误码和错误消息的AjaxResult对象。
     */
    public static AjaxResult bad() {
        return new AjaxResult().code(400).msg("请求错误");
    }

    /**
     * 设置返回码。
     *
     * @param code 返回码。
     * @return 当前AjaxResult对象，支持链式调用。
     */
    public AjaxResult code(int code) {
        super.put(CODE_TAG, code);
        return this;
    }

    /**
     * 设置消息。
     *
     * @param msg 消息。
     * @return 当前AjaxResult对象，支持链式调用。
     */
    public AjaxResult msg(String msg) {
        super.put(MSG_TAG, msg);
        return this;
    }

    /**
     * 设置数据。
     *
     * @param data 数据。
     * @return 当前AjaxResult对象，支持链式调用。
     */
    public AjaxResult data(Object data) {
        super.put(DATA_TAG, data);
        return this;
    }

    /**
     * 设置查询结果，包括总条数和数据集。
     *
     * @param total 总条数。
     * @param rows 数据集。
     * @return 当前AjaxResult对象，支持链式调用。
     */
    /** 查询结果 */
    public AjaxResult rows(int total, Object rows) {
        super.put(DATA_TAG, new Rows(total, rows));
        return this;
    }

    /**
     * 设置更新操作的结果，包括影响的行数。
     *
     * @param affectedRows 影响的行数。
     * @return 当前AjaxResult对象，支持链式调用。
     */
    /** 更新结果 */
    public AjaxResult updateResult(int affectedRows) {
        super.put(DATA_TAG, new UpdateResult(affectedRows));
        return this;
    }

    /**
     * 重写put方法，支持链式调用。
     *
     * @param key 键。
     * @param value 值。
     * @return 当前AjaxResult对象。
     */
    /*
     * 方便链式调用
     */
    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * Rows类用于封装查询结果中的总条数和数据集。
     */
    static class Rows {
        private int total;
        private Object rows;
        private static final String type = "select";

        public Rows(int total, Object rows) {
            this.total = total;
            this.rows = rows;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public Object getRows() {
            return rows;
        }

        public void setRows(Object rows) {
            this.rows = rows;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * UpdateResult类用于封装更新（插入/删除）操作的结果，主要是影响的行数。
     */
    static class UpdateResult{
        private int affectedRows;
        private static final String type = "update/insert/delete";

        public UpdateResult(int affectedRows) {
            this.affectedRows = affectedRows;
        }

        public int getAffectedRows() {
            return affectedRows;
        }

        public void setAffectedRows(int affectedRows) {
            this.affectedRows = affectedRows;
        }

        public String getType() {
            return type;
        }
    }
}
