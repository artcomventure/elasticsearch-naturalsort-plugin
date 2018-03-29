package de.artcom_venture.elasticsearch.naturalsort;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordTokenizer;

import java.text.Collator;

public class NaturalSortAnalyzer extends Analyzer {

    private final NaturalSortAttributeFactory factory;

    public NaturalSortAnalyzer(Collator collator, int digits, int maxtoken) {
        this.factory = new NaturalSortAttributeFactory(collator, digits, maxtoken);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        KeywordTokenizer tokenizer = new KeywordTokenizer(factory, KeywordTokenizer.DEFAULT_BUFFER_SIZE);
        return new TokenStreamComponents(tokenizer, tokenizer);
    }
}
