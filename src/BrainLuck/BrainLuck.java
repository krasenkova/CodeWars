package BrainLuck;
/*
result.append((char) Byte.toUnsignedInt(memory[pointer]));
 */

import java.util.*;

public class BrainLuck {

    private String[] code;
    private byte[] memory;
    private int pointer;

    private Map<Integer, Integer> pairsForOpenBracket;
    private Map<Integer, Integer> pairsForCloseBrackets;

    public BrainLuck(String code) {
        this.memory = new byte[10_000];
        this.pointer = 0;
        this.code = code.split("");
        this.pairsForOpenBracket = new HashMap<>();
        this.pairsForCloseBrackets = new HashMap<>();
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < this.code.length; i++) {
            switch (this.code[i]) {
                case "[":
                    stack.push(i);
                    break;
                case "]":
                    int bracketOpened = stack.pop();
                    pairsForOpenBracket.put(bracketOpened, i);
                    pairsForCloseBrackets.put(i, bracketOpened);
                    break;
                default:
                    // do nothing
            }
        }
    }

    public String process(String input) {
        StringBuilder result = new StringBuilder();
        Iterator<String> inputIterator = Arrays.asList(input.split("")).iterator();
        int instructionPointer = 0;
        while (instructionPointer < code.length) {
            String command = code[instructionPointer];
            switch (command) {
                case ">":
                    pointer++;
                    break;
                case "<":
                    pointer--;
                    break;
                case "+":
                    memory[pointer]++;
                    break;
                case "-":
                    memory[pointer]--;
                    break;
                case ".":
                    result.append((char) memory[pointer]);
                    break;
                case ",":
                    memory[pointer] = (byte) inputIterator.next().charAt(0);
                    break;
                case "[":
                    if (memory[pointer] == 0) {
                        int matchingCloseBracket = this.pairsForOpenBracket.get(instructionPointer);
                        instructionPointer = matchingCloseBracket + 1;
                        continue;
                    }
                    break;
                case "]":
                    if (memory[pointer] != 0) {
                        int matchingOpenBracket = this.pairsForCloseBrackets.get(instructionPointer);
                        instructionPointer = matchingOpenBracket + 1;
                        continue;
                    }
                    break;
            }
            instructionPointer++;
        }
        return result.toString();
    }
}
