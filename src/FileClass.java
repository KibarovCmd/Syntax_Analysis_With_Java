import java.text.DecimalFormat;

/**
*
* @author İskender Musaoğlu / iskender.musaoglu@ogr.sakarya.edu.tr
* @since 01.04.2024 - 05.04.2024
* <p>
* Class dosyalarının bilgilerini set ve get etmek için yazılmış sınıf
* </p>
*/

public class FileClass {
	String name;
	int javaDocCount;
	int commentCount;
	int loc;
	int stdrspm;
	int funcCount;
	int codeCount;
	double spmy;
	
	FileClass() {
		name = "";
		javaDocCount = 0;
		commentCount = 0;
		loc = 0;
		stdrspm = 0;
		funcCount = 0;
		codeCount = 0;
		spmy = 0;
	}
		
	public static String spmyDTS(double number) {
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedNumber = df.format(number);
        return formattedNumber;
    }
	
	public double getSpmy() {
		return spmy;
	}

	public void setSpmy(double spmy) {
		this.spmy = spmy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJavaDocCount() {
		return javaDocCount;
	}

	public void setJavaDocCount(int javaDocCount) {
		this.javaDocCount = javaDocCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getStdrspm() {
		return stdrspm;
	}

	public void setStdrspm(int stdrspm) {
		this.stdrspm = stdrspm;
	}

	public int getFuncCount() {
		return funcCount;
	}

	public void setFuncCount(int funcCount) {
		this.funcCount = funcCount;
	}

	public int getCodeCount() {
		return codeCount;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}
}
