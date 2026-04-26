import org.gradle.kotlin.dsl.*
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("java-library")
  id("org.springframework.boot") version "3.5.7"
  id("io.spring.dependency-management") version "1.1.6"
  id("co.uzzu.dotenv.gradle") version "4.0.0"
  id("pmd")
}

group = "com.joalvarez"
version = "1.0.0"
description = "spring-poc-ai-module"

pmd {
  toolVersion = "7.14.0"
  ruleSets = listOf()
  ruleSetFiles = files("linters/pmd-ruleset.xml")
  isConsoleOutput = true
  isIgnoreFailures = false
}

val springAiVersion = "1.0.0"

repositories {
  mavenCentral()
  mavenLocal()

  maven {
    url = uri("https://repo.spring.io/milestone")
  }
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.ai:spring-ai-bom:${springAiVersion}")
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-validation")

  //implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.4")
  runtimeOnly("org.postgresql:postgresql")
//  implementation("com.h2database:h2")
  implementation("org.liquibase:liquibase-core")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.instancio:instancio-junit:5.4.1")

  implementation("org.springframework.ai:spring-ai-starter-model-openai")
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
  options.encoding = "UTF-8"
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.named<BootJar>("bootJar") {
  archiveFileName.set("spring-poc-ai-module.jar")
}

tasks.register("printProjectName") {
    doLast {
        println(rootProject.name)
    }
}

tasks.withType<ProcessResources> {
  filesMatching("application.yml") {
    val tokens = mapOf(
      "version" to project.version,
      "description" to project.description
    )
    filter(
      org.apache.tools.ant.filters.ReplaceTokens::class,
      "tokens" to tokens,
      "beginToken" to "@",
      "endToken" to "@"
    )
  }
}
