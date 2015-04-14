/** * A级 */
/** 
  *CertificateUtils.java 2010-3-17 Administrator
  */
package com.meiah.core.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
/**
 * @function   
 * @author Administrator
 */
public class CertificateUtils {
	
	 
	  
		/**
		 * 
		 * @function 验证吊销列表 
		 * @param cert 传入客户端传过来的证书
		 * @param crlPath 吊销列表文件
		 * @return  true 该证书被吊销  false 该证书未被吊销
		 * @throws IOException 
		 * @throws CRLException 
		 * @throws CertificateException  
		 */
	    public static boolean validateCrl(X509Certificate cert,String crlPath) throws CertificateException, CRLException, IOException   {   
	      
	         //创建CRL对象    
	        X509CRL crl = loadX509CRL(crlPath); 
	        // 验证证书是否被吊销    
	         return crl.isRevoked(cert);
	         
	    }   
	    
	       
	    /**  
	     * 加载CRL证书吊销列表文件  
	     * @param crlFilePath  吊销列表本地路径
	     * @return  X509CRL 对象
	     * @throws CertificateException 
	     * @throws CRLException 
	     * @throws IOException  
	     */  
	    public static X509CRL loadX509CRL(String crlFilePath) throws CertificateException, CRLException, IOException  {   
	        FileInputStream in = new FileInputStream(crlFilePath);   
	        CertificateFactory cf = CertificateFactory.getInstance("X.509");   
	        X509CRL crl = (X509CRL)cf.generateCRL(in);   
	        in.close();   
	        return crl;   
	    }   
	       
	    /**  
	     * 加载证书文件  
	     * @param certFilePath  加载本地证书
	     * @return  X509Certificate 
	     * @throws FileNotFoundException 
	     * @throws CertificateException 
	     */  
	    public static X509Certificate getCertificate(String certFilePath) throws FileNotFoundException, CertificateException {   
	        FileInputStream in = new FileInputStream(certFilePath);   
	        CertificateFactory cf = CertificateFactory.getInstance("X.509");   
	        X509Certificate cert = (X509Certificate)cf.generateCertificate(in);   
	        return cert;   
	    }   
	       
	    /**  
	     * 读取证书序列号  
	     * @param cert  
	     * @return  
	     */  
	    public static String getSerialNumber(X509Certificate cert) {   
	        if(null == cert) return null;   
	        byte [] serial = cert.getSerialNumber().toByteArray();             
	        if(serial.length>0){   
	            String serialNumberString = new String();   
	            for(int i=0;i<serial.length;i++){   
	                String s =Integer.toHexString(Byte.valueOf(serial[i]).intValue());   
	                if(s.length()==8) s = s.substring(6);   
	                else if(1==s.length()) s="0"+s;   
	                serialNumberString += s;   
	            }   
	            return serialNumberString;   
	        }   
	        return null;   
	    }    
	    
	    
//	    public static void main(String[] args) throws Exception {
//			
//	    	X509Certificate cer = getCertificate("d:\\ca\\certnew.cer");
//	    	System.out.println(getSerialNumber(cer));
//	    } 
}
