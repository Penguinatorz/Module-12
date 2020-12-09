/**
 * This class is a passing class used in Filechooser due to eventhandler limits
 * This assists passing the chosen file string
 * @author Jan
 *
 */
public class sendPara {
	
	
	private static String selectedFile;

	public sendPara()
	{
	}
	
	/**
	 * This method gets the selected textfile to be passed
	 * @return passes the textfile that has been turned into a string
	 */
	
	public String getSelectedFile() {
		return selectedFile;
	}
/**
 * This method is used in Filechooser.class to pass the textfile as a string parameter
 * @param selectedFile this is the textfile allowing for passing of the string
 */
	public void setSelectedFile(String selectedFile) {
		sendPara.selectedFile = selectedFile;
	}
	
	

}
