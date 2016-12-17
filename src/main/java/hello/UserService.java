package hello;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;;

public class UserService {
	
	
	boolean isAuthorized(User user) {
		// TODO Auto-generated method stub
		if(user.getName().equals("usuario1")) return true;
		return false;
	}

	public String convertImage(File imagen) throws IOException {
		// TODO Auto-generated method stub
		byte[] bytes = loadFile(imagen);
		byte[] encoded = Base64.encodeBase64(bytes);
		String encodedString = new String(encoded);;
		return encodedString ;
	}
    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

	public InputStream getImage(User user) {
		
		byte[] currentImage = user.getImage().getBytes();
		byte[] valueDecoded = Base64.decodeBase64(currentImage);
		InputStream targetStream = new ByteArrayInputStream(valueDecoded);

		return targetStream;
	}	


}
