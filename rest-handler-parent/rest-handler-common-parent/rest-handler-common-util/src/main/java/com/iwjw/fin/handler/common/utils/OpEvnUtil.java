/*
 * @author caojiayao 2017年4月18日 下午1:00:25
 */
package com.iwjw.fin.handler.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.domain.OperationEnvironment;

/**
 * <p>操作环境信息 工具类<p>
 * @author caojiayao 
 * @version $Id: OpEvnUtil.java, v 0.1 2017年4月18日 下午1:00:25 caojiayao Exp $
 */
public class OpEvnUtil {

    /**
     * 创建操作环境信息
     * @param clientIp
     * @param clientMac
     * @param clientId
     * @param serverIp
     * @param serverName
     * @param extension
     * @return
     */
    public static OperationEnvironment createOpEvn(String clientIp, String clientMac,
                                                   String clientId, String serverIp,
                                                   String serverName, Extension extension) {
        OperationEnvironment opEvn = createOpEvn();
        opEvn.setClientId(clientId);
        opEvn.setClientIp(clientIp);
        opEvn.setClientMac(clientMac);
        opEvn.setExtension(extension);
        opEvn.setServerIp(serverIp);
        opEvn.setServerName(serverName);

        return opEvn;
    }

    /**
     * 创建空操作环境信息
     * @return
     */
    public static OperationEnvironment createOpEvn() {
        return new OperationEnvironment();
    }

    /**
     * 创建客户端操作环境信息
     * @param clientId
     * @param serverIp
     * @param serverName
     * @param extension
     * @return
     */
    public static OperationEnvironment createClientOpEvn(String clientId, String serverIp,
                                                         String serverName, Extension extension) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            return createOpEvn(inetAddress.getHostAddress(), getMACAddress(inetAddress), clientId,
                serverIp, serverName, extension);
        } catch (UnknownHostException | SocketException e) {
            return createOpEvn(null, null, clientId, serverIp, serverName, extension);
        }

    }

    /**
     * 创建服务端操作环境信息
     * @param clientId
     * @param serverIp
     * @param serverName
     * @param extension
     * @return
     */
    public static OperationEnvironment createServerOpEvn(String clientIp, String clientMac,
                                                         String clientId, String serverName,
                                                         Extension extension) {
        try {
            return createOpEvn(clientIp, clientMac, clientId,
                InetAddress.getLocalHost().getHostAddress(), serverName, extension);
        } catch (UnknownHostException e) {
            return createOpEvn(clientIp, clientMac, clientId, null, serverName, extension);
        }

    }

    /**
     * 获取MAC地址的方法
     * @param inetAddress
     * @return
     * @throws SocketException 
     * @throws Exception
     */
    public static String getMACAddress(InetAddress inetAddress) throws SocketException {
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();

        //下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }

        //把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();
    }

    /**
     * 序列化操作环境信息
     * @param opEvn
     * @return
     */
    public static String opEvnToJson(OperationEnvironment opEvn) {
        return JsonUtil.toJson(opEvn);
    }

    /**
     * 反序列化操作环境信息
     * @param opEvn
     * @return
     */
    public static OperationEnvironment JsonToOpEvn(String json) {
        return JsonUtil.parse(json, OperationEnvironment.class);
    }

}
