# knattralang
The glorious knattra language

Implementation heavily inspired by Enkel 
https://github.com/JakubDziworski/Enkel-JVM-language


#Usefull commands

compile project:
```mvn clean package```

compile a knattra language file:
```java -cp compiler/target/compiler-1.0-SNAPSHOT-jar-with-dependencies.jar com.jayway.knattra.Compiler KnattraCode/first.knt```


run the compiled class: ```java first```

view bytecode for a class ```javap -c first.class```


generate asm code for a class
```java -cp compiler/target/compiler-1.0-SNAPSHOT-jar-with-dependencies.jar org.objectweb.asm.util.ASMifier first.class```



#Instructions
Add more operators

Add more primitive types

Add String concatenation HINT: See print statement






#Documentation

## ANTLR
https://github.com/antlr/antlr4/blob/master/doc/index.md

## ASM
http://download.forge.objectweb.org/asm/asm4-guide.pdf