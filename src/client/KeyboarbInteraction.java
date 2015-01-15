package client;

public class KeyboarbInteraction {
	
	private char key;
	private String handGesture;
	private String dataFormat;
	
	
	public KeyboarbInteraction(char key)
	{
		this.key = key;
	}
	
	public char getKey() {
		return key;
	}
	
	public String getHandGesture() {
		return handGesture;
	}
	
	public String getDataFormat() {
		return dataFormat;
	}
	
	
	public void setKey(char key) {
		this.key = key;
	}
	
	public void setHandGesture(String handGesture) {
		this.handGesture = handGesture;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
}