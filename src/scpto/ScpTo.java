package scpto;

import com.jcraft.jsch.*;
import java.io.*;
import java.util.Calendar;
 
public class ScpTo {
	
	private String lfile;
	private String user;
	private String host;
	private String rfile;
	
	public ScpTo(String lfile, String user, String host, String rfile) {
		this.lfile = lfile;
		this.user = user;
		this.host = host;
		this.rfile = rfile;
	}
	
	public void send(){ 
	    FileInputStream fis=null;
	    try{
	    	JSch jsch=new JSch();
	    	Session session=jsch.getSession(user, host, 22);
 
	    	UserInfo ui=new MyUserInfo();
	    	session.setUserInfo(ui);
	    	System.out.println("Inicio:" + Calendar.getInstance().getTime());
	    	session.connect();
 
	    	boolean ptimestamp = true;
	 
		    // exec 'scp -t rfile' remotely
		    String command="scp " + (ptimestamp ? "-p" :"") +" -t "+rfile;
		    Channel channel=session.openChannel("exec");
		    ((ChannelExec)channel).setCommand(command);
 
		    // get I/O streams for remote scp
		    OutputStream out=channel.getOutputStream();
		    InputStream in=channel.getInputStream();
 
		    channel.connect();
		    if(checkAck(in)!=0)
		    	System.exit(0);
 
		    File _lfile = new File(lfile);
	 
		    if(ptimestamp){
		    		command="T "+(_lfile.lastModified()/1000)+" 0";
			        command+=(" "+(_lfile.lastModified()/1000)+" 0\n"); 
			        out.write(command.getBytes()); out.flush();
			        if(checkAck(in)!=0)
			        	System.exit(0);
		    }
 
		    long filesize=_lfile.length();
		    command="C0644 "+filesize+" ";
		    if(lfile.lastIndexOf('/')>0)
		    	command+=lfile.substring(lfile.lastIndexOf('/')+1);
		    else
		    	command+=lfile;
		    command+="\n";
		    out.write(command.getBytes()); out.flush();
		    if(checkAck(in)!=0)
		    	System.exit(0);
 
		    // send a content of lfile
		    fis=new FileInputStream(lfile);
		    byte[] buf=new byte[1024];
		    while(true){
		    	int len=fis.read(buf, 0, buf.length);
		    	if(len<=0) 
		    		break;
		    	out.write(buf, 0, len); //out.flush();
		    }
		    fis.close();
		    fis=null;
		    // send '\0'
		    buf[0]=0; out.write(buf, 0, 1); out.flush();
		    if(checkAck(in)!=0)
		    	System.exit(0);
		    out.close();
 
		    channel.disconnect();
		    session.disconnect();
		    System.out.println("Fim:" + Calendar.getInstance().getTime());
 
		    //System.exit(0);
	    }
	    catch(Exception e){
	    	System.out.println(e);
	    	try{
	    		if(fis!=null)
	    			fis.close();
	    		}
	    	catch(Exception ee){}
	    }
  }
 
	static int checkAck(InputStream in) throws IOException{
		int b=in.read();
		// b may be 0 for success,
	    //          1 for error,
	    //          2 for fatal error,
	    //          -1
	    if(b==0) 
	    	return b;
	    if(b==-1) 
	    	return b;
 
	    if(b==1 || b==2){
	    	StringBuffer sb=new StringBuffer();
	    	int c;
	    	do {
	    		c=in.read();
	    		sb.append((char)c);
	    	}
	    	while(c!='\n');
	    	if(b==1) // error
	    		System.out.print(sb.toString());
	    	if(b==2) // fatal error
	    		System.out.print(sb.toString());
	    }
	    return b;
	}
 
  public static class MyUserInfo implements UserInfo{
	  String passwd="telemidia";
	    
	  public String getPassword(){ 
		  return passwd; 
	  }
    
	  public boolean promptYesNo(String str){
		  return true;
	  }
  
	  public String getPassphrase(){ 
		  return null; 
	  }
    
	  public boolean promptPassphrase(String message){ 
		  return true; 
	  }
    
	  public boolean promptPassword(String message){
		  return true;
	  }
    
	  public void showMessage(String message){}
   
  } // Fim da classe MyUserInfo

} //Fim da classe ScpTo