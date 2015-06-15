resultadoProcesado <- read.table("E:/MiCuenta/Documents/GitHub/inicio/SimulacionEventoDiscreto/test/resultadoProcesado.txt")
names(resultadoProcesado) = c("MomentoWorking","MomentoDanio","MomentoReparacionInicial","MomentoReparacionFinal")
seleccion <- subset(resultadoProcesado,select=MomentoDanio:MomentoReparacionFinal) 
tiempoPromedioFueraServicio <- mean(seleccion$MomentoReparacionFinal-seleccion$MomentoDanio)
cat("El promedio de tiempo fuera de servicio es: ", tiempoPromedioFueraServicio)

