package com.file.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.owasp.esapi.ESAPI;

public class FileApp {

	private static final String SRC = "F:\\testFile.txt";
	private static final String DST = "F:\\DestFile.txt";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		returnFile(SRC, new FileOutputStream(DST));

	}
	
	    public static void Test() {
	 
	        String content = "Hello Java Code Geeks";
	 
	        byte[] bytes = content.getBytes();
	 
	        try (OutputStream out = new FileOutputStream(SRC)) {
	 
	            // write a byte sequence
	            out.write(bytes);
	 
	            // write a single byte
	           // out.write(bytes[0]);
	 
	            // write sub sequence of the byte array
	           // out.write(bytes,4,10);
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	public static boolean returnFile(String filename, OutputStream out) throws FileNotFoundException, IOException {
		//A FileInputStream is for bytes
		FileInputStream fis = null;
		boolean status = false;
		try {
			fis = new FileInputStream(filename);
			byte[] buf = new byte[4 * 1024]; // 4K buffer
			int bytesRead;
			while ((bytesRead = fis.read(buf)) != -1) {
				//out.write(buf, 0, bytesRead);
				//114124_CGI_stored_XSS_start
				out.write(ESAPI.validator().getValidFileContent("AttachmentDisplayServlet",buf,4 * 1024,true), 0, bytesRead);
				//114124_CGI_stored_XSS_end
			}
			status = true;
		} catch (Exception e) {
			status = false;
		} finally {
			if (fis != null)
				fis.close();
		}
		return status;
	}

}
