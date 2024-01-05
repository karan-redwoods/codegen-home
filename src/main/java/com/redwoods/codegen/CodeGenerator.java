package com.redwoods.codegen;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    private EntityClassGenerator entityClassGenerator;
    private RestControllerGenerator restControllerGenerator;
    private ServiceInterfaceGenerator serviceInterfaceGenerator;
    private ServiceImplClassGenerator serviceImplClassGenerator;
    private RepositoryInterfaceGenerator repositoryInterfaceGenerator;

    public CodeGenerator(EntityClassGenerator entityClassGenerator,
                         RestControllerGenerator restControllerGenerator,
                         ServiceInterfaceGenerator serviceInterfaceGenerator,
                         ServiceImplClassGenerator serviceImplClassGenerator,
                         RepositoryInterfaceGenerator repositoryInterfaceGenerator){
        this.entityClassGenerator = entityClassGenerator;
        this.restControllerGenerator = restControllerGenerator;
        this.serviceInterfaceGenerator = serviceInterfaceGenerator;
        this.serviceImplClassGenerator = serviceImplClassGenerator;
        this.repositoryInterfaceGenerator = repositoryInterfaceGenerator;
    }

    public void generateCRUDCode(String entityName){
        entityClassGenerator.generateEntityClass(entityName);
        restControllerGenerator.generateRestControllerClass(entityName);
        serviceInterfaceGenerator.generateServiceInterface(entityName);
        serviceImplClassGenerator.generateServiceImplClass(entityName);
        repositoryInterfaceGenerator.generateRepository(entityName);
    }
}
