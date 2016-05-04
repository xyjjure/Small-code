import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.*;
import java.io.*;

public class CodeCounter {
    static long normallines = 0;
    static long commentlines = 0;
    static long whitelines = 0;
	public static void main(String[] args) {
		File f=new File("E:\\Eclipse\\thread\\src\\thread");
		File[] codefile=f.listFiles();
		String line = " ";
		for(File child: codefile){
			if(child.getName().matches(".*\\.java$")){
				parse(child);
			}
		}
		System.out.println("normallines:"+normallines);
		System.out.println("commentlines:"+commentlines);
		System.out.println("whitelines:"+whitelines);

	}
	private static void parse(File f) {
		BufferedReader br = null; 
		boolean comment=false;
		try {
			br= new BufferedReader(new FileReader(f));
			String line= " ";
			while((line=br.readLine())!=null){
				line=line.trim();
				if(line.matches("^[\\s&&[^\\n]]*$")){
					whitelines++;
				} else if(line.startsWith("/*")&&!line.endsWith("*/")){
					commentlines++;
					comment=true;
				}else if(true == comment){
					commentlines++;
					if(line.endsWith("*/")){
						comment=false;
					}
				}else if(line.startsWith("//")){
					commentlines++;
				}else if(line.startsWith("/*")&&line.endsWith("*/")){
					commentlines++;
				}else {
					normallines++;
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br !=null){
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				br=null;
			}
		}
		
	}

}
