package com.yummipizza.api;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.yummipizza.api");

        noClasses()
            .that()
                .resideInAnyPackage("com.yummipizza.api.service..")
            .or()
                .resideInAnyPackage("com.yummipizza.api.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.yummipizza.api.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
