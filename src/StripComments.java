import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StripComments {

    public static String stripComments(String text, String[] commentSymbols) {
        return text.replaceAll("\\s*([" + String.join("", commentSymbols) + "][^\\n]*)?(\\n|$)", "$2");
        /*
        My first solution:
        String[] splitted = text.split(" *[" + String.join("", commentSymbols) + "].*(?=\\n?)| +(?=\\n|$)");
        return String.join("", splitted);

        Test Michael's solution:
        Pattern p = Pattern.compile("\\s*([" + String.join("", commentSymbols) + "][^\\n]*)?(\\n|$)");
        Matcher m = p.matcher(text);
        String result = m.replaceAll(matchResult -> matchResult.group(2));
        System.out.println("result = " + result);
        return result;
         */
    }

    @Test
    public void stripComments() throws Exception {
        assertEquals(
            "apples, pears\ngrapes\nbananas",
            StripComments.stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[]{"#", "!"})
        );

        assertEquals(
            "a\n b\nc",
            StripComments.stripComments("a \n b \nc ", new String[]{"#", "!"})
        );

        assertEquals(
            "a\nc\nd",
            StripComments.stripComments("a #b\nc\nd $e f g", new String[]{"#", "$"})
        );
    }
}
