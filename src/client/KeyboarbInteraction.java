package client;
import java.sql.Timestamp;

public class KeyboarbInteraction {
	
	private String handGesture;
	private Timestamp timestamp;
	private String dataFormat;
	
	
	public KeyboarbInteraction(String handGesture, Timestamp timestamp)
	{
		this.handGesture = handGesture;
		this.timestamp = timestamp;
		setDataFormat(handGesture, timestamp);
	}

	
	public String getHandGesture() {
		return handGesture;
	}
	
	public String getDataFormat() {
		return dataFormat;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	
	
	public void setHandGesture(String handGesture) {
		this.handGesture = handGesture;
	}

	public void setDataFormat(String gesture, Timestamp time) {
		//dataFormat = gesture;
		dataFormat = "<?xml version='1.0' encoding='UTF-8'?>";
		dataFormat += "<aui:HandGesture capturedTimeStamp=\""+ time + "\" Gesture=\"" + gesture + "\" ";
		dataFormat += "xsi:schemaLocation=\"urn:mpeg:mpegu:schema:aui:2012 MPEG-U-AUI.xsd\" ";
		dataFormat += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ";
		dataFormat += "xmlns:aui=\"urn:mpeg:mpegu:schema:aui:2012\">";
		dataFormat += "</aui:HandGesture>";
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}