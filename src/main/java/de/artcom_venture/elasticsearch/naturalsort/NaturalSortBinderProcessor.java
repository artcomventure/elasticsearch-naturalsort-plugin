package de.artcom_venture.elasticsearch.naturalsort;

import org.elasticsearch.index.analysis.AnalysisModule;

public class NaturalSortBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("naturalsort", NaturalSortAnalyzerProvider.class);
    }
}
