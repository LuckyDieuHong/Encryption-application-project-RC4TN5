package encryption2.encryptionapp;

import static encryption2.encryptionapp.RC4.checkInput;
import java.util.Random;

public class TNA5 {
    public static char[] xoayX(char [] x)
    {
        int []xabc= new int[]{2,4,5};
        int count1=0;
        char[] Xthe=new char[x.length];
        for(int i=0;i<xabc.length;i++)
        {
            if(x[xabc[i]]=='1')
            count1++;
        }
        Xthe[0]=x[0];
        for(int i=1;i<x.length;i++)
        {
            Xthe[i]=x[i-1];
        }
        if(count1%2==0)
        {
            Xthe[0]='0';
        }
        else 
        Xthe[0]='1';
        
         return Xthe;
    }
    public static char[] xoayY(char [] y)
    {
        int []yabc= new int[]{6,7};
        int count1=0;
        char[] the=new char[y.length];
        for(int i=0;i<yabc.length;i++)
        {
            if(y[yabc[i]]=='1')
            count1++;
        }
        the[0]=y[0];
        for(int i=1;i<y.length;i++)
        {
            the[i]=y[i-1];
        }
        if(count1%2==0)
        {
            the[0]='0';
        }
        else 
        the[0]='1';       
         return the;
    }
    public static char[] xoayZ(char [] z)
    {
        int []zabc= new int[]{2,7,8};
        int count1=0;
        char[] the=new char[z.length];
        for(int i=0;i<zabc.length;i++)
        {
            if(z[zabc[i]]=='1')
            count1++;
        }
        the[0]=z[0];
        for(int i=1;i<z.length;i++)
        {
            the[i]=z[i-1];
        }
        if(count1%2==0)
        {
            the[0]='0';
        }
        else 
        the[0]='1';
        
         return the;
    }
    public static char XOR(char a,char b)
    {
        if(a==b)
        return '0';
        else return '1';
    }
    public String MaHoatinya5(String plaintxt)
    {
         if(checkInput(plaintxt))
        {
            char[]K=new char[64];     
        Random r =new Random();
        StringBuilder khoaK= new StringBuilder(64);
        for(int i=0;i<64;i++)
        {
            khoaK.append(r.nextBoolean()?"1":"0");
        }
        //System.out.println("Khoa k cua ban la: "+khoaK.toString());
        K=khoaK.toString().toCharArray();
        char[]plain= plaintxt.toCharArray();
        char[] x=new char[19];
        char[] y=new char[22];
        char[] z=new char[23];
        char[] s=new char[plain.length];
        int nx=0, ny=0,nz=0;
        int m=0,c1=0;
        
        for(int i=0;i<K.length;i++)
        {
            if(i>=0&&i<19)
            x[nx++]=K[i];
            if(i>=19&&i<41)
            y[ny++]=K[i];
            if(i>=41&&i<64)
            z[nz++]=K[i];
        }
        String X=new String(x);
        String Y=new String(y);
        String Z=new String(z);
        //System.out.println(X+":"+Y+":"+Z);
        char[] maj=new char[]{x[1],y[3],z[3]};
        for(int i=0;i<maj.length;i++){
            int somaij=Character.getNumericValue(maj[i]);
               if(somaij==1)
               {
                c1++;
               }
            }
        if(c1>=2)
        m=1;
        else
        m=0;
        for(int i=0;i<s.length;i++)
        {
            
        int gtrix=Character.getNumericValue( x[1]);
          int gtriy=Character.getNumericValue(y[3]);
          int gtriz=Character.getNumericValue(z[3]);
        if(gtrix==m);
        x=xoayX(x);
        if(gtriy==m)
        y=xoayY(y);
        if(gtriz==m)
        z=xoayZ(z);
        X=new String(x);
        Y=new String(y);
        Z=new String(z);
        //System.out.println(X+":"+Y+":"+Z);
        s[i]=XOR(z[z.length-1],XOR(x[x.length-1],y[y.length-1]));
        plain[i]=XOR(s[i],plain[i]);
        }
        plaintxt=new String(plain);
        return plaintxt;
        }
         else 
        {
            return new String("Your Plainext or your key is wrong");
        }
    }
    
    public static boolean checkInput(String plainText)
    {
        try {
            Integer.parseInt(plainText);
             return true ;
        } catch (Exception e) {
            return false;
        }
    }
}
