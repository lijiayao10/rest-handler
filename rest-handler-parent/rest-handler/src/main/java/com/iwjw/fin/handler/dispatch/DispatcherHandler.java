/*
 * @author caojiayao 2017年4月18日 下午5:02:15
 */
package com.iwjw.fin.handler.dispatch;

import static com.iwjw.fin.handler.common.enums.ErrorCodeEnum.SERVICE_NOT_FOUND;
import static com.iwjw.fin.handler.common.enums.ResultCodeEnum.F;
import static com.iwjw.fin.handler.common.utils.HandlerUtil.buildResponse;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.iwjw.fin.handler.annotation.EnableHandler;
import com.iwjw.fin.handler.common.domain.Response;
import com.iwjw.fin.handler.service.IHandler;

/**
 * <p>
 * 处理服务调度中心
 * <p>
 * 
 * @author caojiayao
 * @version $Id: DispatcherHandler.java, v 0.1 2017年4月18日 下午5:02:15 caojiayao
 *          Exp $
 */
@RestController
@RequestMapping("${handler.dispatcher.path:/handler}")
@ConditionalOnClass(EnableHandler.class)
public class DispatcherHandler extends AbstractDispatcherHandler {
	
	/**
	 * 服务分发接口
	 * @param service
	 * @param methodName
	 * @param params
	 * @return
	 */
	@RequestMapping("/{service}")
	public Response dispatch(@PathVariable String service, String methodName, String[] params) {
 		final IHandler iHandler = super.getIHandler(service);

		if (iHandler == null)
			return buildResponse(SERVICE_NOT_FOUND.name(), SERVICE_NOT_FOUND.getErrorMessage(), F);

		return iHandler.handler(methodName, params);
	}
	
	/**
	 * 服务打印接口
	 * @return
	 */
	@RequestMapping("/infos")
	public Map<String, Set<String>> infos() {

		Map<String, Set<String>> infos = Maps.newHashMap();
		for (Entry<String, IHandler> entry : super.getIHandlers()) {
			infos.put(entry.getKey(), entry.getValue().info());
		}

		return infos;
	}
}
