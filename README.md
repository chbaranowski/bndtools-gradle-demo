# Bndtools Gradle Build Demo

The project contains a simple bndtools / bnd project. 
Demo project can be build with gradle version 1.2 (http://www.gradle.org).
Gradle must not be installed then invoke gradle via the wrapper script "gradlew".

## Build with Gradle

To build the project invoke:

    gradlew build
    
    or
    
    gradle build
    
## Run Bnd Testing 

To run the OSGi bundle tests invoke:

    gradlew testBundle
    
    or
    
    gradle testBundle

## Import in Eclipse

To generate the eclipse project settings invoke:

    gradlew eclipse
    
    or 
    
    gradle eclipse

After generating the eclipse project settings the bndtools project be imported in eclipse.