package dev.luan.chained.common;

import com.google.inject.AbstractModule;
import dev.luan.chained.ChainedTogether;
import lombok.AllArgsConstructor;
import org.checkerframework.common.util.count.report.qual.ReportCall;

@AllArgsConstructor
public class GuiceModule extends AbstractModule {

    private final ChainedTogether chainedTogether;

    @Override
    protected void configure() {
        bind(ChainedTogether.class).toInstance(this.chainedTogether);
    }
}
