
RM := rm -fr

BUILDDIR  := build
SRCDIR    := src
ETCDIR    := etc
JAVA_BUILDDIR  := $(BUILDDIR)/java
PROJECT_JAR_NAME := simulacion.jar
MANIFEST  := $(ETCDIR)/MANIFEST.MF
CARPETA_PRUEBA:=test
ARCHIVO_ENTRADA_DRIVEN:= $(CARPETA_PRUEBA)/entradaDriven.txt
ARCHIVO_RESULTADO:= $(CARPETA_PRUEBA)/resultado.txt
ARCHIVO_RESULTADO_PROCESADO:= $(CARPETA_PRUEBA)/resultadoProcesado.txt


MAKE_BUILD_WARNING := echo "Something is wrong, try 'make compile' first"
MAKE_ALL_WARNING   := echo "Something is wrong, try 'make all' first"


compile: 	
	javac -classpath $(classpath) -sourcepath $(SRCDIR)/*.java -d $(JAVA_BUILDDIR)
	jar cfm $(PROJECT_JAR_NAME) $(MANIFEST) -C $(JAVA_BUILDDIR) . 
	echo "compilacion exitosa. Jar " $(PROJECT_JAR_NAME) " generado." 	
clean:	
	$(RM) "$(BUILDDIR)" "$(PROJECT_JAR_NAME)" 

ejecutarAleatorio:
	java -cp $(PROJECT_JAR_NAME) DiscreteEventSimulation random => $(ARCHIVO_RESULTADO)

ejecutarDriven:
	java -cp $(PROJECT_JAR_NAME) DiscreteEventSimulation trace $(ARCHIVO_ENTRADA_DRIVEN) => $(ARCHIVO_RESULTADO)
	
procesarResultado:
	java -cp $(PROJECT_JAR_NAME) DiscreteEventSimulation procesarResultado $(ARCHIVO_RESULTADO) $(ARCHIVO_RESULTADO_PROCESADO)

generarDriven:
	java -cp $(PROJECT_JAR_NAME) DiscreteEventSimulation generarDriven $(ARCHIVO_RESULTADO) entradaDriven.txt