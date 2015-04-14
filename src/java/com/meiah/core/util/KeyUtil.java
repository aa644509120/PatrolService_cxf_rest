/** * A级 */
package com.meiah.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class KeyUtil {
	private static Logger logger = Logger.getLogger(KeyUtil.class);
	public static Key privateKey;
	public static Key publicKey;
	private static String publicKeyFile = KeyUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "")+"publicKey.key";
//	private static String privateKeyFile = KeyUtil.class.getResource("/").getPath().toString().replaceAll("file:/", "")+"privateKey.key";
	
	static{
		try {
			publicKey = getKey(publicKeyFile);
		} catch (Exception e) {
			logger.error("加载密钥出错",e);
		}
	}
	
	  /**
     * 根据key的路径文件获得持久化成文件的key
     * <P>
     * 例子: RsaEncrypt.getKey("c:/systemkey/private.key");
     *
     * @param keyPath
     * @return
     */
    private static Key getKey(String keyPath) throws Exception {
        FileInputStream fis = new FileInputStream(keyPath);
        byte[] b = new byte[16];
        fis.read(b);
        SecretKeySpec dks = new SecretKeySpec(b,"AES");
        fis.close();
        return dks;
    }
	
//	/**
//	 * 获取Key
//	 * @param keyDir
//	 * @return
//	 */
//	public static Key getKey(String keyDir) {
//		Key key = null;
//		try {
//			FileInputStream fin = new FileInputStream(keyDir);
//			ObjectInputStream ois = new ObjectInputStream(fin);
//			key = (Key) ois.readObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
//		return key;
//	}

	/**
	 * 生成密钥文件
	 * @param keyfile
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	private static void generateRSAKeyPair(String keyfile)
			throws NoSuchAlgorithmException, IOException, FileNotFoundException {
		SecureRandom sr = new SecureRandom();
		KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
		kg.initialize(512, sr);
		// 产生新密钥对
		KeyPair kp = kg.generateKeyPair();
		// 获得私匙
		KeyUtil.privateKey = kp.getPrivate();
		// 获得公钥
		KeyUtil.publicKey = kp.getPublic();
	
		File f = new File(keyfile);
		if(!f.exists()) {
			f.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(keyfile + "publicKey.key");
		ObjectOutputStream dos = new ObjectOutputStream(fos);
		dos.writeObject(KeyUtil.publicKey);
		fos = new FileOutputStream(keyfile + "privateKey.key");
		dos = new ObjectOutputStream(fos);
		dos.writeObject(KeyUtil.privateKey);
		dos.close();
		fos.close();
	}
	
	/**
	 * 生成密钥文件
	 * @param keyfile
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private static void generateAESKeyPair(String keyfile)
			throws Exception {
		SecureRandom sr = new SecureRandom("meiyapico@300188".getBytes());
		KeyGenerator kg = KeyGenerator.getInstance("AES");
		kg.init(128, sr);
		SecretKey key = kg.generateKey();
	
		File f = new File(keyfile);
		if(!f.exists()) {
			f.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(keyfile + "publicKey.key");
		System.out.println(Base64Utils.encode(key.getEncoded()));
		fos.write(key.getEncoded());
		fos.close();
	}
	public static void main(String[] args) {
		try {
			System.out.println("111");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
