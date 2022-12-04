package kr.or.nextit.exception;

public class BizPasswordNotMatchedException extends BizException {

	public BizPasswordNotMatchedException() {
	}

	public BizPasswordNotMatchedException(String message) {
		super(message);
	}

	public BizPasswordNotMatchedException(Throwable cause) {
		super(cause);
	}

	public BizPasswordNotMatchedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizPasswordNotMatchedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
