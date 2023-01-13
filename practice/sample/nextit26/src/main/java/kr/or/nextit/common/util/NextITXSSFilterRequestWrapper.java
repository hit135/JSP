package kr.or.nextit.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NextITXSSFilterRequestWrapper extends HttpServletRequestWrapper{

	// HttpServletRequestWrapper는 값을 취득해주는 클래스
	
	// 꼭 적어야함
	public NextITXSSFilterRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String parameter) {
		
		String value = super.getParameter(parameter);
		
		if(value == null) {
			return null;
		}
		
		StringBuffer strBuffer = new StringBuffer();
		for(int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
			case '<':
				strBuffer.append("&lt;");
				break;
			case '>':
				strBuffer.append("&gt;");
				break;
			case '&':
				strBuffer.append("&amp;");
				break;
			case '"':
				strBuffer.append("&quot;");
				break;
			case '\'':
				strBuffer.append("&apos;");
				break;
			default:
				strBuffer.append(c);
				break;
			}
		}
		value = strBuffer.toString();
		return value;
	}
	
	
	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		
		if(values == null) {
			return null;
		}
		
		for(int i = 0; i < values.length; i++) {
			if(values[i] != null) {
				
				StringBuffer strBuffer = new StringBuffer();
				for(int j = 0; j < values[i].length(); j++) {
					char c = values[i].charAt(j);
					switch (c) {
					case '<':
						strBuffer.append("&lt;");
						break;
					case '>':
						strBuffer.append("&gt;");
						break;
					// case '&':
						// strBuffer.append("&amp;");
						// break;
					case '"':
						strBuffer.append("&quot;");
						break;
					case '\'':
						strBuffer.append("&apos;");
						break;
					default:
						strBuffer.append(c);
						break;
					}
				}
				values[i] = strBuffer.toString();
			}else {
				values[i] = null;
			}
		}
		return values;
	}
	
	
	
	
	
	
	
	
}
