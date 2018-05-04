package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.remote.rmi.RMIServer;

public class TransformerFichier {

	public static byte[] fileToByte(String path) throws IOException {
		return Files.readAllBytes(Paths.get(path));
	}
	
	public static void byteToFile(byte[] bytes, String path) {
		try{
            Files.write(Paths.get(path),bytes);
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
}
