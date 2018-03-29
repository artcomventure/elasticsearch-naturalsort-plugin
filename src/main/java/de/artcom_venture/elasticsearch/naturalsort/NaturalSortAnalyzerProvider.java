package de.artcom_venture.elasticsearch.naturalsort;

import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.elasticsearch.index.settings.IndexSettingsService;

import java.text.Collator;
import java.util.Locale;

public class NaturalSortAnalyzerProvider extends AbstractIndexAnalyzerProvider<NaturalSortAnalyzer> {

    private final Collator collator;
    private final int digits;
    private final int maxTokens;

    @Inject
    public NaturalSortAnalyzerProvider(Index index,
            IndexSettingsService indexSettingsService,
            @Assisted String name,
            @Assisted Settings settings) {
        super(index, indexSettingsService.indexSettings(), name, settings);
        
        this.collator = createCollator(settings);
        this.digits = settings.getAsInt("digits", 9);
        this.maxTokens = settings.getAsInt("maxTokens", 5);
    }

    @Override
    public NaturalSortAnalyzer get() {
        return new NaturalSortAnalyzer(collator, digits, maxTokens);
    }

    protected static Collator createCollator(Settings settings) {
        return Collator.getInstance(Locale.getDefault());
    }
}
