package de.artcom_venture.elasticsearch.naturalsort;

import org.apache.lucene.analysis.tokenattributes.CharTermAttributeImpl;
import org.apache.lucene.util.BytesRef;
import java.text.Collator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NaturalSort extends CharTermAttributeImpl {

    private final static Pattern NUMBERS = Pattern.compile("([0-9]+)", Pattern.UNICODE_CHARACTER_CLASS);
    private final Collator collator;
    private final int digits;
    private final int maxTokens;

    public NaturalSort(Collator collator, int digits, int maxTokens) {
        this.collator = collator;
        this.digits = digits;
        this.maxTokens = maxTokens;
    }

    @Override
    public BytesRef getBytesRef() {
        byte[] collationKey = collator.getCollationKey(natural(toString())).toByteArray();
        final BytesRef ref = this.builder.get();
        ref.bytes = collationKey;
        ref.offset = 0;
        ref.length = collationKey.length;
        return ref;
    }

    private String natural(String input) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = NUMBERS.matcher(input);
        int tokens = 0;
        while (matcher.find()) {
            String repl = String.format("%0" + this.digits + "d", matcher.group(1).length()) + matcher.group();
            matcher.appendReplacement(buffer, repl);
            tokens++;
            if (tokens >= this.maxTokens){
                break;
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
