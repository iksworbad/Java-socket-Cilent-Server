
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Server {
public static void main(String args[]) throws Exception {
String filename;
System.out.println("Wpisz nazwe pliku: ");
Scanner sc=new Scanner(System.in);
filename=sc.nextLine();
sc.close();
while(true) {
ServerSocket ss=new ServerSocket(5000);
System.out.println("Czekam na polaczenie...");
Socket s=ss.accept();
System.out.println("Polaczene z: "+ s.getInetAddress().toString());
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
//BufferedReader br=new BufferedReader(new inputStreamReader(System.in));
try {
String str="";
str=din.readUTF();
System.out.println("Wysylanie....ok");
if(!str.equals("stop")) {
System.out.println("Wysylanie pliku: "+filename);
dout.writeUTF(filename);
File f=new File(filename);
FileInputStream fin=new FileInputStream(f);
long sz=(int ) f.length();
byte b[]=new byte [1024];
int read;
dout.writeUTF(Long.toString(sz));
dout.flush();
System.out.println("Wielkosc: "+ sz);
System.out.println("Wielkosc bufforu: "+ ss.getReceiveBufferSize());
while ((read=fin.read(b))!=-1) {
dout.write(b,0,read);
dout.flush();
}
fin.close();
System.out.println("...ok");
dout.flush();
}
dout.writeUTF("stop");
System.out.println("Wysylanie zakonczone");
dout.flush();
}catch(Exception e) {
e.printStackTrace();
System.out.println("error");
}
din.close();
s.close();
ss.close();
}
}

}