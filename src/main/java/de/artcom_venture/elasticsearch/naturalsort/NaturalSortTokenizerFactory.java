package de.artcom_venture.elasticsearch.naturalsort;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;
import org.elasticsearch.index.settings.IndexSettingsService;
import java.text.Collator;

public class NaturalSortTokenizerFactory extends AbstractTokenizerFactory {

    private final NaturalSortAttributeFactory factory;

    @Inject
    public NaturalSortTokenizerFactory(Index index,
            IndexSettingsService indexSettingsService,
            @Assisted String name,
            @Assisted Settings settings) {
        super(index, indexSettingsService.indexSettings(), name, settings);
        
        Collator collator = NaturalSortAnalyzerProvider.createCollator(settings);
        int digits = settings.getAsInt("digits", 1);
        int maxTokens = settings.getAsInt("maxTokens", 2);
        this.factory = new NaturalSortAttributeFactory(collator, digits, maxTokens);
    }

    @Override
    public Tokenizer create() {
        return new KeywordTokenizer(factory, KeywordTokenizer.DEFAULT_BUFFER_SIZE);
    }
}
