package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Utilities {
    public Utilities() {
    }

    public static Number min(final Number a, final Number b) {
        if (a.longValue() <= b.longValue()) {
            return a;
        } else {
            return b;
        }
    }

    public static <T> String seqToString(final VDMSeq sequence,
        final Func_1<T, String> printer) {
        if (Utils.empty(sequence)) {
            return "[]";
        } else {
            return "[" + auxSeqToString(Utils.copy(sequence), printer) + "]";
        }
    }

    private static <T> String auxSeqToString(final VDMSeq sequence,
        final Func_1<T, String> printer) {
        if (Utils.equals(sequence.size(), 1L)) {
            return printer.eval(((T) sequence.get(0)));
        } else {
            return printer.eval(((T) sequence.get(0))) + ", " +
            auxSeqToString(SeqUtil.tail(Utils.copy(sequence)), printer);
        }
    }

    private static <T> VDMSeq setToSeq(final VDMSet s) {
        if (Utils.equals(s.size(), 1L)) {
            VDMSeq letBeStExp_1 = null;
            T x = null;
            Boolean success_1 = false;
            VDMSet set_4 = Utils.copy(s);

            for (Iterator iterator_4 = set_4.iterator();
                    iterator_4.hasNext() && !(success_1);) {
                x = ((T) iterator_4.next());
                success_1 = true;
            }

            if (!(success_1)) {
                throw new RuntimeException(
                    "Let Be St found no applicable bindings");
            }

            letBeStExp_1 = SeqUtil.seq(x);

            return Utils.copy(letBeStExp_1);
        } else {
            VDMSeq letBeStExp_2 = null;
            T x = null;
            Boolean success_2 = false;
            VDMSet set_5 = Utils.copy(s);

            for (Iterator iterator_5 = set_5.iterator();
                    iterator_5.hasNext() && !(success_2);) {
                x = ((T) iterator_5.next());
                success_2 = true;
            }

            if (!(success_2)) {
                throw new RuntimeException(
                    "Let Be St found no applicable bindings");
            }

            letBeStExp_2 = SeqUtil.conc(SeqUtil.seq(x),
                    setToSeq(SetUtil.diff(Utils.copy(s), SetUtil.set(x))));

            return Utils.copy(letBeStExp_2);
        }
    }

    public static <T> String setToString(final VDMSet s,
        final Func_1<T, String> printer) {
        if (Utils.empty(s)) {
            return "{}";
        } else {
            return "{" + auxSeqToString(setToSeq(Utils.copy(s)), printer) +
            "}";
        }
    }

    public String toString() {
        return "Utilities{}";
    }
}
