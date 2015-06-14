resultadoProcesado <- read.table("E:/MiCuenta/Documents/GitHub/inicio/SimulacionEventoDiscreto/test/resultadoProcesado.txt")
names(resultadoProcesado) = c("MomentoTrabajando","MomentoDanio","MomentoReparacionInicial","MomentoReparacionFinal")
seleccion <- subset(resultadoProcesado,select=MomentoReparacionFinal:MomentoReparacionInicial) 
tiempoPromedioReparacion <- mean(seleccion$MomentoReparacionFinal-seleccion$MomentoReparacionInicial)
cat("El promedio de tiempo reparacion es: ", tiempoPromedioReparacion)

