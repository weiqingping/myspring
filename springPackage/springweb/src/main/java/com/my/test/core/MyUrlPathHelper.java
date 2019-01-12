package com.my.test.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UrlPathHelper;


public class MyUrlPathHelper extends UrlPathHelper {
	private  static final String BODY_JOSN_FLAG=".*(.json|.JSON)$";
	private static final String BIZ_COCd_REGEX="\"bizCode\":\"(.*?)\"";
	private static final String METHOD_COCd_REGEX="\"methodCode\":\"(.*?)\"";
	
	@Override
	public String getLookupPathForRequest(HttpServletRequest request) {
		if(isJsonBodyRequest(request.getRequestURI())){
			try {
				 String reauestJosn=getRequestMessage(request);
				  String methodCode=this.matchString(reauestJosn, METHOD_COCd_REGEX);
				  if(StringUtils.isEmpty(methodCode)){
					  throw new Exception("methodCode can not null");
				  }
				  String bizCode=this.matchString(reauestJosn, BIZ_COCd_REGEX);
				  if(StringUtils.isEmpty(bizCode)){
					  throw new Exception("bizCode can not null");
				  }
				
				  String realPath="/"+bizCode+"/"+methodCode;
				  return realPath;
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			} 
			}else{
				return super.getLookupPathForRequest(request);

			}
		
	}
	
	
	private boolean isJsonBodyRequest(String path){
		Pattern pattern = Pattern.compile(BODY_JOSN_FLAG);
        Matcher matcher = pattern.matcher(path);
        return matcher.matches();
	}
	
	private String matchString(String String,String regex){
        Matcher matcher = Pattern.compile(regex).matcher(String);
        if (matcher.find()) {
            String value = matcher.group(1);
           return value;
        }
        return null;
	}
	
	private String getRequestMessage(HttpServletRequest request) throws IOException{
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        InputStream in = request.getInputStream();

	        byte[] buf = new byte[1024];
	        for (;;) {
	            int len = in.read(buf);
	            if (len == -1) {
	                break;
	            }

	            if (len > 0) {
	                baos.write(buf, 0, len);
	            }
	        }

	        byte[] bytes = baos.toByteArray();
	        
	        return new String(bytes,"UTF-8");
	}

}
