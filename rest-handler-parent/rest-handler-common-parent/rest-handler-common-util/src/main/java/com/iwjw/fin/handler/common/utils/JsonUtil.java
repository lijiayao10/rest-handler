package com.iwjw.fin.handler.common.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netfinworks.common.util.money.Money;

/**
 * <p>利用jackson的json工具类</p>
 * @author paganini
 * @version $Id: JsonUtil.java, v 0.1 2016年4月18日 上午11:35:44 qiuzhongtian Exp $
 */
public class JsonUtil {

    private static final Logger LOGGER       = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper = JsonObjectMapperFactory.getInstance();

    /**
     * 将对象转换为JSON字符串
     * @param obj
     * @return
     */
    public static String toJson(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("json序列化异常", e);
            throw new IllegalArgumentException("转换为JSON字符串时异常", e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parse(final String json, final Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            LOGGER.error("json反序列化异常", e);
            throw new IllegalArgumentException("由JSON字符串时转换为对象时异常", e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     * @param json
     * @param typeReference
     * @return
     */
    public static <T> T parse(final String json, final TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (Exception e) {
            LOGGER.error("json反序列化异常", e);
            throw new IllegalArgumentException("由JSON字符串时转换为对象时异常", e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     * @param json
     * @param javaType
     * @return
     */
    public static <T> T parse(final String json, final JavaType javaType) {
        try {
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.error("json反序列化异常", e);
            throw new IllegalArgumentException("由JSON字符串时转换为对象时异常", e);
        }
    }

    /**
     * 将JSON字符串转换为对象
     * 
     * @param json
     * @param parametrized
     * @param parameterClazzes
     * @return
     */
    public static <T> T parse(final String json, final Class<?> parametrized,
                              final Class<?>... parameterClazzes) {
        try {
            return parse(json, objectMapper.getTypeFactory().constructParametrizedType(parametrized,
                parametrized, parameterClazzes));
        } catch (Exception e) {
            LOGGER.error("json反序列化异常", e);
            throw new IllegalArgumentException("由JSON字符串时转换为对象时异常", e);
        }
    }

    /**
     * <p>json对象映射工厂</p>
     * @author paganini
     * @version $Id: JsonObjectMapperFactory.java, v 0.1 2016年4月18日 上午11:36:02 qiuzhongtian Exp $
     */
    public static class JsonObjectMapperFactory {
        private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        private static ObjectMapper om          = new ObjectMapper();

        static {
            om.addMixIn(Money.class, IgnoreMoneySetAmountBigDecimal.class);
            om.addMixIn(com.netfinworks.common.util.money.Money.class,
                IgnoreMoneySetAmountBigDecimal.class);
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            om.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
        }

        /** 忽略金额相关 */
        @JsonIgnoreProperties("centFactor")
        interface IgnoreMoneySetAmountBigDecimal {
            @JsonIgnore
            void setAmount(BigDecimal amount);
        }

        public static ObjectMapper getInstance() {
            return om;
        }
    }

}
