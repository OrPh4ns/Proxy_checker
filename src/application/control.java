package application;



import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class control {
	
	String host;
	int ip;

	
	public void filter(String s) {

		
		this.host = s.substring(0,s.indexOf(":"));
		this.ip = Integer.parseInt(s.substring(s.indexOf(":")+1));
		
	
	}
	
    @FXML
    private TextField thread;
    
    @FXML
    private TextArea list;

    @FXML
    private TextArea yes;

    @FXML
    private TextArea no;

    @FXML
    void OnCheck() throws FileNotFoundException {

    	
   
    	 String[] lines = list.getText().split("\\n"); 
    	 System.out.println(lines[0]);
    	 for(int i = 0 ; i< lines.length; i++) {
    		 filter(lines[i]);
    		 if(connect()==true) {
    			 yes.appendText(lines[i]+"\n");
    		 }else {
    			 no.appendText(lines[i]+"\n");
    		 }
    	 }
    }
    
public boolean connect() {
	
   	try {
   		
   		int th = Integer.parseInt(thread.getText());
   		Thread.sleep(th);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.host, this.ip));
			URL url = new URL("http://www.google.com");
			HttpURLConnection uc = (HttpURLConnection)url.openConnection(proxy);
			uc.connect();
			if(uc.usingProxy()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

    }	
    


}
