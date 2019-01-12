package com.my.test.dao.interceptor;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import com.my.test.task.DataContainer;

public class IpIntercepotor implements Intercepotor {

	@Override
	public boolean test(DataContainer o) throws Exception{
		if(!StringUtils.isEmpty(o.getIp()) && !ipMatch(o.getIp())){
			return false;
		}
		return true;
	}

	private boolean ipMatch(String ip) throws Exception {
		String localIp = getLocalHostLANAddress().getHostAddress();
		StringTokenizer tokenizer = new StringTokenizer(ip, "\\|");
		while (tokenizer.hasMoreTokens()) {
			if (localIp.equals(tokenizer.nextToken())) {
				return true;
			}
		}
		return false;
	}
	
	public static InetAddress getLocalHostLANAddress() throws Exception {
		try {
			InetAddress candidateAddress = null;
			for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
				NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
				for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
					InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
					if (!inetAddr.isLoopbackAddress()) {
						if (inetAddr.isSiteLocalAddress()) {
							return inetAddr;
						} else if (candidateAddress == null) {
							candidateAddress = inetAddr;
						}
					}
				}
			}
			if (candidateAddress != null) {
				return candidateAddress;
			}
			InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
			return jdkSuppliedAddress;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
