package com.aykj.loglink.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.aykj.loglink.Constants;
import com.aykj.loglink.LogLinker;

/**
 * Create On 2018-12-04 16:13
 *
 * @author mo
 */
@Activate
public class DubboConsumerLogRequestIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        invocation.getAttachments().put(Constants.getRequestIdKey(), LogLinker.getRequestId());
        return invoker.invoke(invocation);
    }
}
