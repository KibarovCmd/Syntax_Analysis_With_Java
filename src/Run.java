/**
*
* @author İskender Musaoğlu / iskender.musaoglu@ogr.sakarya.edu.tr
* @since 01.04.2024 - 05.04.2024
* <p>
* İşlemleri başlatan sınıf
* </p>
*/
public class Run {
	public Run() {
		System.out.println("Alexander's Sign");
	}
	public static void runAlexander() {
		Clone cln = new Clone();
		cln.clone_Repo_From_Git();
		Folders fl = new Folders();
		fl.getFolderPath(cln.getRepoLink());
		fl.scanList();
		fl.onlyClassFilesWithoutInterfaceAndAbstract();
		fl.printAllFoldersFuncCount();
		fl.javaDocCount();
		fl.printAllFoldersOfLineCount();
		fl.commentCount();
		fl.commentCount2();
		fl.codeRowsCount();
		fl.calcSmpy();
		System.out.println("-----------------------------------------------------");
		fl.printDataFolder();
	}
	
	public static void runAlexander2() {
		Clone cln = new Clone();
		cln.clone_Repo_From_Git();
		Folders fl = new Folders();
		fl.getFolderPath(cln.getRepoLink());
		fl.scanList();
		fl.onlyClassFilesWithoutInterfaceAndAbstract();
		fl.codeRowsCount();
		System.out.println("-----------------------------------------------------");
	}
}
