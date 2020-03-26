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
	