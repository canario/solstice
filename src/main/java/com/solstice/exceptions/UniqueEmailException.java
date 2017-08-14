package com.solstice.exceptions;

public class UniqueEmailException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        // TODO Auto-generated method stub
        return super.getCause();
    }
}
