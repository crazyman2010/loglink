package com.aykj.loglink;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;


public class LogLinker {
    private final static ConcurrentHashMap<Long, String> requestIdMap = new ConcurrentHashMap<>();

    /**
     * 获取当前线程请求id
     * 如果请求id不存在,则生成一个
     *
     * @return
     */
    public static String getRequestId() {
        String requestId = requestIdMap.get(Thread.currentThread().getId());
        if (StringUtils.isEmpty(requestId)) {
            requestId = RequestIdGenerator.nextId();
            requestIdMap.put(Thread.currentThread().getId(), requestId);
        }
        return requestId;
    }


    /**
     * 设置当前线程的请求id
     *
     * @param requestId
     */
    public static void setRequestId(String requestId) {
        if (StringUtils.isEmpty(requestId)) {
            requestIdMap.remove(Thread.currentThread().getId());
        } else {
            requestIdMap.put(Thread.currentThread().getId(), requestId);
        }
    }

    /**
     * 清除requestId
     */
    public static void clearRequestId() {
        requestIdMap.remove(Thread.currentThread().getId());
    }
}
