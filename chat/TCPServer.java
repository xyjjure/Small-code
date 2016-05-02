import java.net.*;
import java.io.*;
public class TCPServer {

	
	public static void main(String[] args){
       try{
		ServerSocket ss=new ServerSocket(6666);
		Socket s=ss.accept();
		ReceiveServer re=new ReceiveServer(s);
		SendServer se=new SendServer(s);
		Thread t1=new Thread(re);
		Thread t2=new Thread(se);
		t1.start();
		t2.start();
       }catch(Exception e){
    	   System.out.println("´íÎó£¬ÏµÍ³ÍË³ö");
    	   System.exit(-1);
       }

	}

}
class ReceiveServer implements Runnable{
    private String line;
	private Socket s=null;
	ReceiveServer(Socket s){
		this.s=s;
	}
	public void run(){
		try{
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		System.out.println("Client:"+line);
		    while(!(line=br.readLine()).equalsIgnoreCase("out")){
			System.out.println("Client:"+line);
		}
		 br.close();
		// s.close();  
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("error");
		}
		
		
	}
}
class SendServer implements Runnable{
	private String line;
	private Socket s=null;
	SendServer(Socket s){
		this.s=s;
	}
	public void run(){
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw=new PrintWriter(s.getOutputStream());
			while(!(line=br.readLine()).equalsIgnoreCase("out")){
				System.out.println("Server:"+line);
				pw.println(line);
				pw.flush();
			}
			br.close();
			pw.close();
			s.close();
			System.exit(-1);
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("error");
			}
	}
}