package de.artcom_venture.elasticsearch.naturalsort;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.util.AttributeFactory;
import java.text.Collator;

public class NaturalSortAttributeFactory extends AttributeFactory.StaticImplementationAttributeFactory<NaturalSort> {

    private final Collator collator;
    private final int digits;
    private final int maxTokens;

    public NaturalSortAttributeFactory(Collator collator, int digits, int maxTokens) {
        this(TokenStream.DEFAULT_TOKEN_ATTRIBUTE_FACTORY, collator, digits, maxTokens);
    }

    public NaturalSortAttributeFactory(AttributeFactory delegate, Collator collator, int digits, int maxTokens) {
        super(delegate, NaturalSort.class);
        
        this.collator = collator;
        this.digits = digits;
        this.maxTokens = maxTokens;
    }

    @Override
    protected NaturalSort createInstance() {
        return new NaturalSort(collator, digits, maxTokens);
    }
}
