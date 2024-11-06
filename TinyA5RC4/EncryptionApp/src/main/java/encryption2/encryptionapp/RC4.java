
package encryption2.encryptionapp;
public class RC4 {
    public String handleEncryptncryptRC(String plainText , String key) {
        if(checkInput(plainText, key))
        {
        String cipherText = "";
        int index = 0;
        //Phase 1 : Init S and T
        int S[] = new int[256];
        int T[] = new int[256];
        for(int i = 0 ; i < 256 ; i++) {
            if(index == key.length() ) 
                index = 0;

            S[i] = i;
            T[i] = Integer.parseInt(String.valueOf(key.charAt(index++)));  
        }

        // Phase 2 : Permute S and T
        int i = 0 , j = 0;
        for( i = 0 ; i < 256 ; i++) {

            j = (j + S[i] + T[i]) % 256;

            int m = S[i];
            S[i] = S[j];
            S[j] = m;
        }

        //Phase 3 : Pseudo-random generation
        String pseudo = "";
        i = 0 ;
        j = 0;
        while (true) {

            if(pseudo.length() == plainText.length()) break;

            i = (i+1) % 256;
            j= (j + S[i]) % 256;
            int m = S[i];
            S[i] = S[j];
            S[j] = m;

            int t = (S[i] + S[j]) % 256;
            int k = S[t];
            pseudo += decimalToBinary(k);

        }

        cipherText = xorBinary(plainText, pseudo);
        return cipherText;
        }
        else
        {
            return new String("Your Plainext or your key is wrong");
        }
    }

    public static String decimalToBinary(int decimal) {
        String binary = "";
        int count = 7;

        for(int i = count ; i >= 0 ; i--) {
            if(Math.pow(2,i) > decimal) binary += '0';
            else {
                binary += '1';
                decimal -= Math.pow(2,i);
            }
        }
        return binary;
    }

    public static String xorBinary(String key1 ,String key2) {
        if(key1.length() != key2.length()) {
            System.out.println("Unequal Length of Key ");
            return "";
        }
        else {
            String result = "";
            for(int i = 0 ; i< key1.length() ; i++) {
                if(key1.charAt(i) == key2.charAt(i)) result+='0';
                else result+='1';
            }
            return result;
        }
    }
    
    public static boolean checkInput(String plainText , String key)
    {
        try {
            Integer.parseInt(plainText);
            Integer.parseInt(key);
            if(plainText.toCharArray().length/9 !=0 || plainText.toCharArray().length < 8)
            {
                return false;
            }
                return true ;
        } catch (Exception e) {
            return false;
        }
    }
    }
