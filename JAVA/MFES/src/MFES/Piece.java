package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Piece {
    public static VDMSet ColorsSet = SetUtil.set('b', 'g', 'r', 'o', 'y', 'm');
    public static String Colors = new String(new char[] {
                'b', 'g', 'r', 'o', 'y', 'm'
            });

    public Piece() {
    }

    public static String getRandomSequence(final Number length) {
        String seqCompResult_1 = new String(new char[] {  });

        VDMSet set_2 = SetUtil.range(1L, (double) length);

        for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext();) {
            Number i = ((Number) iterator_2.next());
            seqCompResult_1 = seqCompResult_1 +
                new String(new char[] { randomPiece() });
        }

        return seqCompResult_1;
    }

    private static Character randomPiece() {
        return Colors.charAt(Utils.index(MATH.rand(5L).longValue() + 1L));
    }

    public String toString() {
        return "Piece{" + "ColorsSet := " + Utils.toString(ColorsSet) +
        ", Colors := " + Utils.toString(Colors) + "}";
    }
}
