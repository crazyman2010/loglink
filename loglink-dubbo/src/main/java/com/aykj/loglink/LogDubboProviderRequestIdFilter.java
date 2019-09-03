package com.aykj.loglink;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.springframework.util.StringUtils;

/**
 * Create On 2018-12-04 16:13
 *
 * @author heyangya
 */
@Activate(group = {Constants.PROVIDER})
public class LogDubboProviderRequestIdFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String requestId = invocation.getAttachment(com.aykj.loglink.Constants.LOG_REQUEST_ID);
        if (!StringUtils.isEmpty(requestId)) {
            LogLinker.setRequestId(requestId);
        } else {
            LogLinker.clearRequestId();
        }

        return invoker.invoke(invocation);
    }
}
