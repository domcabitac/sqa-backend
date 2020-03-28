JFLAGS = -g
JC = javac
JVM= java
.SUFFIXES: .java .class
.java.class: ; $(JC) $(JFLAGS) $*.java

 CLASSES = \
    OutputWriter.java

 MAIN = Main

 default: classes

 classes: $(CLASSES:.java=.class)

 run : classes 
	$(JVM) OutputWriter daily-transactions.txt current-user-accounts.txt available-items-test.txt

 test : 
	export CLASSPATH=$CLASSPATH:./junit-4.13.jar:./hamcrest-core-1.3.jar:./org.apache.commons.io.jar
	$(JVM)c TestJunit.java TestRunner.java

	