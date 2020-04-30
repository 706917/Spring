package alex.lab.photo.app.ui.model.responce;

import java.util.Date;


public class ErrorMessageModel {

	private Date timeStamp;
	private String message;

	
	public ErrorMessageModel() {}

	/**
	 * @param timeStamp
	 * @param message
	 */
	public ErrorMessageModel(Date timeStamp, String message) {
		this.timeStamp = timeStamp;
		this.message = message;
	}

	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
