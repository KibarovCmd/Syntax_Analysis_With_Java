import java.io.IOException;
import java.util.Scanner;

/**
*
* @author İskender Musaoğlu / iskender.musaoglu@ogr.sakarya.edu.tr
* @since 01.04.2024 - 05.04.2024
* <p>
* Repoyu indiren ve isimlerini alan sınıf
* </p>
*/

public class Clone {
	private String repoLink = "";
	private Scanner scannerRepo;
	
	public void clone_Repo_From_Git() {
		Scanner scannerRepo = new Scanner(System.in);
		System.out.print("Repo Link: "); 
		repoLink = scannerRepo.nextLine();
		if(repoLink.charAt(repoLink.length()-1) != 't' && repoLink.charAt(repoLink.length()-2) != 'i' && repoLink.charAt(repoLink.length()-3) != 'g' && repoLink.charAt(repoLink.length()-4) != '.') {
			repoLink += ".git";
		}
		Runtime rt = Runtime.getRuntime();
		try {
	    	Process p = rt.exec(new String[]{"cmd.exe","/c", "git clone " + repoLink});
	    	System.out.println("Waiting For Download...");
	    	p.waitFor();
		} catch (IOException e) {
			System.out.println("Invalid Process");
	    	e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getRepoLink() {
		int i = 0, j = 0;
		do {
			if(repoLink.charAt(j) == '/') {
				i++;
			}
			j++;
		} while(i != 4);
		int lastIndex = repoLink.lastIndexOf(".git");
		return repoLink.substring(j, lastIndex);
	}
	
	public void closeScanner() {
		scannerRepo.close();
	}
}
