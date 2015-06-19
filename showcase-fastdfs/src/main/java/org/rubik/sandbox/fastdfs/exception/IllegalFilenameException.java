/**
 * Copyright ©2008-2012 Lafaso.com All Rights Reserved. 
 */
package org.rubik.sandbox.fastdfs.exception;

/**
 * <p>非法的文件名异常，文件全名必须有扩展名。</p>
 * 
 * @author xiajinxin
 *
 */
public class IllegalFilenameException extends RuntimeException {

	private static final long serialVersionUID = 863001747964476968L;

	public IllegalFilenameException(String msg) {
		super("非法的文件名! " + msg);
	}

    public IllegalFilenameException(String msg, Throwable cause) {
        super("非法的文件名! " + msg, cause);
    }
}
