package com.cameronarbis.nerapplication.core;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.jvnet.hk2.annotations.Service;
import org.springframework.context.annotation.Bean;
import java.util.Properties;


@Service
public class Pipeline {
    private static Properties properties;
    private static String propertiesName = "tokenize, ssplit, pos, lemma, ner";
    private static StanfordCoreNLP stanfordCoreNLP;

    private Pipeline() {

    }
    static {
        properties = new Properties();
        properties.setProperty("annotators", propertiesName);

    }
    @Bean(name = "stanfordCoreNLP")
    public static StanfordCoreNLP getInstance() {
        if(stanfordCoreNLP == null) {
            stanfordCoreNLP = new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }
}