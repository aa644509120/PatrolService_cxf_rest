/** * A级 */
package com.meiah.core.util;

import java.util.zip.CRC32;

import org.apache.commons.lang.StringUtils;

public class Crc32 {
	/**
	 *  字符串转crc32
	 * @function 
	 * @param str 需要转crc32的url
	 * @return
	 */
	public static long stringToCrc32(String str){
		if (StringUtils.isNotEmpty(str)) {
			byte[] bytes = str.getBytes();
			CRC32 crc = new CRC32();
			crc.update(bytes);
			return crc.getValue();
		}else{
			return 0L;
		}
		
	}
}
