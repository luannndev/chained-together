package dev.luan.chained;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dev.luan.chained.common.GuiceModule;
import dev.luan.chained.common.models.Chain;
import dev.luan.chained.common.registry.Registry;
import dev.luan.chained.services.ChainService;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

@Log4j2
@Getter
@Accessors(fluent = true)
public class ChainedTogether extends JavaPlugin {

    private final Injector injector;
    private final Registry registry;

    @Inject
    private ChainService chainService;

    public ChainedTogether() {
        this.injector = Guice.createInjector(new GuiceModule(this));
        this.registry = new Registry(this, getClassLoader(), this.injector);
    }

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        Bukkit.getServicesManager().register(Injector.class, this.injector, this, ServicePriority.Normal);
        this.registry.registerAllListeners();

        log.info("ChainedTogether enabled in {}ms", System.currentTimeMillis() - startTime);
    }

    @Override
    public void onDisable() {
        this.chainService.getActiveChains().forEach(Chain::disband);
    }
}
