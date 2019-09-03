package com.aykj.loglink;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * Create On 2018-12-04 16:13
 *
 * @author heyangya
 */
@Activate(group = {Constants.CONSUMER})
public class LogDubboConsumerRequestIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        invocation.getAttachments().put(com.aykj.loglink.Constants.LOG_REQUEST_ID, LogLinker.getRequestId());
        return invoker.invoke(invocation);
    }
}
