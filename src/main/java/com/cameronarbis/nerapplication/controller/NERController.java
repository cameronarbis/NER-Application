package com.cameronarbis.nerapplication.controller;

import com.cameronarbis.nerapplication.model.Type;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class NERController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    @PostMapping
    @RequestMapping(value = "/ner")
     public Set<String> ner(@RequestBody final String input, final Type type) {
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();
        return new HashSet<>(collectList(coreLabels, type));

    }

    private List<String> collectList(List<CoreLabel> coreLabels, final Type type) {

        coreLabels
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());

        return null;
    }

}