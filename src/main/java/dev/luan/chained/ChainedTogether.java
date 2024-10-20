package dev.luan.chained;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.luan.chained.common.GuiceModule;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.bukkit.plugin.java.JavaPlugin;

@Log4j2
@Getter
@Accessors(fluent = true)
public class ChainedTogether extends JavaPlugin {

    private final Injector injector;

    public ChainedTogether() {
        this.injector = Guice.createInjector(new GuiceModule(this));
    }
}
