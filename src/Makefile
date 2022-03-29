all: Main CtrlDominio Clases Controladores CtrlPersistencia Presentacion

Main: Main.java
	javac -encoding ISO-8859-1 *.java

CtrlDominio: Dominio/CtrlDominio.java
	javac -encoding ISO-8859-1 Dominio/*.java

Clases: Dominio/Clases/Cell.java Dominio/Clases/Blanca.java Dominio/Clases/Negra.java Dominio/Clases/NoJugable.java Dominio/Clases/Jugable.java Dominio/Clases/Pair.java Dominio/Clases/Ranking.java Dominio/Clases/Usuario.java Dominio/Clases/kakuro.java
	javac -encoding ISO-8859-1 Dominio/Clases/*.java

Controladores: Dominio/Controladores/Generar.java Dominio/Controladores/LeerEscribir.java Dominio/Controladores/SolveValida.java Dominio/Controladores/Partida.java Dominio/Controladores/CtrlRankings.java Dominio/Controladores/CtrlUsuario.java
	javac -encoding ISO-8859-1 Dominio/Controladores/*.java

CtrlPersistencia: Persistencia/CtrlPersistencia.java
	javac -encoding ISO-8859-1 Persistencia/*.java

Presentacion: Presentacion/CtrlPresentacion.java Presentacion/Crear.java Presentacion/Generar.java Presentacion/Gestionarkakuro.java Presentacion/Jugar.java Presentacion/PantallaPrincipal.java Presentacion/Partida.java Presentacion/Perfil.java Presentacion/Preview.java Presentacion/Ranking.java Presentacion/Register.java Presentacion/SignIn.java
	javac -encoding ISO-8859-1 Presentacion/*.java

run:
	java Main

Tests: Dominio/Controladores/Tests/DriverCtrlRanking.java Dominio/Controladores/Tests/DriverCtrlUsuarios.java Dominio/Controladores/Tests/DriverPair.java Dominio/Controladores/Tests/DriverRanking.java Dominio/Controladores/Tests/DriverSolve.java Dominio/Controladores/Tests/DriverUsuario.java
	
	javac -encoding ISO-8859-1 Dominio/Controladores/Tests/*.java


clean:
	rm -f *.class
	rm -f ./Dominio/*.class
	rm -f ./Dominio/Clases/*.class
	rm -f ./Dominio/Controladores/*.class
	rm -f ./Dominio/Controladores/Tests/*.class
	rm -f ./Persistencia/*.class
	rm -f ./Presentacion/*.class



