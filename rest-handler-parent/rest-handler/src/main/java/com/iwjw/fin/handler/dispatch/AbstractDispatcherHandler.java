/*
 * @author caojiayao 2017年4月18日 下午5:02:15
 */
package com.iwjw.fin.handler.dispatch;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.google.common.collect.Maps;
import com.google.common.collect.Maps.EntryTransformer;
import com.iwjw.fin.handler.service.IHandler;
import com.iwjw.fin.handler.service.annotation.HandlerService;
import com.iwjw.fin.handler.service.impl.FactoryHandler;

/**
 * <p>
 * 处理服务调度中心
 * <p>
 * 
 * @author caojiayao
 * @version $Id: DispatcherHandler.java, v 0.1 2017年4月18日 下午5:02:15 caojiayao
 *          Exp $
 */
public class AbstractDispatcherHandler implements ApplicationContextAware {

	/** handler 集合 **/
	private Map<String, IHandler> handlerServices = Maps.newHashMap();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		final Map<String, Object> HandlerServices = applicationContext.getBeansWithAnnotation(HandlerService.class);

		if (MapUtils.isNotEmpty(HandlerServices))
			this.handlerServices = Maps.transformEntries(HandlerServices,
					new EntryTransformer<String, Object, IHandler>() {

						@Override
						public IHandler transformEntry(String key, Object value) {
							return new FactoryHandler(value);
						}
					});
	}

	/**
	 * 获得Handler服务
	 * 
	 * @param service
	 * @return
	 */
	protected IHandler getIHandler(final String service) {
		return handlerServices.get(service);
	}

	/**
	 * 获得Handler服务
	 * 
	 * @param service
	 * @return
	 */
	protected Set<Entry<String, IHandler>> getIHandlers() {
		final Set<Entry<String, IHandler>> entries = handlerServices.entrySet();
		return entries;
	}

}
