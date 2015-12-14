package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Game {
    private String solution;
    private VDMSeq attempts = SeqUtil.seq();
    private Number maxAttempts;
    private Player codeMaker;
    private Player codeBreaker;
    private Number boardLenght;
    private Number matches;

    public Game(final String correctPlay, final String m, final String b,
        final Number trys, final Number size, final Number partidas) {
        cg_init_Game_1(correctPlay, m, b, trys, size, partidas);
    }

    public Game(final Number trys, final Number size, final Number partidas) {
        cg_init_Game_2(trys, size, partidas);
    }

    public Game() {
    }

    public void cg_init_Game_1(final String correctPlay, final String m,
        final String b, final Number trys, final Number size,
        final Number partidas) {
        solution = correctPlay;
        attempts = SeqUtil.seq();
        codeMaker = new Player(m);
        codeBreaker = new Player(b);
        maxAttempts = trys;
        boardLenght = size;
        matches = partidas;
    }

    public void cg_init_Game_2(final Number trys, final Number size,
        final Number partidas) {
        boardLenght = size;
        matches = partidas;
        solution = Piece.getRandomSequence(boardLenght);
        attempts = SeqUtil.seq();
        codeMaker = new Player();
        codeBreaker = new Player();
        maxAttempts = trys;
    }

    public String getSolution() {
        return solution;
    }

    public Player getCodeBreaker() {
        return codeBreaker;
    }

    public Player getCodeMaker() {
        return codeMaker;
    }

    public Number getMatches() {
        return matches;
    }

    public void setMaxAttempts(final Number n) {
        maxAttempts = n;
    }

    public Boolean isGameOver() {
        Boolean orResult_2 = false;

        if (isSolutionCracked()) {
            orResult_2 = true;
        } else {
            orResult_2 = Utils.equals(attempts.size(), maxAttempts);
        }

        return orResult_2;
    }

    public void winGame() {
        if (isSolutionCracked()) {
            codeMaker.addPoints(attempts.size());
        } else {
            codeMaker.addPoints(attempts.size() + 1L);
        }
    }

    public void changePlayer() {
        Player tmp = codeMaker;
        codeMaker = codeBreaker;
        codeBreaker = tmp;
        matches = matches.longValue() - 1L;
    }

    public Boolean isSolutionCracked() {
        return SetUtil.inSet(solution, SeqUtil.elems(Utils.copy(attempts)));
    }

    public void makeAPlay(final String attempt) {
        attempts = SeqUtil.conc(Utils.copy(attempts), SeqUtil.seq(attempt));
    }

    public Number numberOfTriesRemaining() {
        return maxAttempts.longValue() - attempts.size();
    }

    public Number calculateRightColorsInRightPlaces(final String attempt) {
        return numberOfMatchingElems(attempt, solution);
    }

    public Number calculateRightColorsInWrongPlaces(final String attempt) {
        Number temp = 0L;

        for (Iterator iterator_6 = Piece.ColorsSet.iterator();
                iterator_6.hasNext();) {
            Character color = (Character) iterator_6.next();
            temp = temp.longValue() +
                min(countOccurences(solution, color),
                    countOccurences(attempt, color)).longValue();
        }

        return temp.longValue() -
        calculateRightColorsInRightPlaces(attempt).longValue();
    }

    private static Number numberOfMatchingElems(final String firstSeq,
        final String secondSeq) {
        if (Utils.equals(firstSeq, SeqUtil.toStr(SeqUtil.seq()))) {
            return 0L;
        } else {
            if (Utils.equals(firstSeq.charAt(0), secondSeq.charAt(0))) {
                return 1L +
                numberOfMatchingElems(SeqUtil.tail(firstSeq),
                    SeqUtil.tail(secondSeq)).longValue();
            } else {
                return numberOfMatchingElems(SeqUtil.tail(firstSeq),
                    SeqUtil.tail(secondSeq));
            }
        }
    }

    private static Number countOccurences(final String sequence,
        final Character element) {
        if (Utils.equals(sequence, SeqUtil.toStr(SeqUtil.seq()))) {
            return 0L;
        } else {
            if (Utils.equals(sequence.charAt(0), element)) {
                return 1L +
                countOccurences(SeqUtil.tail(sequence), element).longValue();
            } else {
                return countOccurences(SeqUtil.tail(sequence), element);
            }
        }
    }

    public static Number min(final Number a, final Number b) {
        if (a.longValue() <= b.longValue()) {
            return a;
        } else {
            return b;
        }
    }

    public String toString() {
        return "Game{" + "solution := " + Utils.toString(solution) +
        ", attempts := " + Utils.toString(attempts) + ", maxAttempts := " +
        Utils.toString(maxAttempts) + ", codeMaker := " +
        Utils.toString(codeMaker) + ", codeBreaker := " +
        Utils.toString(codeBreaker) + ", boardLenght := " +
        Utils.toString(boardLenght) + ", matches := " +
        Utils.toString(matches) + "}";
    }
}
