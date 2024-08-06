import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author İskender Musaoğlu / iskender.musaoglu@ogr.sakarya.edu.tr
* @since 01.04.2024 - 05.04.2024
* <p>
* Dosyalar üzerindeki tüm işlemeri yapan sınıf
* </p>
*/

public class Folders {
    private List<File> fileList;
    private File folder;
    private int javaFileNumber;
    private List<FileClass> fileClassList;
    
    Folders() {
    	fileList = new ArrayList<>();
    	javaFileNumber = 0;
    	fileClassList = new ArrayList<>();
    }

    public void getFolderPath(String str) {
        folder = new File(str);
    }

    public void scanList() {
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Klasör bulunamadı veya bir klasör değil.");
        }
        listFiles(folder);
    }

    public void printListFolder() {
        for (File file : fileList) {
            System.out.println(file.getName());
        }
    }
       
    public void printAllFoldersOfLineCount() {
    	for(int counter = 0; counter < fileClassList.size(); counter++) {
    		int col = 0;
        	Scanner scanner1;
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {
    	        	col++;
    	        	scanner1.nextLine(); 	
    	        }
    	        fileClassList.get(counter).setLoc(col);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}   		
    	}
    }
    
    public void printAllFoldersFuncCount() {
    	String regex = "(\\s)*((?:public |private |static |abstract )+)(\\s)*(?:boolean|int|void|double|char|long|short|byte|float)*(\\s)*(.*)(\\s)*\\((.*)\\)(\\s)*\\{?";
    	for(int counter = 0; counter != javaFileNumber; counter++) {
    		int funcNumber = 0;
        	Scanner scanner1;
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {  	
    	        	Pattern connection = Pattern.compile(regex);
    	        	String t = scanner1.nextLine();
    	        	Matcher okayConnection = connection.matcher(t);
    	        	if(okayConnection.find() == true) {
    	        		boolean isthere = false;
    	        		for(int i = 0; i < t.length(); i++) {
    	        			if(t.charAt(i) == '=') isthere = true;
    	        		}
    	        		if(isthere == false) {
    	        			funcNumber++;
    	        		}
    	        	}
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		fileClassList.get(counter).setFuncCount(funcNumber);
    	}
    }
    
    public void printFolderOfData(int index) {
    	File fl = fileList.get(index);
    	System.out.println("Name Of This Folder: " + fl.getName());
    	System.out.println("Data: ");
    	Scanner scanner1;
		try {
			scanner1 = new Scanner(fileList.get(index));
	        while (scanner1.hasNextLine()) {
	        	System.out.println(scanner1.nextLine());
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private void listFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                	int length = file.getName().length();
                	if(length > 4) {
                		if(file.getName().charAt(length - 1) == 'a' && file.getName().charAt(length - 2) == 'v' && file.getName().charAt(length - 3) == 'a' && file.getName().charAt(length - 4) == 'j' && file.getName().charAt(length - 5) == '.') {
                			fileList.add(file);
                			javaFileNumber++;
                		}
                	}
                } else if (file.isDirectory()) {
                    listFiles(file);
                }
            }
        } else {
            System.out.println("Klasör boş veya okunabilir değil.");
        }
    }
    
    public void onlyClassFiles() {
    	int javaFileNumber1 = 0;
    	File fl;
    	List<File> fileList1 = new ArrayList<>();
    	for(int counter = 0; counter != javaFileNumber; counter++) {
    		fl = fileList.get(counter);
        	Scanner scanner1;
        	String regex = "(\\s)*public\\s(?:class|interface)\\s[a-zA-Z][a-zA-Z0-9]*(?:\\simplements\\s[a-zA-Z][a-zA-Z0-9]*)*(\\s)*\\{?";
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {
    	        	Pattern connection = Pattern.compile(regex);
    	        	Matcher okayConnection = connection.matcher(scanner1.nextLine());
    	        	if(okayConnection.find() == true) {
    	        		fileList1.add(fl);
    	        		javaFileNumber1++;
    	        	}
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    	fileList = fileList1;
    	javaFileNumber = javaFileNumber1;
    }
    public void onlyClassFilesWithoutInterfaceAndAbstract() {
    	int javaFileNumber1 = 0;
    	File fl;
    	List<File> fileList1 = new ArrayList<>();
    	for(int counter = 0; counter != javaFileNumber; counter++) {
    		fl = fileList.get(counter);
        	Scanner scanner1;
        	String regex = "(\\s)*public\\sclass\\s[a-zA-Z][a-zA-Z0-9]*(?:\\simplements\\s[a-zA-Z][a-zA-Z0-9]*)*(\\s)*\\{?";
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {
    	        	Pattern connection = Pattern.compile(regex);
    	        	Matcher okayConnection = connection.matcher(scanner1.nextLine());
    	        	if(okayConnection.find() == true) {
    	        		fileList1.add(fl);
    	        		javaFileNumber1++;
    	        		FileClass fltemp = new FileClass();
    	        		fltemp.setName(fl.getName());
    	        		fileClassList.add(fltemp);
    	        	}
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    	fileList = fileList1;
    	javaFileNumber = javaFileNumber1;
    }
    
    public void javaDocCount() {
    	String regex = "(?<!\")\\/\\*\\*[^\"\\r\\n]*(?:[\\s\\S]*?)\\*\\/(?!\")";
    	String t = "";
    	for(int counter = 0; counter != javaFileNumber; counter++) {
    		t = "";
        	Scanner scanner1;
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {    	        	
    	        	t += (scanner1.nextLine() + "\n");
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Pattern connection = Pattern.compile(regex);
    		Matcher okayConnection = connection.matcher(t);
    		int javaDoc = 0;
    		int tempS = 0;
    		while (okayConnection.find()) {
    			for(int i = 0; i < okayConnection.group().length(); i++) {
    				if(okayConnection.group().charAt(i) == '\n') {
    					javaDoc++;
    				}
    			}
    			tempS++;
            }
    		if(javaDoc != 0) javaDoc = javaDoc-tempS;
    		fileClassList.get(counter).setJavaDocCount(javaDoc);
    	}
    }
    
    public void commentCount() {
    	String regex = "(?<!\")//.*";
    	for(int counter = 0; counter != javaFileNumber; counter++) {
        	Scanner scanner1;
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    			int t = 0;
    	        while (scanner1.hasNextLine()) {    	        	
    	        	Pattern connection = Pattern.compile(regex);
    	        	String str = scanner1.nextLine();
    	    		Matcher okayConnection = connection.matcher(str);
    	    		if(okayConnection.find() == true) {
    	    			t++;
    	        	}  	    		
    	        }
    	        fileClassList.get(counter).setCommentCount(fileClassList.get(counter).getCommentCount() + t);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public void commentCount2() {
    	String regex = "(?<!\")\\/\\*(?!\\*)(?:[\\s\\S]*?)\\*\\/(?!\")";
    	String t = "";
    	for(int counter = 0; counter != javaFileNumber; counter++) {
    		t = "";
        	Scanner scanner1;
    		try {
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {    	        	
    	        	t += (scanner1.nextLine() + "\n");
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}
    		Pattern connection = Pattern.compile(regex);
    		Matcher okayConnection = connection.matcher(t);
    		int javaDoc = 0;
    		int tempS = 0;
    		while (okayConnection.find()) {
    			for(int i = 0; i < okayConnection.group().length(); i++) {
    				if(okayConnection.group().charAt(i) == '\n') {
    					javaDoc++;
    				}
    			}
    			tempS++;
            }
    		if(javaDoc != 0) javaDoc = javaDoc-tempS;
    		fileClassList.get(counter).setCommentCount(fileClassList.get(counter).getCommentCount() + javaDoc);
    	}
    }
    
    public void codeRowsCount() {
    	String regex = "^(\\s)*[?!/|*]";
    	String regex2 = "^(?!\\s*$).+$";
    	for(int counter = 0; counter != javaFileNumber; counter++) {
        	Scanner scanner1;
        	int t = 0;
    		try {	
    			scanner1 = new Scanner(fileList.get(counter));
    	        while (scanner1.hasNextLine()) {
    	        	String str = scanner1.nextLine();
    	        	Pattern pattern = Pattern.compile(regex);
    	            Matcher okayConnection = pattern.matcher(str);
    	            Pattern pattern2 = Pattern.compile(regex2);
    	            Matcher okayConnection2 = pattern2.matcher(str);
    	            if (okayConnection.find() != true) {
    	            	if (okayConnection2.find() == true) {
        	            	t++;
        	            }
    	            }
    	            fileClassList.get(counter).setCodeCount(t);
    	        }
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		}		
    	}
    }
    
    public void printDataFolder() {
    	for(int i = 0; i < fileClassList.size(); i++) {
    		System.out.println("Sınıf: " + fileClassList.get(i).getName());
    		System.out.println("JavaDoc Satır Sayısı: " + fileClassList.get(i).getJavaDocCount());
    		System.out.println("Yorum Satır Sayısı: " + fileClassList.get(i).getCommentCount());
    		System.out.println("Kod Satır Sayısı: " + fileClassList.get(i).getCodeCount());
    		System.out.println("LOC: " + fileClassList.get(i).getLoc());
    		System.out.println("Fonksiyon Sayısı: " + fileClassList.get(i).getFuncCount());
    		System.out.println("Yorum Sapma Yüzdesi: % " + FileClass.spmyDTS(fileClassList.get(i).getSpmy()));
    		System.out.println("-----------------------------------------------------");
    	}
    }
    
    public void calcSmpy() {
    	for(int i = 0; i < fileClassList.size(); i++) {
    		double javaDoc = fileClassList.get(i).getJavaDocCount();
    		double comments = fileClassList.get(i).getCommentCount();
    		double func = fileClassList.get(i).getFuncCount();
    		double codeLineCount = fileClassList.get(i).getCodeCount();
    		double yg = ((javaDoc + comments)*0.8) / func;
    		double yh = (codeLineCount/func)*0.3;
    		double smpy = ((100*yg)/yh)-100;
    		fileClassList.get(i).setSpmy(smpy);
    	}
    }
}
