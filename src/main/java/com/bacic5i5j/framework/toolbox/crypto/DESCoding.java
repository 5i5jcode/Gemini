package com.bacic5i5j.framework.toolbox.crypto;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加解密的工具类
 */
public class DESCoding {
	private static Logger logger = Logger.getLogger("DESCoding");

	private String Algorithm = "DESede"; //定义 加密算法，默认为DESede
	private byte[] keyBytes = null;

	/**
	 * 用默认的算法DESede，传入密钥，生成工具类
	 *
	 * @param keyBytes 密钥，必须是24字节
	 * @throws Exception
	 */
	public DESCoding(byte[] keyBytes) throws Exception {
		if (keyBytes.length != 24) {
			throw new Exception("the keys's length must be 24!");
		}
		this.keyBytes = keyBytes;
	}

	/**
	 * 用指定的算法，传入加密的key，生成工具类
	 *
	 * @param keyBytes  密钥，必须是24字节
	 * @param Algorithm 算法
	 * @throws Exception
	 */
	public DESCoding(byte[] keyBytes, String Algorithm) throws Exception {
		if (keyBytes.length != 24) {
			throw new Exception("the keys's length must be 24!");
		}
		this.keyBytes = keyBytes;
		this.Algorithm = Algorithm;
	}

	/**
	 * 对指定的字节数组进行加密
	 *
	 * @param src 需要进行加密的字节数组
	 * @return byte[]     加密后的字节数组，若加密失败，则返回null
	 */
	public byte[] encode(byte[] src) {
		try {
			//  生成密钥
			SecretKey deskey = new SecretKeySpec(this.keyBytes, Algorithm);

			//  加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			logger.warn("DESCoding.encode error in java.security.NoSuchAlgorithmException:" + e1);
			return null;
		} catch (javax.crypto.NoSuchPaddingException e2) {
			logger.warn("DESCoding.encode error in javax.crypto.NoSuchPaddingException:" + e2);
			return null;
		} catch (Exception e3) {
			logger.warn("DESCoding.encode error in Exception:" + e3);
			return null;
		}
	}

	/**
	 * 加密并转换成hex Str
	 *
	 * @param src
	 * @return String
	 */
	public String encode2HexStr(byte[] src) {
		return HexUtil.bytes2HexStr(encode(src));
	}

	/**
	 * 加密并转换成BASE64编码的字符串
	 *
	 * @param src
	 * @return String
	 */
	public String encode2Base64(byte[] src) {
		return BASE64Coding.encode(encode(src));
	}

	/**
	 * 解密
	 *
	 * @param src 用3DES加密后的字节数组
	 * @return byte[]     解密后的字节数组
	 */
	public byte[] decode(byte[] src) {
		try {
			//  生成密钥
			SecretKey deskey = new SecretKeySpec(this.keyBytes, Algorithm);

			//  解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			logger.warn("DESCoding.decode error in java.security.NoSuchAlgorithmException:" + e1);
			return null;
		} catch (javax.crypto.NoSuchPaddingException e2) {
			logger.warn("DESCoding.decode error in javax.crypto.NoSuchPaddingException:" + e2);
			return null;
		} catch (Exception e3) {
			logger.warn("DESCoding.decode error in Exception:" + e3);
			return null;
		}
	}
}
