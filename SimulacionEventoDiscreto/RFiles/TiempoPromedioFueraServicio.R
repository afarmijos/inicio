resultadoProcesado <- read.table("E:/MiCuenta/Documents/GitHub/inicio/SimulacionEventoDiscreto/test/resultadoProcesado.txt")
names(resultadoProcesado) = c("MomentoReparacionFinalAnterior","MomentoDanio","MomentoReparacionInicial","MomentoReparacionFinal")
seleccion <- subset(resultadoProcesado,select=MomentoReparacionFinalAnterior:MomentoDanio) 
tiempoPromedioFueraServicio <- mean(seleccion$MomentoDanio-seleccion$MomentoReparacionFinalAnterior)
cat("El promedio de tiempo fuera de servicio es: ", tiempoPromedioFueraServicio)

