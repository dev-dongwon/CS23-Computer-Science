JFLAGS = -d . 
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	GitArea.java \
	InstructionUtils.java \
	RemoteArea.java \
	Instruction.java \
	VmGitProgramUtils.java \
	VmGitProgram.java

classes: $(CLASSES:.java=.class)

all: classes

Instruction.class : GitArea.class InstructionUtils.class RemoteArea.class
	$(JC) $(JFLAGS) GitArea.java
	$(JC) $(JFLAGS) InstructionUtils.java
	$(JC) $(JFLAGS) RemoteArea.java

VmGitProgram.class : VmGitProgramUtils.class Instruction.class
	$(JC) $(JFLAGS) Instruction.java
	$(JC) $(JFLAGS) VmGitProgramUtils.java

test : 
	$(JC) $(JFLAGS) VmGitProgram.java

OBJECTS = \
	GitArea.class \
	InstructionUtils.class \
	RemoteArea.class \
	Instruction.class \
	VmGitProgramUtils.class \
	VmGitProgram.class

install : 
	mv $(OBJECTS) /usr/bin

