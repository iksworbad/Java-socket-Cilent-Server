import java.net.*;
import java.util.Scanner;
import java.io.*;

class Client {
    public static void main(String[] args) throws Exception {
  
        String address = "";
        Scanner sc=new Scanner (System.in);
        System.out.println("Wpisz adres serwera (127.0.0.1): ");
        address=sc.nextLine();
        
        Socket s=new Socket (address, 5000);
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
        //
        System.out.println("Wpisz \"start\" aby rozpoczac pobieranie ...");
        String str="", filename="";
        try {
        	while(!str.equals("start"))
        		str=br.readLine();
        	
        	dout.writeUTF(str);
        	dout.flush();
        	
        	filename=din.readUTF();
        	System.out.println("Odebrany program: " +filename);
        	filename="client"+filename;
        	System.out.println("Zapisany jako: " +filename);
        //
        long sz =Long.parseLong(din.readUTF());
        System.out.println("Rozmiar pliku: " +(sz/(1024*1024))+" MB");
        //
        byte b[]=new byte [1024];
        System.out.println("Odbieranie pliku... " );
        FileOutputStream fos=new FileOutputStream(new File (filename), true);
        long bytesRead;
        //
        do
        {
        	bytesRead = din.read(b, 0, b.length);
        /*
          */
        
        fos.write(b,0,b.length);
        //
        //
        }while(!(bytesRead<1024));
        System.out.println("Ukoñczono pobieranie " );
        fos.close();
        dout.close();
        s.close();
        }
        catch(Exception e)
        {
        	//
        }}}
        	
        	
        