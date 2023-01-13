package kr.or.nextit.exception;

public class BizNotEffectedException extends BizException {

	public BizNotEffectedException() {
	}

	public BizNotEffectedException(String message) {
		super(message);
	}

	public BizNotEffectedException(Throwable cause) {
		super(cause);
	}

	public BizNotEffectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizNotEffectedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
