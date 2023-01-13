package kr.or.nextit.exception;

public class BizMailAuthException extends BizException {

	public BizMailAuthException() {
	}

	public BizMailAuthException(String message) {
		super(message);
	}

	public BizMailAuthException(Throwable cause) {
		super(cause);
	}

	public BizMailAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizMailAuthException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
