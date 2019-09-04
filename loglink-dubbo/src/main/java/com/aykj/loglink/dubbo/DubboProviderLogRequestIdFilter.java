package com.aykj.loglink.dubbo;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.aykj.loglink.Constants;
import com.aykj.loglink.LogLinker;
import org.springframework.util.StringUtils;

/**
 * Create On 2018-12-04 16:13
 *
 * @author mo
 */
@Activate
public class DubboProviderLogRequestIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String requestId = invocation.getAttachment(Constants.getRequestIdKey());
        if (!StringUtils.isEmpty(requestId)) {
            LogLinker.setRequestId(requestId);
        } else {
            LogLinker.clearRequestId();
        }

        return invoker.invoke(invocation);
    }
}
