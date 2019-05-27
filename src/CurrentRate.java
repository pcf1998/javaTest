import java.net.*;
import java.io.*;

public class CurrentRate {

    public static void main(String args[]) throws Exception {
        URL u=new URL("http://api.k780.com/?app=finance.rate_unionpayintl&cur_base=CNY&cur_transaction=USD&cur_date=20190404&appkey=41463&sign=7a17830d5bba9d840e8af0b39a561adc&format=json");
        InputStream in=u.openStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        }  finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        System.out.println(new String(b,"utf-8"));
    }




}
