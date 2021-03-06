import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;

        try {
            socket = new Socket("localhost",3333);

            inputStreamReader=new InputStreamReader(socket.getInputStream());
            outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());

            bufferedReader=new BufferedReader(inputStreamReader);
            bufferedWriter= new BufferedWriter(outputStreamWriter);

            Scanner scanner= new Scanner(System.in);

            while(true){
                String msgToSend=scanner.nextLine();

                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                //flush the buffer so that things  don't pile up
                bufferedWriter.flush();

                System.out.println("Server: "+ bufferedReader.readLine());
//if you type bye the session ends
                if(msgToSend.equalsIgnoreCase("Bye"))
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
                  try{
                        if(socket !=null)
                            socket.close();
                        if(inputStreamReader !=null)
                            inputStreamReader.close();
                      if(outputStreamWriter !=null)
                          outputStreamWriter.close();
                      if(bufferedReader !=null)
                          bufferedReader.close();
                      if(bufferedWriter !=null)
                          bufferedWriter.close();
                  }catch(IOException e){
                      e.printStackTrace();
                  }

        }


    }
}

