package com.ml_sma.service;

import com.ml_sma.service.datafetcher.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    @Autowired
    DownloadDataFetcher downloadDataFetcher;
    private GraphQL graphQL;
    @Autowired
    ScrapingDataFetcher scrapingDataFetcher;
    @Autowired
    ProcessDataFetcher processDataFetcher;
    @Autowired
    AlgorithmDataFetcher algorithmDataFetcher ;
    @Autowired
    GuessTextDataFetcher guessTextDataFetcher;
    @Autowired
    PcaDataFetcher pcaDataFetcher ;


    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("processData", processDataFetcher)
                        .dataFetcher("ExecuteAgentAlgo", algorithmDataFetcher)
                        .dataFetcher("guessText", guessTextDataFetcher)
                        .dataFetcher("pca", pcaDataFetcher)
                        .dataFetcher("downloadData", downloadDataFetcher)
                        .dataFetcher("scraping", scrapingDataFetcher))
                        .build();
    }


    public GraphQL getGraphQL() {
        return graphQL;
    }
}
