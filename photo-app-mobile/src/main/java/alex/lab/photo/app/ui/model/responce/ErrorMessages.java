package alex.lab.photo.app.ui.model.responce;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field.Please check the documentation"),
	RECORD_ALREADY_EXIST("Record already exist"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Recod with provided id is not found"),
	AUTHENTIFICATION_FAILED("Authentification failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address is not verified");
	
	private String errorMessage;
	
	

	/**
	 * @param errorMessage
	 */
	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
