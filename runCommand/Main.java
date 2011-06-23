package runCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String copy_from_dir = "";
		String copy_to_dir = "";
		try {
			copy_from_dir = args[0];
			copy_to_dir = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Ingen mappe å kopiere fra og til definert.");
			System.exit(0);
		}
		
		int sleeping = 6000;
		while(true)
		{
			//Runtime rt = Runtime.getRuntime();
			try {
				File srcdir = new File(copy_from_dir);
				File dstdir = new File(copy_to_dir);
				
				if(!srcdir.isDirectory() || !dstdir.isDirectory())
				{
					System.out.println("Finner ikke mappene du vil kopiere fra / til");
					System.exit(0);
				}
				copyDirectory(srcdir, dstdir);
				//Process proc = rt.exec("cmd /c c:\\copy-videos.bat");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
			try {
				System.out.println("Sover " + (int)(sleeping/1000) + " sek");
				Thread.sleep(sleeping);
			} catch (InterruptedException e ) {
				e.printStackTrace();
			}
		}
		//System.out.println("Jeg gidder ikke mer!");
	}
	
	// Copies all files under srcDir to dstDir.
	public static void copyDirectory(File srcDir, File dstDir) throws IOException {
		if (srcDir.isDirectory()) {
			if (dstDir.exists()) 
			{
				
				String[] children = srcDir.list();
				for (int i=0; i<children.length; i++) {
					File child = new File(dstDir, children[i]);
					if(!child.exists())
						copyDirectory(new File(srcDir, children[i]), child);
				}
				
			}
		} else {
			// This method is implemented in e1071 Copying a File
			copyFile(srcDir, dstDir);
		}
	}
	
	// Copies src file to dst file.
	// If the dst file does not exist, it is created
	public static void copyFile(File src, File dst) throws IOException {
		System.out.println("Kopierer fil " + src.getName());
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		
		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
}
