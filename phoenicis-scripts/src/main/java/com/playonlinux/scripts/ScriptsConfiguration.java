package com.playonlinux.scripts;

import com.playonlinux.apps.AppsConfiguration;
import com.playonlinux.multithreading.MultithreadingConfiguration;
import com.playonlinux.scripts.interpreter.BackgroundScriptInterpreter;
import com.playonlinux.scripts.interpreter.ScriptFetcher;
import com.playonlinux.scripts.interpreter.ScriptInterpreter;
import com.playonlinux.scripts.nashorn.NashornEngineFactory;
import com.playonlinux.scripts.nashorn.NashornScriptInterpreter;
import com.playonlinux.scripts.wizard.WizardConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WizardConfiguration.class)
public class ScriptsConfiguration {
    @Autowired
    private WizardConfiguration wizardConfiguration;

    @Autowired
    private AppsConfiguration appsConfiguration;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MultithreadingConfiguration multithreadingConfiguration;

    @Bean
    public NashornEngineFactory scriptEngineFactory() {
        return new NashornEngineFactory(wizardConfiguration.setupWizardFactory(), scriptFetcher());
    }

    @Bean
    public ScriptFetcher scriptFetcher() {
        return new ScriptFetcher(appsConfiguration.appsManager());
    }

    @Bean
    public ScriptInterpreter scriptInterpreter() {
        return new BackgroundScriptInterpreter(nashornInterprpeter(), multithreadingConfiguration.scriptExecutorService());
    }

    @Bean
    ScriptInterpreter nashornInterprpeter() {
        return new NashornScriptInterpreter(scriptEngineFactory());
    }
}
