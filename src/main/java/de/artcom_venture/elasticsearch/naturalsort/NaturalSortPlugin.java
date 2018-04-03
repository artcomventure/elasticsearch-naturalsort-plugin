package de.artcom_venture.elasticsearch.naturalsort;

import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.Plugin;

public class NaturalSortPlugin extends Plugin {

    public void onModule(AnalysisModule module) {
        module.addProcessor(new NaturalSortBinderProcessor());
    }

    @Override
    public String name() {
        return "naturalsort";
    }

    @Override
    public String description() {
        return "NaturalSort Plugin";
    }
}
