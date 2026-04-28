package Complementary_DNA;

import java.lang.StringBuffer;

public class DnaStrand {
    public static String makeComplement(String dna) {
        //StringBuffer buf = new StringBuffer(dna.length());
        //for (int i=0; i<dna.length(); i++) {
        //  switch(dna.charAt(i)) {
        //   case 'A': buf.append('T'); break;
        //    case 'T': buf.append('A'); break;
        //    case 'C': buf.append('G'); break;
        //    case 'G': buf.append('C'); break;
        //  }
        //}
        return dna.replace('A','t').replace('T','a').replace('C','g').replace('G', 'c').toUpperCase();
    }
}