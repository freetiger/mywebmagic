package com.yuhe.mywebmagic.zhenai;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;

public class RegistForm implements Serializable {
	private static final long serialVersionUID = 7639592168602099484L;
	//String data = "MC_Email=sese@163.com&MC_RegCID=322&MC_RegCIDC=321&M_LoginPassword=1161hyx&M_LoginPassword_Confirm=1161hyx&M_UserName=sese&Provision=true&ruzType=我是买家&ToUrl=&WebCode=1111&___R_hidd=868";
	private String MC_Email;
	private String MC_RegCID;
	private String MC_RegCIDC;
	private String M_LoginPassword;
	private String M_LoginPassword_Confirm;
	private String M_UserName;
	private String Provision;
	private String ruzType;
	private String ToUrl;
	private String WebCode;
	private String ___R_hidd;
	
	public String getMC_Email() {
		return MC_Email;
	}
	public void setMC_Email(String mC_Email) {
		MC_Email = mC_Email;
	}
	public String getMC_RegCID() {
		return MC_RegCID;
	}
	public void setMC_RegCID(String mC_RegCID) {
		MC_RegCID = mC_RegCID;
	}
	public String getMC_RegCIDC() {
		return MC_RegCIDC;
	}
	public void setMC_RegCIDC(String mC_RegCIDC) {
		MC_RegCIDC = mC_RegCIDC;
	}
	public String getM_LoginPassword() {
		return M_LoginPassword;
	}
	public void setM_LoginPassword(String m_LoginPassword) {
		M_LoginPassword = m_LoginPassword;
	}
	public String getM_LoginPassword_Confirm() {
		return M_LoginPassword_Confirm;
	}
	public void setM_LoginPassword_Confirm(String m_LoginPassword_Confirm) {
		M_LoginPassword_Confirm = m_LoginPassword_Confirm;
	}
	public String getM_UserName() {
		return M_UserName;
	}
	public void setM_UserName(String m_UserName) {
		M_UserName = m_UserName;
	}
	public String getProvision() {
		return Provision;
	}
	public void setProvision(String provision) {
		Provision = provision;
	}
	public String getRuzType() {
		return ruzType;
	}
	public void setRuzType(String ruzType) {
		this.ruzType = ruzType;
	}
	public String getToUrl() {
		return ToUrl;
	}
	public void setToUrl(String toUrl) {
		ToUrl = toUrl;
	}
	public String getWebCode() {
		return WebCode;
	}
	public void setWebCode(String webCode) {
		WebCode = webCode;
	}
	public String get___R_hidd() {
		return ___R_hidd;
	}
	public void set___R_hidd(String ___R_hidd) {
		this.___R_hidd = ___R_hidd;
	}
	
	/**
	 * 获得支付请求参数串
	 */
	public String getRequestData(){
		StringBuffer params = new StringBuffer();
		Class<? extends RegistForm> dealClass = this.getClass();
		Field[] fields = dealClass.getDeclaredFields();
		for(Field field:fields) {
			if(field.getModifiers()==Modifier.PRIVATE && field.getType()==java.lang.String.class) {
				String value;
				try {
					value = (String)field.get(this);
					if(value!=null) {	//StringUtils.isNotBlank(value)
						params.append("&").append(field.getName()).append("=").append(value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return params.substring(1);
	}	
}
